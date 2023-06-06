package br.com.tiago.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tiago.repository.UserRepository;

@Service
public class UserServices implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

	//CONSTRUTOR USANDO INJEÇÃO DEP.
	public UserServices(UserRepository userRepository) {
		this.userRepository = userRepository;
		
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		var user = userRepository.findByUserName(username);
		if(user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException("UserName" + username + "not found!");
		}
	}
	
}
