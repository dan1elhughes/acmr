package com.rv007602.acmr.flightcount;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Reducible;

import java.util.ArrayList;

public class Airports_Reducer extends Reducible {
	@Override
	protected KVPair reduce(String key, ArrayList<String> values) {
		String val = "";

		for (String value : values) {
			val += value;
		}
		return new KVPair(key, val);
	}
}
