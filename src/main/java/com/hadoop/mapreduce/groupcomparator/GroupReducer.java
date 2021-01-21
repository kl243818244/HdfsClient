package com.hadoop.mapreduce.groupcomparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.hadoop.mapreduce.bean.OrderBean;

public class GroupReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable> {

//	private static final int TopN=2;
	
	@Override
	protected void reduce(OrderBean arg0, Iterable<NullWritable> arg1,
			Reducer<OrderBean, NullWritable, OrderBean, NullWritable>.Context arg2)
			throws IOException, InterruptedException {
//		int count=0;
/*		for (NullWritable value:arg1) {
			if(count<TopN) {
				arg2.write(arg0, NullWritable.get());
				count++;
			}
		}*/
		
		// arg0 会实时进行更新
/*		for (Iterator iterator = arg1.iterator(); iterator.hasNext();) {
			NullWritable type = (NullWritable) iterator.next();
			System.out.println(arg0);
		}*/
		
		arg2.write(arg0, NullWritable.get());
	}
	
	
	
	/*public static void main(String[] args) {
		List<Integer> re = new ArrayList<>();

        re.add(1);
        re.add(2);
        re.add(6);
        re.add(5);
        re.add(8);
        re.add(8);
        re.add(4);

        Collections.sort(re, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
            //下面这么写，结果是降序
                if(o1 < o2){
                    return 1;
                }else if(o1 > o2){
                    return -1;
                }
                return -1;
            }

        });

        System.out.println(re);
	}*/
	
	
}
