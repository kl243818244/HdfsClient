package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	
	public static final String CUBE_COMMON_URL = "http://172.16.6.162:4000/cubejs-api";

	@Test
	public void contextLoads() {
	}
	
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Test
	public void testTemplate() {
		String input = "{\"measures\":[\"KLGDiagnosisSymptomCube.count\"],\"timeDimensions\":[],\"dimensions\":[\"KLGDiagnosisSymptomCube.externalSourceTypeCode\",\"KLGDiagnosisSymptomCube.teId\"],\"filters\":[]}";
		
		
		ResponseEntity<JSONObject> forEntity = restTemplate.getForEntity(CUBE_COMMON_URL + "/v1/load?query={1}", JSONObject.class, input);
		
		System.out.println(forEntity.getBody());
		
	}
	

}
