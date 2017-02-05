package com.rv007602.acmr;

import com.rv007602.acmrlib.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

class Main {

	public static void main(String[] args) throws Exception {
		Controller controller = new Controller();

		controller.setMapper(Mapper.class);
		controller.setReducer(Reducer.class);

		BufferedReader input = new BufferedReader(new FileReader("data.txt"));
		BufferedWriter output = new BufferedWriter(new FileWriter("output/output.txt"));
		controller.setInput(input);
		controller.setOutput(output);

		controller.run();
	}
}
