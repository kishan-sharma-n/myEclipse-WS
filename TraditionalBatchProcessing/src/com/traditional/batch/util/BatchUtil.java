package com.traditional.batch.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BatchUtil {
	
	public static List<String> extractCsvValues (String fileContent) {
		
		List<String> extractedValues = new ArrayList<>(); 
		StringTokenizer stringTokenizer = new StringTokenizer(fileContent, ",");
		
		while(stringTokenizer.hasMoreTokens()) {
			extractedValues.add(stringTokenizer.nextToken());
		}
		
		//System.out.println("person information in BatchUtil.extractCsvValues()--->"+extractedValues);
		
		return extractedValues;
	}
	
	public static List<String> readFile(String fileName) {

		List<String> fileContents = new ArrayList<>();
		BufferedReader br = null;
		String sCurrentLine;

		try {

			br = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				fileContents.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return fileContents;

	}

}
