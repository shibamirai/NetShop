package katachi.spring.exercise.repository;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.exercise.domain.model.LoginUser;
import katachi.spring.exercise.domain.model.User;

@Mapper
public interface UserMapper {

	public LoginUser findLoginUser(String email);
	public int insertOne(User user);

}
