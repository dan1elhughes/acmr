package com.rv007602.acmrlib;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public abstract class Reducible implements Callable {
	private String key;
	private ArrayList<String> values;

	protected abstract KVPair reduce(String key, ArrayList<String> values);

	public void setData(String key, ArrayList<String> values) {
		this.key = key;
		this.values = values;
	}

	public KVPair call() {
		System.out.println("Reduce { " + this.key + " , " + values.size() + " values } :: " + Thread.currentThread().getName());
		return this.reduce(this.key, this.values);
	}
}
