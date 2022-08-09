package katachi.spring.exercise.config;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Configuration
public class JavaConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Errors errors() {
		return new Errors() {

			@Override
			public void setNestedPath(String nestedPath) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void rejectValue(String field, String errorCode, String defaultMessage) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void rejectValue(String field, String errorCode) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void reject(String errorCode, String defaultMessage) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void reject(String errorCode) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void pushNestedPath(String subPath) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void popNestedPath() throws IllegalStateException {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public boolean hasGlobalErrors() {
				// TODO 自動生成されたメソッド・スタブ
				return false;
			}

			@Override
			public boolean hasFieldErrors(String field) {
				// TODO 自動生成されたメソッド・スタブ
				return false;
			}

			@Override
			public boolean hasFieldErrors() {
				// TODO 自動生成されたメソッド・スタブ
				return false;
			}

			@Override
			public boolean hasErrors() {
				// TODO 自動生成されたメソッド・スタブ
				return false;
			}

			@Override
			public String getObjectName() {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public String getNestedPath() {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public List<ObjectError> getGlobalErrors() {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public int getGlobalErrorCount() {
				// TODO 自動生成されたメソッド・スタブ
				return 0;
			}

			@Override
			public ObjectError getGlobalError() {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public Object getFieldValue(String field) {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public Class<?> getFieldType(String field) {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public List<FieldError> getFieldErrors(String field) {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public List<FieldError> getFieldErrors() {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public int getFieldErrorCount(String field) {
				// TODO 自動生成されたメソッド・スタブ
				return 0;
			}

			@Override
			public int getFieldErrorCount() {
				// TODO 自動生成されたメソッド・スタブ
				return 0;
			}

			@Override
			public FieldError getFieldError(String field) {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public FieldError getFieldError() {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public int getErrorCount() {
				// TODO 自動生成されたメソッド・スタブ
				return 0;
			}

			@Override
			public List<ObjectError> getAllErrors() {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public void addAllErrors(Errors errors) {
				// TODO 自動生成されたメソッド・スタブ

			}
		};
	}
}
