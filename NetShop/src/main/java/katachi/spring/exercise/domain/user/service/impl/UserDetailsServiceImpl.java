package katachi.spring.exercise.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import katachi.spring.exercise.repository.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserDetails userDetails = mapper.findLoginUser(username);
		if (userDetails == null) {
			throw new UsernameNotFoundException("user not found.");
		}
		return userDetails;
	}
}