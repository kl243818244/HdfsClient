package com.hadoop.mapreduce.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import com.hadoop.mapreduce.bean.FlowBean;

public class CustomPartitioner extends Partitioner<Text, FlowBean> {

	@Override
	public int getPartition(Text key, FlowBean value, int numPartitions) {
		String substring = key.toString().substring(0, 3);
		
		if(substring.equals("136")) {
			return 0;
		}
		
		if(substring.equals("137")) {
			return 1;
		}
		
		if(substring.equals("138")) {
			return 2;
		}
		
		if(substring.equals("139")) {
			return 3;
		}
		return 4;
	}

}
