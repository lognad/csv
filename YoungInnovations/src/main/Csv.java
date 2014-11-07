package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import structures.CsvData;
import structures.CsvHashmap;

public class Csv {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String dir = System.getProperty("user.dir");
		String csvFileToRead1 = dir + "\\csv\\contracts.csv";
		String csvFileToRead2 = dir + "\\csv\\awards.csv";
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		String line = "";
		int i = 0;
		long total = 0;
		CsvData csvData;
		try {

			br1 = new BufferedReader(new FileReader(csvFileToRead1));

			while ((line = br1.readLine()) != null) {
				// split on comma(',')
				String[] csvString1 = line.split(",");
				// System.out.println(line);
				csvData = new CsvData();
				csvData.setContractname(csvString1[0]);
				csvData.setStatus(csvString1[1]);
				csvData.setBidPurchaseDeadline(csvString1[2]);
				csvData.setBidSubmissionDeadline(csvString1[3]);
				csvData.setBidOpeningDate(csvString1[4]);
				csvData.setTenderid(csvString1[5]);
				csvData.setPublicationDate(csvString1[6]);
				csvData.setPublishedIn(csvString1[7]);
				CsvHashmap.csvMap.put(i++, csvData);
			}

			// System.out.println("\n\n");
			int keyval = 0;
			br2 = new BufferedReader(new FileReader(csvFileToRead2));

			while ((line = br2.readLine()) != null) {

				// System.out.println(line);
				// split on comma(',')
				String[] csvString2 = line.split(",");

				Set<Integer> key = CsvHashmap.csvMap.keySet();
				for (int j : key) {
					CsvData data = new CsvData();
					data = CsvHashmap.csvMap.get(j);
					if (data.getContractname().equals(csvString2[0])) {
						if (data.getStatus().equals("Closed")) {
							total += Long.parseLong(csvString2[5]);
						}
						keyval = j;
						break;
					}
				}
				// csvData = new CsvData();
				csvData = CsvHashmap.csvMap.get(keyval);
				csvData.setContractDate(csvString2[1]);
				csvData.setCompletionDate(csvString2[2]);
				csvData.setAwardee(csvString2[3]);
				csvData.setAwardeeLocation(csvString2[4]);
				csvData.setAmount(csvString2[5]);
				CsvHashmap.csvMap.put(keyval, csvData);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br1 != null) {
				try {
					br1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		CsvWriter csvWriter = new CsvWriter();
		try {
			csvWriter.csvwrite();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total Amount of closed contracts: " + total);
	}

}
