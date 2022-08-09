package katachi.spring.exercise.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import katachi.spring.exercise.domain.user.model.MUser;

@Mapper
public interface UserMapper {

	// ユーザー登録
	public int insertOne(MUser user);

	//住所登録
	public int insertAddressOne(MUser user);

	//ログインユーザー取得
	public UserDetails findLoginUser(String username);

	//ユーザーID重複チェック
	public String userIdOne(String userId);

}
