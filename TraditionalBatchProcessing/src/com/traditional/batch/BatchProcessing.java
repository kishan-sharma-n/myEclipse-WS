package com.traditional.batch;

import java.util.Date;

import com.traditional.batch.person.PersonInformationBatchProcessing;

public class BatchProcessing {

	public static void main(String[] args) {
		
		Date startDate = new Date();
		Date endDate = null;
		
		System.out.println("File to be loaded--->"+args[0]);
		
		PersonInformationBatchProcessing personInformationBatchProcessing = new PersonInformationBatchProcessing();
		personInformationBatchProcessing.loadPersonInfor(args[0], Integer.parseInt(args[1]));
		
		endDate = new Date();
		System.out.println("Time taken to load in seconds--->"+((endDate.getTime()-startDate.getTime())/1000));

	}

}
