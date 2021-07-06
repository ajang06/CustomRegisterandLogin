package com.revature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateUser1() {
		User user = new User();
		user.setUsername("JohnDoe12");
		user.setEmail("johndoe@mail.com");
		user.setPassword("JD123");
		user.setFirstName("John");
		user.setLastName("Doe");
		User savedUser = repo.save(user);
		User persistedUser = entityManager.find(User.class, savedUser.getUsername());
		assertThat(persistedUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void testCreateUser2() {
		User user = new User();
		user.setUsername("JaneDoe12");
		user.setEmail("janesmith@mail.com");
		user.setPassword("JS123");
		user.setFirstName("Jane");
		user.setLastName("Smith");
		User savedUser = repo.save(user);
		User persistedUser = entityManager.find(User.class, savedUser.getUsername());
		assertThat(persistedUser.getFirstName()).isEqualTo(user.getFirstName());
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "janesmith@mail.com";
		User user = repo.findByEmail(email);
		assertThat(user).isNotNull();
	}

}
