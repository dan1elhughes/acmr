package com.rv007602.acmr;

import com.rv007602.acmrlib.KVPair;
import com.rv007602.acmrlib.Mappable;

import java.util.ArrayList;

public class Mapper extends Mappable {

	public ArrayList<KVPair> map(String line) {
		String[] words = line.split(" ");
		ArrayList<KVPair> results = new ArrayList<>();

		for (String word : words) {
			word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

			if (word.length() != 0) {
				results.add(new KVPair(word, String.valueOf(1)));
			}
		}

		return results;
	}
}
