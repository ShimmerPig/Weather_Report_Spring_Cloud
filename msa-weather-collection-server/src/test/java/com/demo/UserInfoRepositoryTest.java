package com.demo;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.bean.UserInfo;
import com.demo.dao.UserInfoRepository;
import com.demo.myEnum.GenderEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserInfoRepositoryTest {
	@Autowired
	private UserInfoRepository repository;
	
	@Test
	public void findOneTest() {
		UserInfo user=repository.findById(11).get();
		assertNotNull(user);
	}
	
	@Test
	public void saveTest() {
		UserInfo user=new UserInfo("pig","123", 
		"12345678909", "2954298785@qq.com", GenderEnum.WOMAN.getCode());
		UserInfo result=repository.save(user);
		assertNotNull(result);
	}
}
