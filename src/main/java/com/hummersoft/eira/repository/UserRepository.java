package com.hummersoft.eira.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hummersoft.eira.model.User;


/**
 * @author 
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	Optional<User>  findByUserName(String UserName);
	
	  @Query("FROM User WHERE EmailID= :email and ActiveFlag=1") 
	  Optional<User> findActiveuserByEmail(@Param("email") String email);
	  

	  @Query("FROM User WHERE EmailID= :email") 
		User findByEmailID(@Param("email")String emailId);
	
	 

}
