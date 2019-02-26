package com.demo;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.bean.FileInfo;
import com.demo.dao.FileInfoRepository;
import com.demo.service.impl.FileInfoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileInfoServiceImplTest {
	
	@Autowired
	private FileInfoServiceImpl fileInfoService;
	
	@Test
	public void findOne()throws Exception{
		FileInfo fileInfo=fileInfoService.findOne(1);
		assertNotNull(fileInfo);
	}
	@Test
	public void findAll()throws Exception{
		List<FileInfo>fileInfoList=fileInfoService.findAll();
		assertNotEquals(0, fileInfoList.size());
	}
	@Test
	public void save()throws Exception{
		FileInfo fileInfo=new FileInfo();
		fileInfo.setFileName("pig.txt");
		fileInfo.setFileIcon(1);
		fileInfo.setFileType("text/plain");
		FileInfo result=fileInfoService.save(fileInfo);
		assertNotNull(result);
	}
	
}
