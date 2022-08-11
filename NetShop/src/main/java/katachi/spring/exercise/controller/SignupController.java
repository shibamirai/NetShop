package katachi.spring.exercise.controller;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.exercise.application.service.UserApplicationService;
import katachi.spring.exercise.domain.user.model.MUser;
import katachi.spring.exercise.domain.user.service.UserService;
import katachi.spring.exercise.form.SignupForm;

@Controller
public class SignupController {

	@Autowired
	private UserApplicationService userApplicationService;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/signup")
	public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form) {
		model.addAttribute("genderMap", userApplicationService.getGenderMap(locale));
		model.addAttribute("list", userApplicationService.getPrefectureList());
		return "signup/signup";
	}

	@PostMapping("/signup/confirm")
	public String postSignup(Model model, Locale locale, @ModelAttribute @Validated SignupForm form,
			BindingResult bindingResult) {
		if (userService.isRegistered(form.getUserId())) {
			bindingResult.rejectValue("userId", "userId.error");
		}

		if (bindingResult.hasErrors()) {
			return getSignup(model, locale, form);
		}

		//入力内容確認画面へ遷移
		return "signup/confirmation";
	}

	@PostMapping(value = "/signup", params = "complete")
	public String postCompletSignup(@ModelAttribute SignupForm form) {

		MUser user = modelMapper.map(form, MUser.class);

		//ユーザー登録処理
		userService.signup(user);

		return "signup/success";
	}

	@PostMapping(value = "/signup", params = "cancel")
	public String postCancelSignup(Model model, Locale locale,
			@ModelAttribute SignupForm form) {
		// ユーザー登録画面に遷移
		return getSignup(model, locale, form);
	}

}
