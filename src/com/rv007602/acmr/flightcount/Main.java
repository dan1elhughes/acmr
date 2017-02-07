package com.rv007602.acmr.flightcount;

import com.rv007602.acmr.lib.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

class Main {

	public static void main(String[] args) throws Exception {
		Main.step1();
		Main.step2();
		Main.step3();
	}

	// Lists all existing airports as having a total flight count of 0.
	public static void step1() throws Exception {
		Controller controller = new Controller();

		controller.setMapper(Airports_Mapper.class);
		controller.setReducer(Airports_Reducer.class);

		BufferedReader input = new BufferedReader(new FileReader("input/Top30_airports_LatLong.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("output/flightcount.txt"));
		controller.setInput(input);
		controller.setOutput(output);

		controller.run();
	}

	// Appends the zero-totals with the real values from the data.
	public static void step2() throws Exception {
		Controller controller = new Controller();

		controller.setMapper(Flights_Mapper.class);
		controller.setReducer(Flights_Reducer.class);

		BufferedReader input = new BufferedReader(new FileReader("input/AComp_Passenger_data_no_error.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("output/flightcount.txt", true));
		controller.setInput(input);
		controller.setOutput(output);

		controller.run();
	}

	// Combines the totals.
	public static void step3() throws Exception {
		Controller controller = new Controller();

		controller.setMapper(Totals_Mapper.class);
		controller.setReducer(Totals_Reducer.class);

		BufferedReader input = new BufferedReader(new FileReader("output/flightcount.txt"));
		BufferedWriter output = new BufferedWriter(new FileWriter("output/flightcount-total.txt"));
		controller.setInput(input);
		controller.setOutput(output);

		controller.run();
	}

}
