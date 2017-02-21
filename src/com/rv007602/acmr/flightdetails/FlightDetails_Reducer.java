package com.rv007602.acmr.flightdetails;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Reducible;

import java.util.ArrayList;

public class FlightDetails_Reducer extends Reducible {
	@Override
	protected KVPair reduce(String key, ArrayList<String> values) {
		String[] attributes = values.get(0).split(",");

		/*
		Create a list of flights based on the Flight id, this output should include the passenger Id, relevant IATA/FAA codes, the departure time, the arrival time (times to be converted to HH:MM:SS format), and the flight times.
		 */

		String from = attributes[1];
		String to = attributes[2];
		String departed = attributes[3];
		String duration = attributes[4];

		ArrayList<String> passengers = new ArrayList<>();

		for (String value : values) {
			passengers.add(value.split(",")[0]);
		}

		String passengerS = String.join(", ", passengers);
		String val = "";

		try {
			val = String.format(" from %s to %s\nDeparted: %s\nArrived: %s\nPassengers: %s\n\n",
					from,
					to,
					departed,
					duration,
					passengerS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new KVPair(key, val);
	}
}
