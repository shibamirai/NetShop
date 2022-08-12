package katachi.spring.exercise.domain.user.service;

import katachi.spring.exercise.domain.user.model.LoginUser;
import katachi.spring.exercise.domain.user.model.User;

public interface UserService {

	public boolean isRegistered(String userId);
	public void signup(User user);
	public LoginUser getLoginUser(String email);
}
