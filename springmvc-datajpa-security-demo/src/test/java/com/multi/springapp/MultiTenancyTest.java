package com.multi.springapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

import com.multi.springapp.entities.Texto;
import com.multi.springapp.entities.User;
import com.multi.springapp.repositories.TextoRepository;
import com.multi.springapp.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
@TestExecutionListeners(listeners = { ServletTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		WithSecurityContextTestExecutionListener.class })
public class MultiTenancyTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TextoRepository textoRepository;
	
	@Test
	@WithMockUser(username = "admin", roles = { "USER", "ADMIN" })
	public void testName() throws Exception {
		User user = new User("admin");
		userRepository.save(user);
		Texto texto =new Texto();
		texto.setTexto("blablabla");
		textoRepository.save(texto);
	}
}
