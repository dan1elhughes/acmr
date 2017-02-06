package com.rv007602.acmr.passengernumbers;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Reducible;

import java.util.ArrayList;

public class Reducer extends Reducible {
	@Override
	protected KVPair reduce(String key, ArrayList<String> values) {
		int total = 0;

		for (String count : values) {
			total += Integer.parseInt(count, 10);
		}

		return new KVPair(key, String.valueOf(total));
	}
}
