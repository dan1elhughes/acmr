package com.rv007602.acmr.flightdistance;

import com.rv007602.acmr.Validate;
import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Reducible;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Distance_Reducer extends Reducible {
	@Override
	protected KVPair reduce(String key, ArrayList<String> values) {
		// Generic method to convert from/to airport codes to total nautical miles.
		// When given a flight, i.e. all the from/to are the same, returns just the
		// total for that flight. When given a passenger, i.e. from/to are different,
		// returns the total for all journeys for that passenger.

		Set<String> uniqueValues = new HashSet<>(values);
		int totalNauticalMiles = 0;

		for (String combo : uniqueValues) {

			String[] airports = combo.split("\\|");

			String from = airports[0];
			String to = airports[1];

			float[] fromPos = new float[2];
			float[] toPos = new float[2];

			try (BufferedReader br = new BufferedReader(new FileReader("input/Top30_airports_LatLong.csv"))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] split = line.split(",");
					if (!split[0].equals(line)) {
						String code = split[1];
						if (Validate.airport(code)) {
							String lat = split[2];
							String lng = split[3];

							if (Validate.latlng(lat) && Validate.latlng(lng)) {
								if (code.equals(from)) {
									fromPos[0] = Float.parseFloat(lat);
									fromPos[1] = Float.parseFloat(lng);
								} else if (code.equals(to)) {
									toPos[0] = Float.parseFloat(lat);
									toPos[1] = Float.parseFloat(lng);
								}
							}

						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			double metres = this.latLongToMetres(fromPos[0], fromPos[1], toPos[0], toPos[1]);

			int nauticalMiles = (int) metres / 1852;

			totalNauticalMiles += nauticalMiles;
		}

		return new KVPair(key, String.valueOf(totalNauticalMiles));
	}

	/**
	 * Converts two lat/long pairs to metres using the Haversine formula.
	 * Adapted from http://stackoverflow.com/a/11172685.
	 *
	 * @param lat1 The latitude component of the first coordinate.
	 * @param lon1 The longitude component of the first coordinate.
	 * @param lat2 The latitude component of the second coordinate.
	 * @param lon2 The longitude component of the second coordinate.
	 * @return The distance between the coordinates in metres.
	 */
	private double latLongToMetres(float lat1, float lon1, float lat2, float lon2) {
		double R = 6378.137; // Radius of earth in KM
		double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
		double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
				Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
						Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		return d * 1000; // meters
	}
}
