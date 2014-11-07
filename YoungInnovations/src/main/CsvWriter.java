package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import structures.CsvData;
import structures.CsvHashmap;

public class CsvWriter {
	BufferedWriter writer = null;
	CsvData csvData;

	public void csvwrite() throws IOException {
		File file = new File(System.getProperty("user.dir")
				+ "\\csv\\final.csv");
		FileWriter fw = new FileWriter(file);
		writer = new BufferedWriter(fw);
		if (!file.exists()) {
			file.createNewFile();
			writer.write("contractname,");
			writer.write("status,");
			writer.write("bidPurchaseDeadline");
			writer.write("bidSubmissionDeadline");
			writer.write("bidOpeningDate");
			writer.write("tenderid");
			writer.write("publicationDate");
			writer.write("publishedIn");
			writer.write("contractDate");
			writer.write("completionDate");
			writer.write("awardee");
			writer.write("awardeeLocation");
			writer.write("Amount");
			writer.newLine();
		}

		Set<Integer> key = CsvHashmap.csvMap.keySet();
		for (int i : key) {
			csvData = CsvHashmap.csvMap.get(i);
//			System.out.println(csvData.getPublishedIn());
			writer.write(csvData.getContractname() + ",");
			writer.write(csvData.getStatus() + ",");
			writer.write(csvData.getBidPurchaseDeadline() + ",");
			writer.write(csvData.getBidSubmissionDeadline() + ",");
			writer.write(csvData.getBidOpeningDate() + ",");
			writer.write(csvData.getTenderid() + ",");
			writer.write(csvData.getPublicationDate() + ",");
			writer.write(csvData.getPublishedIn() + ",");
			writer.write(csvData.getContractDate() + ",");
			writer.write(csvData.getCompletionDate() + ",");
			writer.write(csvData.getAwardee() + ",");
			writer.write(csvData.getAwardeeLocation() + ",");
			writer.write(csvData.getAmount() + ",");
			writer.newLine();
		}

		writer.flush();
		writer.close();
		fw.close();
	}
}
