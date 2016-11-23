package com.traditional.batch.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.traditional.batch.util.BatchUtil;
import com.traditional.batch.util.mysql.DBUtil;

public class PersonInformationBatchProcessing {
	
	private static List<PersonVO> fileContents = null;
	
	public void loadPersonInfor (String filePath, int batchSize) {
		
		Connection connection = null;
		List<String> fileContentsRaw = null;
		Iterator<String> iterator = null;
		fileContents = new ArrayList<>();

		try {

			connection = DBUtil.getConnection();
			fileContentsRaw = BatchUtil.readFile(filePath);
			iterator = fileContentsRaw.iterator();
			
			while (iterator.hasNext()) {
				fileContents.add(buildPersonObject(iterator.next()));
			}
			
			System.out.println("fileContents size--->"+fileContents.size());
			
			loadPersonData(connection, fileContents, batchSize);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(-1);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-2);
			}
		}
		
	}

	
	private PersonVO buildPersonObject(String fileContent) {

		PersonVO personVO = new PersonVO();
		List<String> extractedInfo = null;

		extractedInfo = BatchUtil.extractCsvValues(fileContent);

		personVO.setName(extractedInfo.get(0));
		personVO.setAge(Integer.parseInt(extractedInfo.get(1)));
		//personVO.setLastUpdate(new Date(extractedInfo.get(2)));

		return personVO;

	}

	private void loadPersonData(Connection connection, List<PersonVO> fileContents, int batchSize) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		Iterator<PersonVO> iterator = null;
		PersonVO personVO = null;
		int counter = 1;
		
		try {
			
			preparedStatement = connection.prepareStatement("insert into PERSON (NAME, age, LASTUPDATE) values (?,?,?)");
			
			iterator = fileContents.iterator();
			
			while (iterator.hasNext()) {
				personVO = iterator.next();
				preparedStatement.setString(1, personVO.getName());
				preparedStatement.setInt(2, personVO.getAge());
				preparedStatement.setDate(3, new java.sql.Date(personVO.getLastUpdate().getTime()));
				preparedStatement.addBatch();
				
				counter++;
				
				if(counter == batchSize) {
					System.out.println("Executing batch of inserts");
					preparedStatement.executeBatch();
					//connection.commit();
					counter = 0;
				}
			}
			
			if(counter>0) {
				preparedStatement.executeBatch();
				//connection.commit();
			}
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			//connection.rollback();
			throw sqlException;
		} finally {
			preparedStatement.close();
		}
		
	}
	
}
