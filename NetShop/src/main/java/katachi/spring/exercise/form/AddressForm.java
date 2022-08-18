package katachi.spring.exercise.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AddressForm {

	@NotBlank
	private String addressee; //送り先宛名

	@NotBlank
	private String phoneNumber;

	@NotBlank
	private String postalCode;

	@NotBlank
	private String address1;

	@NotBlank
	private String address2;

	@NotBlank
	private String address3;

	private String address4;

	@Override
	public String toString() {
		return "AddressForm [addressee=" + addressee
				+ ", phoneNumber=" + phoneNumber
				+ ", postalCode=" + postalCode
				+ ", address1=" + address1
				+ ", address2=" + address2
				+ ", address3=" + address3
				+ ", address4="+ address4 + "]";
	}
}
