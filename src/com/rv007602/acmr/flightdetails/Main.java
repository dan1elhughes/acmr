package com.rv007602.acmr.flightdetails;

import com.rv007602.acmr.lib.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		// Create a list of flights based on the Flight id, this
		// output should include the passenger Id, relevant IATA/FAA
		// codes, the departure time, the arrival time (times to be
		// converted to HH:MM:SS format), and the flight times

		Controller controller = new Controller();

		controller.setMapper(FlightDetails_Mapper.class);
		controller.setReducer(FlightDetails_Reducer.class);

		BufferedReader input = new BufferedReader(new FileReader("input/AComp_Passenger_data.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("output/flightdetails.txt"));

		controller.setInput(input);
		controller.setOutput(output);

		controller.run();
	}
}
