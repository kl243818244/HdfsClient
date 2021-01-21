package com.hadoop.mapreduce.partition;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.hadoop.mapreduce.bean.FlowBean;

public class PartitionWordcountDriver {

	public static void main(String[] args) {
		try {
			Configuration configuration = new Configuration();
			
//			configuration.set("mapreduce.framework.name", "local");
//			configuration.set("fs.defaultFS", "hdfs://192.168.127.129:9000");
			
//			System.setProperty("HADOOP_USER_NAME", "hadoop");
			
//			configuration.set("fs.defaultFS", "file:///");
			
			Job job = Job.getInstance(configuration);
			
			// 设置 fileinputformat 类型为 combinetextinputformat ==> 合并小型文件的类型
//			job.setInputFormatClass(CombineTextInputFormat.class);
//			CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);
			
//			job.setPartitionerClass(CustomPartitioner.class);
//			job.setNumReduceTasks(5);
			
			job.setCombinerClass(WordCountCombiner.class);

			job.setJarByClass(PartitionWordcountDriver.class);

			job.setMapperClass(PartitionWordcountMapper.class);
			job.setReducerClass(PartitionWordcountReducer.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(FlowBean.class);

			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(FlowBean.class);

			FileInputFormat.setInputPaths(job, new Path("F:\\weining\\BigData\\backfile\\phone_data.txt"));
			FileOutputFormat.setOutputPath(job, new Path("F:\\weining\\BigData\\backfile\\outcombine"));

			boolean waitForCompletion = job.waitForCompletion(true);
			
			System.out.println("结束：" + waitForCompletion);

			System.exit(waitForCompletion ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
