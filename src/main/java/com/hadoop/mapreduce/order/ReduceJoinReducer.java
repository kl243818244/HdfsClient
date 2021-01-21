package com.hadoop.mapreduce.order;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.hadoop.mapreduce.bean.TableBean;

public class ReduceJoinReducer extends Reducer<Text, TableBean, TableBean, NullWritable> {

	@Override
	protected void reduce(Text arg0, Iterable<TableBean> arg1,
			Reducer<Text, TableBean, TableBean, NullWritable>.Context arg2) throws IOException, InterruptedException {
		// 1准备存储订单的集合
		ArrayList<TableBean> orderBeans = new ArrayList<>();

		// 2 准备bean对象
		TableBean pdBean = new TableBean();

		for (TableBean bean : arg1) {

			if ("order".equals(bean.getFlag())) {// 订单表

				// 拷贝传递过来的每条订单数据到集合中
				TableBean orderBean = new TableBean();

				try {
					BeanUtils.copyProperties(orderBean, bean);
				} catch (Exception e) {
					e.printStackTrace();
				}

				orderBeans.add(orderBean);
			} else {// 产品表

				try {
					// 拷贝传递过来的产品表到内存中
					BeanUtils.copyProperties(pdBean, bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// 3 表的拼接
		for (TableBean bean : orderBeans) {

			bean.setPname(pdBean.getPname());

			// 4 数据写出去
			arg2.write(bean, NullWritable.get());
		}

	}
}
