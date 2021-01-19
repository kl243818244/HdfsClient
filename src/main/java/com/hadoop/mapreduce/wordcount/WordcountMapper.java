package com.hadoop.mapreduce.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	Text MrKey = new Text();

	// 设置初始值 | 和Flink的 keyBy非常相似
	IntWritable MrValue = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {

		String string = value.toString();

		String[] split = string.split(" ");

		for (String string2 : split) {
			MrKey.set(string2);

			context.write(MrKey, MrValue);
		}
	}

}
