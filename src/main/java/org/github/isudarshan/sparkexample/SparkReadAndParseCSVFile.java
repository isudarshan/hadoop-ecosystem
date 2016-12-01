package org.github.isudarshan.sparkexample;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * @author sudarsan 
 * Reading and parsing CSV file from local/HDFS
 * 
 */
public class SparkReadAndParseCSVFile {

	public static void main(String[] args) {
		
		//String localFile = "Both-28.CSV";
		String hdfsInputFile = "hdfs://localhost:9000/srv/api/input/states/AP/AP.CSV";
		
		JavaSparkContext context = new JavaSparkContext(new SparkConf()
				.setAppName("SparkReadAndParseCSVFile").setMaster("local"));

		JavaRDD<Record> rddRecords = context.textFile(hdfsInputFile).map(
				new Function<String, Record>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 8364146113524847758L;

					public Record call(String line) throws Exception {
						String[] fields = line.split(",");
						Record record = new Record(fields[0], fields[1],
								fields[2], fields[3], fields[4], fields[5],
								fields[6]);
						return record;
					}
				});

		List<Record> recordList = rddRecords.collect();

		context.close();

		for (Record record : recordList) {
			System.out.println(record);
		}

		System.out.println("Total Size : " + recordList.size());
		
		

	}
}
