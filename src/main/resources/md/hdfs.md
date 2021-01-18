# 疑问

## hdfs 块大小

![image-20210115152121615](C:\Users\JayZhou\AppData\Roaming\Typora\typora-user-images\image-20210115152121615.png)



- 减少硬盘寻道时间(disk seek time)

假如数据块设置过少，那需要读取的数据块就比较多，由于数据块在硬盘上非连续存储，所以随机寻址较慢，读越多的数据块就增大了总的硬盘寻道时间。当硬盘寻道时间比io时间还要长的多时，那么硬盘寻道时间就成了系统的一个瓶颈。合适的块大小有助于减少硬盘寻道时间，提高系统吞吐量。传输一个由多个块的组成的文件取决于磁盘传输速率。随着新一代磁盘去东区传输速率的提升，块的大小将会被设置的更大

- 减少Namenode内存消耗

对于HDFS，他只有一个Namenode节点，他的内存相对于Datanode来说，是极其有限的。然而，namenode需要在其内存FSImage文件中中记录在Datanode中的数据块信息，假如数据块大小设置过小，而需要维护的数据块信息就会过多，那Namenode的内存可能就受限

- 问题分解问题

块的大小太大的话，一个map任务处理一个块，那任务数就变少了，作业运行速度也就变慢了



# 面试重点：

![image-20210118110148099](images\image-20210118110148099.png)

大致流程： 向NN 申请传输文件 =》NN相应可以传输文件 =》要传输 0~128M 的第一块内容 =》NN返回 哪几个 DN 可以传输 =》 client 向 DN 申请传输 =》 DN 相应可以传输 =》传输文件 =》 NN 相应客户端 表示文件已经传输完成



![image-20210118111452876](images\image-20210118111452876.png)



![image-20210118111743903](images\image-20210118111743903.png)

1. 第一阶段：NameNode启动

    （1）第一次启动NameNode格式化后，创建Fsimage和Edits文件。如果不是第一次启动，直接加载编辑日志和镜像文件到内存。

   （2）客户端对元数据进行增删改的请求。

   （3）NameNode记录操作日志，更新滚动日志。

   （4）NameNode在内存中对数据进行增删改。

2. 第二阶段：Secondary NameNode工作

   ​    （1）Secondary NameNode询问NameNode是否需要CheckPoint。直接带回NameNode是否检查结果。

   ​    （2）Secondary NameNode请求执行CheckPoint。

   ​    （3）NameNode滚动正在写的Edits日志。

   ​    （4）将滚动前的编辑日志和镜像文件拷贝到Secondary NameNode。

   ​    （5）Secondary NameNode加载编辑日志和镜像文件到内存，并合并。

   ​    （6）生成新的镜像文件fsimage.chkpoint。

   ​    （7）拷贝fsimage.chkpoint到NameNode。

   ​    （8）NameNode将fsimage.chkpoint重新命名成fsimage。

seen_txid：保存的是 回滚日志成功的 id ( edit_inprogress_****id ) , 并不是成功checkpoint的Id ， 测试命令 ==》 hdfs dfsadmin -rollEdits

![image-20210118153931363](images\image-20210118153931363.png)

DateNode：系统数据库块的位置并不是由NameNode维护的，而是以块列表的形式存储在DateNode之中

dn 具体存储 文件本身 、 数据块的长度 、数据块的校验和、时间戳

![image-20210118154612206](images\image-20210118154612206.png)

两种方式 crc校验 ，奇偶校验

默认 节点掉线时间 ==》 10分钟 + 30 秒







