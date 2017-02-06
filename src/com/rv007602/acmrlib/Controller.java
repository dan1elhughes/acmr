package com.rv007602.acmrlib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Controller {

	private final HashMap<String, ArrayList<String>> mapResults = new HashMap<>();
	private final HashMap<String, String> reduceResults = new HashMap<>();
	private Class<? extends Mappable> mapperClass;
	private Class<? extends Reducible> reducerClass;
	private BufferedReader input;
	private BufferedWriter output;

	public void setInput(BufferedReader input) {
		this.input = input;
	}

	public void setMapper(Class<? extends Mappable> mapperClass) {
		this.mapperClass = mapperClass;
	}

	public void setReducer(Class<? extends Reducible> reducerClass) {
		this.reducerClass = reducerClass;
	}

	public void run() {
		try {
			this.map();
			this.reduce();
			this.finish();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void map() throws Exception {
		String line;
		BufferedReader input = this.input;
		Constructor constructor = this.mapperClass.getConstructor();

		int cpuThreads = Runtime.getRuntime().availableProcessors();
		ExecutorService pool = Executors.newFixedThreadPool(cpuThreads);
		Set<Future<ArrayList<KVPair>>> set = new HashSet<>();

		while ((line = input.readLine()) != null) {
			Mappable mapper = (Mappable) constructor.newInstance();
			mapper.setInput(line);
			Future f = pool.submit(mapper);
			set.add(f);
		}

		for (Future<ArrayList<KVPair>> future : set) {
			this.appendMapResults(future.get());
		}

		pool.shutdown();
	}

	private void reduce() throws Exception {
		Constructor constructor = this.reducerClass.getConstructor();

		int cpuThreads = Runtime.getRuntime().availableProcessors();
		ExecutorService pool = Executors.newFixedThreadPool(cpuThreads);
		Set<Future<KVPair>> set = new HashSet<>();

		for (HashMap.Entry<String, ArrayList<String>> entry : this.mapResults.entrySet()) {
			Reducible reducer = (Reducible) constructor.newInstance();
			reducer.setData(entry.getKey(), entry.getValue());

			Future f = pool.submit(reducer);
			set.add(f);
		}

		for (Future<KVPair> future : set) {
			this.appendReduceResult(future.get());
		}

		pool.shutdown();
	}

	private void finish() {
		String contents = "";

		for (HashMap.Entry<String, String> result : this.reduceResults.entrySet()) {
			KVPair kvp = new KVPair(result.getKey(), result.getValue());
			contents += String.format("%s\n", kvp);
		}

		try {
			BufferedWriter output = this.output;
			output.write(contents);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setOutput(BufferedWriter output) {
		this.output = output;
	}

	private void appendMapResults(ArrayList<KVPair> results) {
		for (KVPair result : results) {

			ArrayList<String> values = this.mapResults.get(result.getKey());
			if (values == null) {
				this.mapResults.put(result.getKey(), new ArrayList<>());
			}

			this.mapResults.get(result.getKey()).add(result.getValue());
		}
	}

	private void appendReduceResult(KVPair result) {
		this.reduceResults.put(result.getKey(), result.getValue());
	}
}
