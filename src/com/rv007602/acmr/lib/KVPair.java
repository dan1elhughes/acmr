package com.rv007602.acmr.lib;

public class KVPair {
	private final String key;
	private final String value;

	/**
	 * Creates a new immutable KVPair.
	 * @param key The key.
	 * @param value The value.
	 */
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

	/**
	 * Converts the KVPair into a CSV-style string.
	 * @return String representation.
	 */
	public String toString() {
		return this.key + "," + this.value;
	}
}
