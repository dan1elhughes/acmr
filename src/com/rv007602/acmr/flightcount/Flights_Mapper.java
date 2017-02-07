package com.rv007602.acmr.flightcount;

import com.rv007602.acmr.lib.KVPair;
import com.rv007602.acmr.lib.Mappable;

import java.util.ArrayList;

public class Flights_Mapper extends Mappable {
	protected ArrayList<KVPair> map(String line) {
		// Headers	: Passenger, Flight, From, To, DepartedAt, Duration
		// Line		: UES9151GS5,SQU6245R,DEN,FRA,1420564460,1049

		String[] attributes = line.split(",");

		ArrayList<KVPair> results = new ArrayList<>();

		// TODO: Check validity before adding result
		String airport = attributes[2];
		String flight = attributes[1];

		results.add(new KVPair(airport, flight));

		return results;
	}
}
