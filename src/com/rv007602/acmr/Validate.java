package com.rv007602.acmr;

public class Validate {

	private static final String VALID_FLIGHT = "[A-Z]{3}[0-9]{4}[A-Z]";
	private static final String VALID_AIRPORT = "[A-Z]{3}";
	private static final String VALID_PASSENGER = "[A-Z]{3}[0-9]{4}[A-Z]{2}[0-9]";
	private static final String VALID_TIME = "[0-9]{10}";
	private static final String VALID_DURATION = "[0-9]{1,4}";
	private static final String VALID_AIRPORTNAME = "[A-Z]{3,20}";
	private static final String VALID_LATLNG = "-?\\d+\\.\\d+";

	private static void failed(String input, String context) {
		System.out.println(String.format("'%s' failed validation for %s", input, context));
	}

	public static boolean flight(String input) {
		if (input.matches(VALID_FLIGHT)) {
			return true;
		} else {
			Validate.failed(input, "VALID_FLIGHT");
			return false;
		}
	}

	public static boolean airport(String input) {
		if (input.matches(VALID_AIRPORT)) {
			return true;
		} else {
			Validate.failed(input, "VALID_AIRPORT");
			return false;
		}
	}

	public static boolean passenger(String input) {
		if (input.matches(VALID_PASSENGER)) {
			return true;
		} else {
			Validate.failed(input, "VALID_PASSENGER");
			return false;
		}
	}

	public static boolean time(String input) {
		if (input.matches(VALID_TIME)) {
			return true;
		} else {
			Validate.failed(input, "VALID_TIME");
			return false;
		}
	}

	public static boolean duration(String input) {
		if (input.matches(VALID_DURATION)) {
			return true;
		} else {
			Validate.failed(input, "VALID_DURATION");
			return false;
		}
	}

	public static boolean airport_name(String input) {
		if (input.matches(VALID_AIRPORTNAME)) {
			return true;
		} else {
			Validate.failed(input, "VALID_AIRPORTNAME");
			return false;
		}
	}

	public static boolean latlng(String input) {
		if (input.matches(VALID_LATLNG)) {
			return true;
		} else {
			Validate.failed(input, "VALID_LATLNG");
			return false;
		}
	}
}
