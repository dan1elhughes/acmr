package com.rv007602.acmr.flightcount;

import com.rv007602.acmr.Validate;
import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Mappable;

import java.util.ArrayList;

public class Airports_Mapper extends Mappable {
	@Override
	protected ArrayList<KVPair> map(String line) {
		// Headers	: Name, Code, Lat, Long
		// Line		: ATLANTA,ATL,33.636719,-84.428067
		// Output	: <ATL, "0">

		String[] attributes = line.split(",");

		ArrayList<KVPair> results = new ArrayList<>();

		if (attributes[0] != line) {
			String code = attributes[1];

			if (Validate.airport(code)) {
				results.add(new KVPair(code, String.valueOf(0)));
			}
		}

		return results;
	}
}
