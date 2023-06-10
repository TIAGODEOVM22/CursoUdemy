package br.com.tiago.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tiago.model.User;
import br.com.tiago.repository.UserRepository;

@Service
public class UserServices implements UserDetailsService {

	@Autowired
	UserRepository repository;

	/*@Override PROFESSOR
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUserName(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("UserName" + username + "not found!");
		}
	}*/
	/*@Override EU
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = Optional.ofNullable(repository.findByUserName(username));
		return (UserDetails) new UsernameNotFoundException("UserName" + username + "not found!");

	}*/
	
	/*CHAT GPT*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    return Optional.ofNullable(repository.findByUserName(username))
	            .orElseThrow(() -> new UsernameNotFoundException("UserName " + username + " not found!"));
	}

}