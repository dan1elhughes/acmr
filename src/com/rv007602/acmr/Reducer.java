package com.rv007602.acmr;

import com.rv007602.acmrlib.KVPair;
import com.rv007602.acmrlib.Reducible;

import java.util.ArrayList;

public class Reducer extends Reducible {

	public KVPair reduce(String key, ArrayList<String> values) {
		int count = 0;
		for (String value : values) {
			count += Integer.parseInt(value, 10);
		}

		return new KVPair(key, String.valueOf(count));
	}
}