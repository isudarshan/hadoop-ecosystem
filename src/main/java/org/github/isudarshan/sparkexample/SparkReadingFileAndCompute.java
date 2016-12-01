/**
 * 
 */
package org.github.isudarshan.sparkexample;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

/**
 * @author sudarsan 
 * Reading a CSV file and performing map/reduce Counts each
 *         line length and finally sum length of all lines.
 * 
 */
public class SparkReadingFileAndCompute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//String localFile = "Both-28.CSV";
		String hdfsFile = "hdfs://localhost:9000/srv/api/input/states/AP/AP.CSV";
		String hdfsOutputFile = "hdfs://localhost:9000/srv/api/output/temp";

		JavaSparkContext context = new JavaSparkContext(new SparkConf()
				.setMaster("local").setAppName("SparkReadingFileAndCompute"));

		JavaRDD<String> lines = context.textFile(hdfsFile);
		JavaRDD<Integer> lineLengths = lines
				.map(new Function<String, Integer>() {

					private static final long serialVersionUID = 5006856923114629114L;

					public Integer call(String line) throws Exception {
						int lineLength = line.length();
						System.out.println("Line : " + line + "\tLength : "
								+ lineLength);
						return lineLength;
					}
				});

		int totalLength = lineLengths
				.reduce(new Function2<Integer, Integer, Integer>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 8095815835454214779L;

					public Integer call(Integer arg0, Integer arg1)
							throws Exception {
						return arg0 + arg1;
					}
				});

		System.out.println("Total Line Count : " + lineLengths.count());
		System.out.println("Total length of all lines : " + totalLength);

		lineLengths.saveAsTextFile(hdfsOutputFile + "/lengthofalllines.txt");

		context.close();
	}

}
