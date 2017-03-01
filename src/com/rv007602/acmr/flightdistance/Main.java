package com.rv007602.acmr.flightdistance;

import com.rv007602.acmr.lib.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		// Calculate the line-of-sight (nautical) miles for each flight and the total travelled by each passenger

		Controller controller = new Controller();

		controller.setMapper(Distance_Mapper.class);
		controller.setReducer(Distance_Reducer.class);

		BufferedReader input = new BufferedReader(new FileReader("input/AComp_Passenger_data.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("output/distances.txt"));

		controller.setInput(input);
		controller.setOutput(output);

		controller.run();
	}
}
