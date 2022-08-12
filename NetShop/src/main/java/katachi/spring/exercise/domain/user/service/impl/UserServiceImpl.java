package katachi.spring.exercise.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import katachi.spring.exercise.domain.user.model.LoginUser;
import katachi.spring.exercise.domain.user.model.User;
import katachi.spring.exercise.domain.user.service.UserService;
import katachi.spring.exercise.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public boolean isRegistered(String email) {
		if (mapper.findLoginUser(email) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void signup(User user) {

		// パスワードはハッシュ化する
		String pass = user.getPassword();
		user.setPassword(encoder.encode(pass));
		
		// 一般ユーザとして登録する
		user.setRole("ROLE_GENERAL");

		mapper.insertOne(user);
	}

	@Override
	public LoginUser getLoginUser(String email) {
		return mapper.findLoginUser(email);
	}
}
