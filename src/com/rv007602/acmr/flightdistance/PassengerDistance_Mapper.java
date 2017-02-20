package com.rv007602.acmr.flightdistance;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Mappable;

import java.util.ArrayList;

public class PassengerDistance_Mapper extends Mappable {
	@Override
	protected ArrayList<KVPair> map(String line) {
		// Headers	: Passenger, Flight, From, To, DepartedAt, Duration
		// Line		: UES9151GS5,SQU6245R,DEN,FRA,1420564460,1049
		// Output	: <"UES9151GS5", "DEN|FRA">

		String[] attributes = line.split(",");

		String codeValid = "[A-Z]{3}";
		String passengerValid = "[A-Z]{3}[0-9]{4}[A-Z]{2}[0-9]";

		ArrayList<KVPair> results = new ArrayList<>();

		if (attributes[0] != line) {
			String passengerID = attributes[0];
			String from = attributes[2];
			String to = attributes[3];

			if (from.matches(codeValid) && to.matches(codeValid) && passengerID.matches(passengerValid)) {
				results.add(new KVPair(passengerID, from + "|" + to));
			}
		}

		return results;
	}
}
