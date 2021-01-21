package com.hadoop.mapreduce.groupcomparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.hadoop.mapreduce.bean.OrderBean;

public class OrderSortComparator extends WritableComparator{
	protected OrderSortComparator() {
		super(OrderBean.class,true);
	}
	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		OrderBean aBean = (OrderBean) a;
		OrderBean bBean = (OrderBean) b;
		
		int result;
		if (aBean.getOrderId() > bBean.getOrderId()) {
			result = 1;
		} else if (aBean.getOrderId() < bBean.getOrderId()) {
			result = -1;
		} else {
			result = 0;
		}

		return result;
	}
}
