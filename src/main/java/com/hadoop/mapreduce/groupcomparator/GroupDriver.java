package com.hadoop.mapreduce.groupcomparator;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.hadoop.mapreduce.bean.FlowBean;
import com.hadoop.mapreduce.bean.OrderBean;

public class GroupDriver {

	public static void main(String[] args) {
		try {
			Configuration configuration = new Configuration();
			
			Job job = Job.getInstance(configuration);
			
			job.setJarByClass(GroupDriver.class);

			job.setMapperClass(GroupMapper.class);
			job.setReducerClass(GroupReducer.class);

			job.setMapOutputKeyClass(OrderBean.class);
			job.setMapOutputValueClass(NullWritable.class);

			job.setOutputKeyClass(OrderBean.class);
			job.setOutputValueClass(NullWritable.class);
			
			job.setGroupingComparatorClass(OrderSortComparator.class);

			FileInputFormat.setInputPaths(job, new Path("F:\\weining\\BigData\\backfile\\GroupingComparator.txt"));
			FileOutputFormat.setOutputPath(job, new Path("F:\\weining\\BigData\\backfile\\comparator"));

			boolean waitForCompletion = job.waitForCompletion(true);
			
			System.out.println("结束：" + waitForCompletion);

			System.exit(waitForCompletion ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
