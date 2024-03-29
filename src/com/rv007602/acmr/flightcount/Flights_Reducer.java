package com.rv007602.acmr.flightcount;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Reducible;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Flights_Reducer extends Reducible {
	@Override
	protected KVPair reduce(String key, ArrayList<String> values) {
		// Input	: key, <"FLIGHT1", "FLIGHT1", "FLIGHT2">
		// Output	: <key, "2">

		Set<String> uniques = new HashSet<>();
		uniques.addAll(values);
		return new KVPair(key, String.valueOf(uniques.size()));
	}
}
