package com.rv007602.acmrlib;

public class KVPair {
	private final String key;
	private final String value;

	public KVPair(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}

	public String toString() {
		return this.key + "," + this.value;
	}
}
