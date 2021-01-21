package com.hadoop.mapreduce.groupcomparator;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.hadoop.mapreduce.bean.OrderBean;

public class GroupMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, OrderBean, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String[] split = value.toString().split("\t");
		
		OrderBean orderBean = new OrderBean();
		orderBean.setOrderId(Integer.valueOf(split[0]));
		orderBean.setPrice(Double.valueOf(split[2]));
		
		context.write(orderBean, NullWritable.get());
	}
}
