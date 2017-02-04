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
	private HashMap<String, ArrayList<String>> mapResults = new HashMap<>();
	private HashMap<String, String> reduceResults = new HashMap<>();

	public void setInput(BufferedReader input) {
		this.input = input;
	}

	public void setMapper(Class<? extends Mappable> mapperClass) {
		this.mapperClass = mapperClass;
	}

	public void setReducer(Class<? extends Reducible> reducerClass) {
		this.reducerClass = reducerClass;
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

}
