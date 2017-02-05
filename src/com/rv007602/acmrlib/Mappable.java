package com.rv007602.acmrlib;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public abstract class Mappable implements Callable {
	private String line;

	protected abstract ArrayList<KVPair> map(String line);

	public void setInput(String line) {
		this.line = line;
	}

	public ArrayList<KVPair> call() {
		return this.map(this.line);
	}
}
