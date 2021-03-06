# 疑问

## hdfs 块大小

![image-20210115152121615](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210115152121615.png)



- 减少硬盘寻道时间(disk seek time)

假如数据块设置过少，那需要读取的数据块就比较多，由于数据块在硬盘上非连续存储，所以随机寻址较慢，读越多的数据块就增大了总的硬盘寻道时间。当硬盘寻道时间比io时间还要长的多时，那么硬盘寻道时间就成了系统的一个瓶颈。合适的块大小有助于减少硬盘寻道时间，提高系统吞吐量。传输一个由多个块的组成的文件取决于磁盘传输速率。随着新一代磁盘去东区传输速率的提升，块的大小将会被设置的更大

- 减少Namenode内存消耗

对于HDFS，他只有一个Namenode节点，他的内存相对于Datanode来说，是极其有限的。然而，namenode需要在其内存FSImage文件中中记录在Datanode中的数据块信息，假如数据块大小设置过小，而需要维护的数据块信息就会过多，那Namenode的内存可能就受限

- 问题分解问题

块的大小太大的话，一个map任务处理一个块，那任务数就变少了，作业运行速度也就变慢了







# MapReduce 概述

## MapReduce优缺点

1. 易于编程
2. 良好扩展性
3. 高容错性
4. **适用于海量数据的计算**

## MapReduce核心思想

![image-20210111145214572](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210111145214572.png)

# Hadoop 序列化

## Hadoop 序列化优点

1. 紧凑
2. 快速
3. 可扩展性
4. 互操作：支持多语言的交互

# MapReduce 框架原理

MapReduce 是分布式运算框架

缺点：

不能处理流数据

与传统数据库相比 计算速度较慢 

不擅长DAG有向计算

## 数据切片与MapTask并行度决定机制

- **数据块：**Block是HDFS物理上把数据分成一块一块。
- **数据切片：**数据切片只是在逻辑上对输入进行分片，并不会在磁盘上将其切分成片进行存储。

![image-20210111161724273](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210111161724273.png)



![image-20210119145736893](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210119145736893.png)

# CombineTextInputFormat

针对小文件过多的情况，将多个小文件从逻辑上规划到一个切片中，这样可以统一交给MapTask

生成切片的过程包括：虚拟存储过程和切片过程 两部

![image-20210112142011002](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210112142011002.png)

## FileInputFormat 实现类

- TextInputFormat ： 默认FileInputFormat实现类
- KeyValueTextInputFormat：每一行均为一条记录，被分隔符分割为 key、value
- NLineInputFormat







## MapReduce工作流程

![image-20210118162742152](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210118162742152.png)



![image-20210118162829801](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210118162829801.png)



![image-20210119170840590](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210119170840590.png)



![image-20210119171415615](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210119171415615.png)



![image-20210119173738072](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210119173738072.png)



![image-20210120103422330](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210120103422330.png)
$$
分组排序：指的是在reduceTask 中进行排序操作
$$

> 因为map的结果数据中key是bean，不是普通数据类型，所以需要使用自定义的比较器来分组，就使用bean中的订单号来比较。

```java
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
```




## MapReduce Join 操作

![image-20210115101544006](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210115101544006.png)

> 注意输出先后顺序

## ReduceTask工作机制

![image-20210115102949131](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210115102949131.png)



![image-20210120161719403](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210120161719403.png)



