package com.hadoop.hdfs;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsClient {
	
	public static void main(String[] args) throws Exception, InterruptedException, URISyntaxException {
		Configuration configuration = new Configuration();
		
		configuration.set("fs.defaultFS", "hdfs://192.168.127.129:9000");
		
		FileSystem fs = FileSystem.get(new URI("hdfs://192.168.127.129:9000"), configuration,"hadoop");
		
		fs.mkdirs(new Path("/zheshiwenjianjia"));
		
		fs.close();
	}

}
