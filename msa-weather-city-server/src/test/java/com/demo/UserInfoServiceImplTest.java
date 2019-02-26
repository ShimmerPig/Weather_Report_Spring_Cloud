package com.demo;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.bean.UserInfo;
import com.demo.myEnum.GenderEnum;
import com.demo.service.impl.UserInfoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserInfoServiceImplTest {
	@Autowired
	private UserInfoServiceImpl service;
	
	@Test
	public void findAll() {
		List<UserInfo>userInfoList=service.findAll();
		assertNotNull(userInfoList.size());
	}
	
	@Test
	public void save() {
		UserInfo user=new UserInfo("ggg", "122", "12435656434",
				"262431672@qq.com", GenderEnum.MAN.getCode());
		service.save(user);
		UserInfo user2=new UserInfo("fifff", "122", "12435656434",
				"262431672@qq.com", GenderEnum.WOMAN.getCode());
		service.save(user2);
	}
}
