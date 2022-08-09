package katachi.spring.exercise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsSevice;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/** セキュリティの対象外を設定 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティを適用しない
		web
				.ignoring()
				.antMatchers("/webjars/**")
				.antMatchers("/image/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/MySQL/**");
	}

	/** セキュリティの各種設定 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/**
		// ログイン不要ページの設定
		http
				.authorizeRequests()
				.antMatchers("/login").permitAll() //直リンクOK
				.antMatchers("/user/signup").permitAll() //直リンクOK
				.anyRequest().authenticated(); // それ以外は直リンクNG
		*/

		// ログイン処理
		http
				.formLogin()
				.loginProcessingUrl("/login") // ログイン処理のパス
				.loginPage("/login") // ログインページの指定
				.failureUrl("/login?error") // ログイン失敗時の遷移先
				.usernameParameter("userId") // ログインページのユーザーID
				.passwordParameter("password") // ログインページのパスワード
				.defaultSuccessUrl("/list", true); // 成功後の遷移先

		/**
		// ログアウト処理
		http
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout");
		*/

	}

	/** 認証の設定 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = passwordEncoder();

		auth
				.userDetailsService(userDetailsSevice)
				.passwordEncoder(encoder);
		//.inMemoryAuthentication()
		//.withUser("user") // userを追加
		//.password(encoder.encode("user"))
		//.roles("");
	}

}
