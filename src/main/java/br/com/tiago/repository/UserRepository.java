package br.com.tiago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tiago.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	/*@Query("SELECT u FROM user u WHERE u.userName =: userName")
	User findByUserName (@Param ("userName") String userName);*/
	
	@Query("SELECT obj FROM User obj WHERE obj.userName =: userName")
	User findByUserName(@Param("userName") String userName);
}
