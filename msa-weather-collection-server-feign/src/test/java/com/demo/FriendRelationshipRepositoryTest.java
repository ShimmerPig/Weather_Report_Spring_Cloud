package com.demo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.bean.FriendRelationship;
import com.demo.dao.FriendRelationshipRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FriendRelationshipRepositoryTest {
	@Autowired
	private FriendRelationshipRepository repository;
	
	@Test
	public void saveTest() {
		FriendRelationship relationship=new FriendRelationship(14, 19);
		FriendRelationship result=repository.save(relationship);
		assertNotNull(result);
	}
	
	@Test
	public void deleteTest() {
		//FriendRelationship relationship=new FriendRelationship(14, 19);
		repository.deleteByUserIdAndFriendId(14, 19);
		//FriendRelationship relationship2=new FriendRelationship(14, 11);
		//repository.delete(relationship2);
	}
	
	@Test
	public void find() {
//		List<FriendRelationship>list=repository.findByUserIdEquals(11);
//		assertNotNull(list.size());
		List<FriendRelationship>list=repository.findByFriendIdEquals(12);
		assertNotNull(list.size());
	}
}
