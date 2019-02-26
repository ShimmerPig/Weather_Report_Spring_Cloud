package com.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.bean.FileInfo;
import com.demo.dao.FileInfoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileInfoRepositoryTest {
	@Autowired
	private FileInfoRepository repository;
	
	@Test
	public void findOneTest() {
		FileInfo fileInfo=repository.findById(1).get();
		System.out.println(fileInfo.toString());
	}
	
	@Test
	public void saveTest() {
		FileInfo fileInfo=new FileInfo();
		fileInfo.setFileName("zhu.txt");
		fileInfo.setFileType("text/plain");
		fileInfo.setFileIcon(1);
		repository.save(fileInfo);
	}
	
}
