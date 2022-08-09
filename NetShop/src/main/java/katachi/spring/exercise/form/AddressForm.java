package katachi.spring.exercise.form;

import lombok.Data;

@Data
public class AddressForm {

	private String name; //送り先宛名
	private String phoneNumber;
	private String postalCoder;
	private String prefectures;
	private String municipalities;
	private String number;

}
