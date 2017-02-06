package com.rv007602.acmr.passengernumbers;

import com.rv007602.acmr.lib.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

class Main {

	public static void main(String[] args) throws Exception {
		Controller controller = new Controller();

		controller.setMapper(Mapper.class);
		controller.setReducer(Reducer.class);

		BufferedReader input = new BufferedReader(new FileReader("input/AComp_Passenger_data.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("output/passenger-numbers.txt"));
		controller.setInput(input);
		controller.setOutput(output);

		controller.run();
	}
}
