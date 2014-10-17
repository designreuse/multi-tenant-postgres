package com.multi.springapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.multi.springapp.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/application-context.xml")
public class MultiTenancyTest {
	
	@Autowired
	private UserRepository userRepository; 

	@Test
	public void testName() throws Exception {
		System.out.println("");
	}
}
