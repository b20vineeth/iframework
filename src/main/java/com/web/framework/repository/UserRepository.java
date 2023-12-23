package com.web.framework.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.framework.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Since email is unique, we'll find users by email
     Optional<User>  findByUname(String uname);

	Optional<User> findByEmail(String email); 
	
	 List<User> findByUnameIn(List<String> unames);

}
