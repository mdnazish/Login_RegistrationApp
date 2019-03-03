/**
 * 
 */
package com.mn.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mn.app.model.User;
import com.mn.app.repository.UserRepository;

/**
 * @author Md Nazish
 *
 */
@Service
@Transactional
public class UserService {

	private final UserRepository userRepo;

	// constructor to assign Spring Data JPA repository dependency
	public UserService(UserRepository userRepository) {
		this.userRepo = userRepository;
	}

	// Saving a user into DB
	public void saveUser(User user) {
		userRepo.save(user);
	}

	// fetching all the user records from DB.
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		for (User user : userRepo.findAll()) {
			users.add(user);
		}
		return users;
	}

	// delete an user record from DB.
	public void deleteUser(int userId) {
		userRepo.deleteById(userId);
	}

	// update an existed user record from DB.
	public Optional<User> updateUser(int userId) {
		return userRepo.findById(userId);

	}
	
	// find User exist for login
	public User findByUsernameAndPassword(String username, String password) {
		return userRepo.findByUsernameAndPassword(username, password);
	}
}
