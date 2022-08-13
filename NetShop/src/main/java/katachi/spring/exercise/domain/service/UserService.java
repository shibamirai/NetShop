package katachi.spring.exercise.domain.service;

import katachi.spring.exercise.domain.model.LoginUser;
import katachi.spring.exercise.domain.model.User;

public interface UserService {

	public boolean isRegistered(String userId);
	public void signup(User user);
	public LoginUser getLoginUser(String email);
}
