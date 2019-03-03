/**
 * 
 */
package com.mn.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.mn.app.model.User;

/**
 * @author Md Nazish
 *
 */
public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsernameAndPassword(String username, String password);
}
