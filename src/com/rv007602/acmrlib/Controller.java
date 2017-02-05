package com.rv007602.acmrlib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

	private Class<? extends Mappable> mapperClass;
	private Class<? extends Reducible> reducerClass;
	private BufferedReader input;
	private BufferedWriter output;
	private final HashMap<String, ArrayList<String>> mapResults = new HashMap<>();
	private final HashMap<String, String> reduceResults = new HashMap<>();

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
		this.map();
		this.reduce();
		this.finish();
	}

	private void map() {
		String line;
		BufferedReader input = this.input;
		Mappable mapper;

		try {
			mapper = this.mapperClass.getConstructor().newInstance();
			while ((line = input.readLine()) != null) {
				mapper.setInput(line);

				ArrayList<KVPair> results = mapper.call();
				this.appendMapResults(results);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void reduce() {
		Reducible reducer;
		try {
			reducer = this.reducerClass.getConstructor().newInstance();
			for (HashMap.Entry<String, ArrayList<String>> entry : this.mapResults.entrySet()) {
				reducer.setData(entry.getKey(), entry.getValue());

				KVPair result = reducer.call();
				this.appendReduceResult(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
