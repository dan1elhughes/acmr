package com.rv007602.acmr.flightcount;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Reducible;

import java.util.ArrayList;

public class Airports_Reducer extends Reducible {
	@Override
	protected KVPair reduce(String key, ArrayList<String> values) {
		int count = 0;

		for (String val : values) {
			count += Integer.parseInt(val, 10);
		}

		return new KVPair(key, String.valueOf(count));
	}
}
