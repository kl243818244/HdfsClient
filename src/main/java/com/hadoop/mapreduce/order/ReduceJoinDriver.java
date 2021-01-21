package com.hadoop.mapreduce.order;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.hadoop.mapreduce.bean.TableBean;


public class ReduceJoinDriver {

	public static void main(String[] args) {
		try {
			Path inputPath = new Path("F:\\weining\\BigData\\backfile\\reducefile");
			
			Path outputPath = new Path("F:\\weining\\BigData\\backfile\\reducefile\\out");
			
			Configuration configuration = new Configuration();
			
			FileSystem fs=FileSystem.get(configuration);
			
			if (fs.exists(outputPath)) {
				fs.delete(outputPath, true);
			}
			
			Job job = Job.getInstance(configuration);
			
			job.setJarByClass(ReduceJoinDriver.class);

			job.setMapperClass(ReduceJoinMapper.class);
			job.setReducerClass(ReduceJoinReducer.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(TableBean.class);

			job.setOutputKeyClass(TableBean.class);
			job.setOutputValueClass(NullWritable.class);
			
			FileInputFormat.setInputPaths(job, inputPath);
			FileOutputFormat.setOutputPath(job, outputPath);

			boolean waitForCompletion = job.waitForCompletion(true);
			
			System.out.println("结束：" + waitForCompletion);

			System.exit(waitForCompletion ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
