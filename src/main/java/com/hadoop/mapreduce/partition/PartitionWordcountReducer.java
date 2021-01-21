package com.hadoop.mapreduce.partition;

import java.io.IOException;
import java.util.stream.StreamSupport;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.hadoop.mapreduce.bean.FlowBean;

public class PartitionWordcountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

	@Override
	protected void reduce(Text arg0, Iterable<FlowBean> arg1, Reducer<Text, FlowBean, Text, FlowBean>.Context arg2)
			throws IOException, InterruptedException {
		FlowBean result = new FlowBean();
		result.setDownFlow(0L);
		result.setSumFlow(0L);
		result.setUpFlow(0L);
		
		for (FlowBean flowBean : arg1) {
			result.setDownFlow(flowBean.getDownFlow() + result.getDownFlow());
			result.setUpFlow(flowBean.getUpFlow() + result.getUpFlow());
			result.setSumFlow(result.getDownFlow() + result.getUpFlow());
		}
		
		arg2.write(arg0, result);
	}
	
}
