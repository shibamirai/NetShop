package katachi.spring.exercise.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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
	public String getSignup(Model model, Locale locale,
			@ModelAttribute SignupForm form) {
		// 性別を取得
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);
		model.addAttribute("signupForm", form);
		System.out.println("aiueo");
		List<String> list = Arrays.asList("北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
				"茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
				"新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", "岐阜県",
				"静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県",
				"奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県", "山口県",
				"徳島県", "香川県", "愛媛県", "高知県", "福岡県", "佐賀県", "長崎県",
				"熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県");
		model.addAttribute("list", list);
		// ユーザー登録画面に遷移
		return "signup/signup";
	}

	@PostMapping(value = "/signup", params = "submit")
	public String postSignup(Model model, Locale locale, @ModelAttribute @Validated SignupForm form,
			BindingResult bindingResult, Errors errors) {

		model.addAttribute("SignupForm", form);

		if (userService.userIdOne(form.getUserId()) == false) {
			errors.rejectValue("userId", "userId.error");
		}

		if (bindingResult.hasErrors()) {
			return getSignup(model, locale, form);
		}

		//入力内容確認画面へ遷移
		return "signup/confirmation";
	}

	@PostMapping(value = "/user/signup", params = "complet")
	public String postCompletSignup(@ModelAttribute SignupForm form) {

		MUser user = modelMapper.map(form, MUser.class);

		//ユーザー登録処理
		userService.signup(user);

		return "signup/success";
	}

	@PostMapping(value = "/user/signup", params = "cancel")
	public String postCancelSignup(Model model, Locale locale,
			@ModelAttribute SignupForm form) {
		// 性別を取得
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);
		model.addAttribute("signupForm", form);
		System.out.println("aiueol");
		System.out.println(form.getUserId());
		List<String> list = Arrays.asList("北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
				"茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
				"新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", "岐阜県",
				"静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県",
				"奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県", "山口県",
				"徳島県", "香川県", "愛媛県", "高知県", "福岡県", "佐賀県", "長崎県",
				"熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県");
		model.addAttribute("list", list);

		// ユーザー登録画面に遷移
		return "signup/signup";
	}

}
