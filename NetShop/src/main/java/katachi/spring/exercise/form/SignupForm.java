package katachi.spring.exercise.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank
	@Email
	private String userId;

	@NotBlank
	@Length(min = 4, max = 100)
	private String password;

	@NotBlank
	private String familyName;

	@NotBlank
	private String firstName;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private Date birthday;

	@NotNull
	private Integer gender;

	//住所
	private String phoneNumber;
	private String postalCoder;
	private String prefectures;
	private String municipalities;
	private String number;

}
