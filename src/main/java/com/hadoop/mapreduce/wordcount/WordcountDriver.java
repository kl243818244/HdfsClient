package com.hadoop.mapreduce.wordcount;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordcountDriver {

	public static void main(String[] args) {
		try {
			Configuration configuration = new Configuration();
			
//			configuration.set("mapreduce.framework.name", "local");
//			configuration.set("fs.defaultFS", "hdfs://192.168.127.129:9000");
			
//			System.setProperty("HADOOP_USER_NAME", "hadoop");
			
//			configuration.set("fs.defaultFS", "file:///");

			Job job = Job.getInstance(configuration);

			job.setJarByClass(WordcountDriver.class);

			job.setMapperClass(WordcountMapper.class);
			job.setReducerClass(WordcountReducer.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);

			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);

			FileInputFormat.setInputPaths(job, new Path("F:\\weining\\BigData\\backfile\\hello.txt"));
			FileOutputFormat.setOutputPath(job, new Path("F:\\weining\\BigData\\backfile\\out"));

			boolean waitForCompletion = job.waitForCompletion(true);

			System.out.println("结束：" + waitForCompletion);

			System.exit(waitForCompletion ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
