package com.rv007602.acmr.flightdetails;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Reducible;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FlightDetails_Reducer extends Reducible {
	@Override
	protected KVPair reduce(String key, ArrayList<String> values) {
		String[] attributes = values.get(0).split(",");

		/*
		Create a list of flights based on the Flight id, this output should include the passenger Id, relevant IATA/FAA codes, the departure time, the arrival time (times to be converted to HH:MM:SS format), and the flight times.
		 */

		String from = attributes[1];
		String to = attributes[2];
		String departedS = attributes[3];
		String durationS = attributes[4];

		int timestamp = Integer.parseInt(departedS, 10);
		int duration = Integer.parseInt(durationS, 10);

		Date departed = new Date((long) timestamp * 1000);
		String departedAt = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy").format(departed);

		Date arrived = new Date(departed.getTime() + (duration * 60 * 1000));
		String arrivedAt = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy").format(arrived);

		int hours = duration / 60;
		int minutes = duration % 60;

		String durationOfFlight = String.format("%d hours and %d minutes", hours, minutes);

		ArrayList<String> passengers = new ArrayList<>();

		for (String value : values) {
			passengers.add(value.split(",")[0]);
		}

		String passengerS = String.join("\n\t", passengers);
		String val = "";

		try {
			val = String.format(
					" from %s to %s\n" +
					"Departed: %s (GMT)\n" +
					"Arrived: %s (GMT)\n" +
					"Duration: %s\n" +
					"Passengers: \n\t%s\n\n",
					from,
					to,
					departedAt,
					arrivedAt,
					durationOfFlight,
					passengerS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new KVPair(key, val);
	}
}
