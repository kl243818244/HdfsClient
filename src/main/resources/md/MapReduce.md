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

## 数据切片与MapTask并行度决定机制

- **数据块：**Block是HDFS物理上把数据分成一块一块。
- **数据切片：**数据切片只是在逻辑上对输入进行分片，并不会在磁盘上将其切分成片进行存储。

![image-20210111161724273](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210111161724273.png)

# CombineTextInputFormat

针对小文件过多的情况，将多个小文件从逻辑上规划到一个切片中，这样可以统一交给MapTask

生成切片的过程包括：虚拟存储过程和切片过程 两部

![image-20210112142011002](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210112142011002.png)

## FileInputFormat 实现类

- TextInputFormat ： 默认FileInputFormat实现类
- KeyValueTextInputFormat：每一行均为一条记录，被分隔符分割为 key、value
- NLineInputFormat







## MapReduce工作流程

![image-20210111172454841](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210111172454841.png)



## MapReduce Join 操作

![image-20210115101544006](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210115101544006.png)

> 注意输出先后顺序

## ReduceTask工作机制

![image-20210115102949131](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210115102949131.png)



