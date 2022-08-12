package katachi.spring.exercise.domain.user.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {

	private int id;
	private String email;
	private String password;
	private String familyName;
	private String firstName;
	private String role;
	private Date birthday;
	private Integer gender;
	private String phoneNumber;
	private String postalCode;
	private String address1;
	private String address2;
	private String address3;
	private String address4;

	public String getFullName() {
		return familyName + firstName;
	}

}