package com.hadoop.mapreduce.partition;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.hadoop.mapreduce.bean.FlowBean;

public class PartitionWordcountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context)
			throws IOException, InterruptedException {
		String[] fields = value.toString().split("\t");
		
		FlowBean flowBean = new FlowBean();
		flowBean.setUpFlow(Long.parseLong(fields[fields.length - 3]));
		flowBean.setDownFlow(Long.parseLong(fields[fields.length - 2]));
		flowBean.setSumFlow(flowBean.getUpFlow() + flowBean.getDownFlow());
		
		context.write(new Text(fields[1]), flowBean);
	}
	

}
