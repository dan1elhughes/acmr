package com.rv007602.acmr.flightdetails;

import com.rv007602.acmr.Validate;
import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Mappable;

import java.util.ArrayList;

public class FlightDetails_Mapper extends Mappable {
	@Override
	protected ArrayList<KVPair> map(String line) {
		// Headers	: Passenger, Flight, From, To, DepartedAt, Duration
		// Line		: UES9151GS5,SQU6245R,DEN,FRA,1420564460,1049
		// Output	: <"SQU6245R", "UES9151GS5,DEN,FRA,1420564460,1049">

		String[] attributes = line.split(",");

		ArrayList<KVPair> results = new ArrayList<>();

		if (!attributes[0].equals(line)) {
			boolean valid = true;

			String passenger = attributes[0];
			valid &= Validate.passenger(passenger);

			String flight = attributes[1];
			valid &= Validate.flight(flight);

			String from = attributes[2];
			valid &= Validate.airport(from);

			String to = attributes[3];
			valid &= Validate.airport(to);

			String departed = attributes[4];
			valid &= Validate.time(departed);

			String duration = attributes[5];
			valid &= Validate.duration(duration);

			if (valid) {
				results.add(new KVPair(flight, String.format("%s,%s,%s,%s,%s", passenger, from, to, departed, duration)));
			}
		}

		return results;
	}
}
