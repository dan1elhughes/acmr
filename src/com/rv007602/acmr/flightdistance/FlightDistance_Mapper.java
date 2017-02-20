package com.rv007602.acmr.flightdistance;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Mappable;

import java.util.ArrayList;

public class FlightDistance_Mapper extends Mappable {
	@Override
	protected ArrayList<KVPair> map(String line) {
		// Headers	: Passenger, Flight, From, To, DepartedAt, Duration
		// Line		: UES9151GS5,SQU6245R,DEN,FRA,1420564460,1049
		// Output	: <"SQU6245R", "DEN|FRA">

		String[] attributes = line.split(",");

		String codeValid = "[A-Z]{3}";
		String flightValid = "[A-Z]{3}[0-9]{4}[A-Z]";

		ArrayList<KVPair> results = new ArrayList<>();

		if (attributes[0] != line) {
			String flightID = attributes[1];
			String from = attributes[2];
			String to = attributes[3];

			if (from.matches(codeValid) && to.matches(codeValid) && flightID.matches(flightValid)) {
				results.add(new KVPair(flightID, from + "|" + to));
			}
		}

		return results;
	}
}
