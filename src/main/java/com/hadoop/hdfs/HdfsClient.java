package com.hadoop.hdfs;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

public class HdfsClient {
	
	public static void main(String[] args) throws Exception, InterruptedException, URISyntaxException {
		Configuration configuration = new Configuration();
		
		configuration.set("fs.defaultFS", "hdfs://192.168.127.129:9000");
		
		FileSystem fs = FileSystem.get(new URI("hdfs://192.168.127.129:9000"), configuration,"hadoop");
		
		// 创建文件夹
//		fs.mkdirs(new Path("/zheshiwenjianjia6"));
//		fs.mkdirs(new Path("/zheshiwenjianjia78"));
//		fs.mkdirs(new Path("/zheshiwenjianjia88"));
//		fs.mkdirs(new Path("/zheshiwenjianjia99"));
		
		
		// 上传文件 || 会占用整个块
//		fs.copyFromLocalFile(new Path("F:/weining/BigData/参数文件/hello.txt"), new Path("/hello.txt"));
		
		
		
		// 下载文件
//		fs.copyToLocalFile(false,new Path("/zheshiwenjianjia/zheshitestwenjian1.txt"), new Path("F:\\weining\\BigData\\HadoopFiles\\downloadfile.txt"),true);
		
		// 删除文件
		fs.delete(new Path("/output"),true);
		
//		fs.rename(new Path("/test.log"), new Path("/改名子的.log"));
		
		// 查看文件详情
		/*RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
			
		while(listFiles.hasNext()){
			LocatedFileStatus status = listFiles.next();
				
			// 输出详情
			// 文件名称
			System.out.println(status.getPath().getName());
			// 长度
			System.out.println(status.getLen());
			// 权限
			System.out.println(status.getPermission());
			// 分组
			System.out.println(status.getGroup());
				
			// 获取存储的块信息
			BlockLocation[] blockLocations = status.getBlockLocations();
				
			for (BlockLocation blockLocation : blockLocations) {
					
				// 获取块存储的主机节点
				String[] hosts = blockLocation.getHosts();
					
				for (String host : hosts) {
					System.out.println(host);
				}
			}
				
			System.out.println("-----------分割线----------");
		}*/
		
		// 文件或文件夹判断
/*		FileStatus[] listStatus = fs.listStatus(new Path("/"));
		
		for (FileStatus fileStatus : listStatus) {
			System.out.println(fileStatus.getPath().getName() + "------------" + fileStatus.isFile());
		}*/
		
		
		
		fs.close();
		
		System.out.println("commit");
	}
	
	

}
