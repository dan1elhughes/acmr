package com.rv007602.acmr.flightdistance;

import com.rv007602.acmr.lib.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		// Calculate the line-of-sight (nautical) miles for each flight and the total travelled by each passenger
		Main.step1();
	}

	public static void step1() throws Exception {
		// Build a list of nautical mile distances for all flights
		Controller controller = new Controller();

		controller.setMapper(FlightDistance_Mapper.class);
		controller.setReducer(FlightDistance_Reducer.class);

		BufferedReader input = new BufferedReader(new FileReader("input/AComp_Passenger_data_no_error.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("output/flights.txt"));

		controller.setInput(input);
		controller.setOutput(output);

		controller.run();
	}
}
