package katachi.spring.exercise.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import katachi.spring.exercise.domain.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) {

		UserDetails loginUser = service.getLoginUser(username);
		if (loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return loginUser;
	}
}