package com.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.bean.FriendRelationship;
import com.demo.bean.UserInfo;
import com.demo.service.impl.FriendRelationshipServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FriendRelationshipServiceImplTest {

	@Autowired
	FriendRelationshipServiceImpl service;
	
	@Test
	public void deleteTest() {
		FriendRelationship ship=new FriendRelationship(11,13);
		service.deleteRelationship(ship);
		FriendRelationship ship2=new FriendRelationship(11,14);
		service.deleteRelationship(ship2);
	}
	
	@Test
	public void findAllFriendTest() {
		List<UserInfo>list=service.findAllFriend(11);
		for(UserInfo u:list) {
			System.out.println("------------------->>"+u.getUserId());
		}
		assertNotNull(list.size());
	}
	
	@Test
	public void saveTest() {
		FriendRelationship ship=new FriendRelationship(16,11);
		service.saveRelationship(ship);
		FriendRelationship ship2=new FriendRelationship(11,17);
		service.saveRelationship(ship2);
		
	}
}
