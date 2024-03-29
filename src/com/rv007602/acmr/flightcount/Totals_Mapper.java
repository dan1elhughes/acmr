package com.rv007602.acmr.flightcount;

import com.rv007602.acmr.Validate;
import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Mappable;

import java.util.ArrayList;

public class Totals_Mapper extends Mappable {

	@Override
	protected ArrayList<KVPair> map(String line) {
		// Headers	: Airport, Flights
		// Line		: DEN,1
		// Output	: <"DEN", "1">

		String[] attributes = line.split(",");

		ArrayList<KVPair> results = new ArrayList<>();

		if (attributes[0] != line) {
			String code = attributes[0];
			String subtotal = attributes[1];

			if (Validate.airport(code)) {
				results.add(new KVPair(code, subtotal));
			}
		}

		return results;
	}
}
