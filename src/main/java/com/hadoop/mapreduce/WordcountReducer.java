package com.hadoop.mapreduce;

import java.io.IOException;
import java.util.stream.StreamSupport;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2) throws IOException, InterruptedException {
		// 将 Iterable 转换为 stream
		Integer reduce = StreamSupport.stream(arg1.spliterator(), false).map(item -> item.get()).reduce(0, (a,b)->a+b);
		
		IntWritable intWritable = new IntWritable(reduce);
		
		arg2.write(arg0, intWritable);
	}
	
	
}
