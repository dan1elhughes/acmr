package com.rv007602.acmr.flightcount;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Mappable;

import java.util.ArrayList;

public class Flights_Mapper extends Mappable {
	protected ArrayList<KVPair> map(String line) {
		// Headers	: Passenger, Flight, From, To, DepartedAt, Duration
		// Line		: UES9151GS5,SQU6245R,DEN,FRA,1420564460,1049
		// Output	: <"DEN", "SQU6245R">

		String[] attributes = line.split(",");

		String codeValid = "[A-Z]{3}";
		String flightValid = "[A-Z]{3}[0-9]{4}[A-Z]";

		ArrayList<KVPair> results = new ArrayList<>();

		if (attributes[0] != line) {
			// TODO: Check validity before adding result
			String flight = attributes[1];
			String code = attributes[2];

			if (code.matches(codeValid) && flight.matches(flightValid)) {
				results.add(new KVPair(code, flight));
			}

		}

		return results;

	}
}
