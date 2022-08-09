package katachi.spring.exercise.domain.user.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MUser implements UserDetails {

	private String userId;

	private String familyName;

	private String firstName;

	private Date birthday;

	private Integer gender;

	private String phoneNumber;

	private String postalCoder;
	private String prefectures;
	private String municipalities;
	private String number;

	private static final long serialVersionUID = 1L;
	private String user;
	private String password;
	private Collection<GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return authorities;
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	public String getFullName() {
		return familyName + firstName;
	}

}