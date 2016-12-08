/**
 * 
 */
package org.github.isudarshan.sparkexample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;

/**
 * @author Sudarsan
 * 
 */
public class SparkCSVCleanup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String localCSVFile = "AP_RAW_SOURCE.CSV";
		JavaSparkContext context = new JavaSparkContext(new SparkConf()
				.setMaster("local").setAppName("SparkCSVCleanup"));

		JavaRDD<Record> lines = context.textFile(localCSVFile).map(
				new GetLines());

		List<Record> records = lines.collect();

		System.out.println("------> Started writing Artifacts to CSV <------");
		writeToCSVFile(records);
		System.out
				.println("------> Completed writing Artifacts to CSV <------");

		context.close();

	}

	static void writeToCSVFile(List<Record> records) {
		List<Record> finalList = records.subList(2, records.size());
		CSVWriter csvWriter = null;
		try {
			csvWriter = new CSVWriter(
					new FileWriter(new File("AP_ARCHIVE.csv")));
			ColumnPositionMappingStrategy<Record> columnPositionMappingStrategy = new ColumnPositionMappingStrategy<Record>();
			columnPositionMappingStrategy.setType(Record.class);
			String[] columnMapping = new String[] { "stCode", "dtCode",
					"dtName", "sdtCode", "sdtName", "tvCode", "name" };
			columnPositionMappingStrategy.setColumnMapping(columnMapping);

			BeanToCsv<Record> beanToCsv = new BeanToCsv<Record>();
			beanToCsv
					.write(columnPositionMappingStrategy, csvWriter, finalList);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (csvWriter != null) {
				try {
					csvWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}

class GetLines implements Function<String, Record> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5855725687467793506L;

	@Override
	public Record call(String line) throws Exception {
		String trimmedLine = line.replaceAll("\"", "").replaceAll("\'", "");
		String[] fields = trimmedLine.split(",");
		Record record = new Record(fields[0], fields[1], fields[2], fields[3],
				fields[4], fields[5], fields[6]);
		return record;
	}

}