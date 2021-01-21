package com.hadoop.mapreduce.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBean implements WritableComparable<OrderBean> {
	private Integer orderId;
	
	private Double price;

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(orderId);
		out.writeDouble(price);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.orderId = in.readInt();
		this.price = in.readDouble();
	}

	@Override
	public int compareTo(OrderBean o) {
		// 升序
		if(this.orderId > o.orderId) {
			return 1;
		}
		
		if(this.orderId < o.orderId) {
			return -1;
		}
		
		// 降序
		return this.price > o.price ? -1 : 1;
	}
}
