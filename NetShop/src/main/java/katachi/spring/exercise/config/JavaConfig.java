package katachi.spring.exercise.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import katachi.spring.exercise.domain.model.User;
import katachi.spring.exercise.form.AddressForm;

@Configuration
public class JavaConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		
		// AddressFormの宛名にはUserのフルネームを設定する
		mapper.typeMap(User.class, AddressForm.class)
			  .addMapping(User::getFullName, AddressForm::setAddressee);

		return mapper;
	}
}
