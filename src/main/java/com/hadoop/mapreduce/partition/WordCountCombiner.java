package com.hadoop.mapreduce.partition;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.hadoop.mapreduce.bean.FlowBean;

/**
 * 有相应的局限性 | 比较适合 count、sum之类的操作
 * @author JayZhou
 *
 */
public class WordCountCombiner extends Reducer<Text, FlowBean, Text, FlowBean>{

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
