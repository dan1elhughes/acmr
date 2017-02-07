package com.rv007602.acmr.passengernumbers;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Mappable;

import java.util.ArrayList;

public class Mapper extends Mappable {

	protected ArrayList<KVPair> map(String line) {
		// Headers	: Passenger, Flight, From, To, DepartedAt, Duration
		// Line		: UES9151GS5,SQU6245R,DEN,FRA,1420564460,1049
		// Output	: <SQU6245R, 1>

		String[] attributes = line.split(",");

		String valid = "[A-Z]{3}[0-9]{4}[A-Z]";

		ArrayList<KVPair> results = new ArrayList<>();

		if (attributes[0] != line) {
			String flightID = attributes[1];

			if (flightID.matches(valid)) {
				results.add(new KVPair(flightID, String.valueOf(1)));
			}
		}

		return results;
	}
}
