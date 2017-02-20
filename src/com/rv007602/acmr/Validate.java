package com.rv007602.acmr;

public class Validate {

	private static final String VALID_FLIGHT = "[A-Z]{3}[0-9]{4}[A-Z]";
	private static final String VALID_AIRPORT = "[A-Z]{3}";
	private static final String VALID_PASSENGER = "[A-Z]{3}[0-9]{4}[A-Z]{2}[0-9]";
	private static final String VALID_TIME = "[0-9]{10}";
	private static final String VALID_DURATION = "[0-9]{1,4}";
	private static final String VALID_AIRPORTNAME = "[A-Z]{3,20}";
	private static final String VALID_LATLNG = "-?\\d+\\.\\d+";

	public static boolean flight(String input) {
		return input.matches(VALID_FLIGHT);
	}

	public static boolean airport(String input) {
		return input.matches(VALID_AIRPORT);
	}

	public static boolean passenger(String input) {
		return input.matches(VALID_PASSENGER);
	}

	public static boolean time(String input) {
		return input.matches(VALID_TIME);
	}

	public static boolean duration(String input) {
		return input.matches(VALID_DURATION);
	}

	public static boolean airport_name(String input) {
		return input.matches(VALID_AIRPORTNAME);
	}

	public static boolean latlng(String input) {
		return input.matches(VALID_LATLNG);
	}
}
