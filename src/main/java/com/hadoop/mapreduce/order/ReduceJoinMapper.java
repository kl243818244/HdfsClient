package com.hadoop.mapreduce.order;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import com.hadoop.mapreduce.bean.TableBean;

public class ReduceJoinMapper extends Mapper<LongWritable, Text, Text, TableBean> {

	private String fileName = "";

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, TableBean>.Context context)
			throws IOException, InterruptedException {
		FileSplit inputSplit = (FileSplit) context.getInputSplit();

		// 获取文件名字 赋值给公共变量
		this.fileName = inputSplit.getPath().getName();
	}

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, TableBean>.Context context)
			throws IOException, InterruptedException {
		// order 、 pd
		String[] fields = value.toString().split("\t");

		TableBean tableBean = new TableBean();

		if (fileName.startsWith("order")) {
			// 2.2 封装bean对象
			tableBean.setOrder_id(fields[0]);
			tableBean.setP_id(fields[1]);
			tableBean.setAmount(Integer.parseInt(fields[2]));
			tableBean.setPname("");
			tableBean.setFlag("order");

			context.write(new Text(fields[1]), tableBean);
		}

		if (fileName.startsWith("pd")) {
			// 2.4 封装bean对象
			tableBean.setP_id(fields[0]);
			tableBean.setPname(fields[1]);
			tableBean.setFlag("pd");
			tableBean.setAmount(0);
			tableBean.setOrder_id("");

			context.write(new Text(fields[0]), tableBean);
		}
	}

}
