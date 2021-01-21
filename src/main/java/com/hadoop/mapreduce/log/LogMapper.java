package com.hadoop.mapreduce.log;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String str = value.toString();

		if (str.length() > 100) {
			context.getCounter("-----------------map-----------------", "-----------------true-----------------").increment(1);

			context.write(new Text(str), NullWritable.get());
		} else {
			context.getCounter("-----------------map-----------------", "-----------------false-----------------").increment(1);
		}
	}

}
