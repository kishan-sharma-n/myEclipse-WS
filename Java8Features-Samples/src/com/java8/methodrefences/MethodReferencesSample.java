/**
 * 
 */
package com.java8.methodrefences;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 591241
 *
 */
public class MethodReferencesSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> coutries = new ArrayList<String>();

		coutries.add("India");
		coutries.add("England");
		coutries.add("Australia");
		coutries.add("USA");

		coutries.forEach(System.out::println);

	}

}
