package com.suresh.learning.swagger.swaggerdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.openapitools.api.UsersApi;
import org.openapitools.model.User;
import org.openapitools.model.User.GenderEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.learning.swagger.swaggerdemo.exception.UserNotFoundProblem;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "users" })
public class UserController implements UsersApi {

	List<User> userList = new ArrayList<>();

	@Override
	public ResponseEntity<List<User>> getUsers() {
		populateUserList();
		return ResponseEntity.ok(userList);
	}

	@Override
	public ResponseEntity<User> addUser(@RequestBody User user) {
		userList.add(user);
		return ResponseEntity.ok(user);
	}

	@Override
	public ResponseEntity<User> deleteUser(@PathVariable("id_number") Integer idNumber) {
		return ResponseEntity
				.ok(userList.stream().filter(users -> users.getIdNumber().equals(idNumber)).findFirst().orElse(null));
	}

	@Override
	public ResponseEntity<User> getUserById(@PathVariable("id_number") Integer idNumber) {
		
		User user =userList.stream().filter(users -> users.getIdNumber().equals(idNumber)).findFirst().orElse(null);
		if(user == null) {
			throw new UserNotFoundProblem(idNumber);
		}
		return ResponseEntity.ok(user);
	}

	private void populateUserList() {
		User user = new User();

		for (int i = 0; i < 3; i++) {
			user.setName("Test User " + i);
			user.setSurname("Test User Surname " + i);
			user.setIdNumber(i);
			user.setMarried((i % 2 == 0));
			user.setGender((i % 2 == 0) ? GenderEnum.MALE : GenderEnum.FEMALE);
			userList.add(user);
		}
	}

}
