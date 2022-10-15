package com.gokul.satsum;

import com.gokul.satsum.model.User;
import com.gokul.satsum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

	@Autowired
	private UserRepository repository;

	@PostConstruct
	public void initUsers() {
		List<User> users = Stream.of(
				new User(101, "user1", "pwd1", "user1@gmail.com"),
				new User(102, "admin", "admin", "admin@gmail.com")
		).collect(Collectors.toList());
		repository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}

}
