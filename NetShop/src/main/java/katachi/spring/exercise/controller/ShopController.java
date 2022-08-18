package katachi.spring.exercise.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.exercise.application.service.UserApplicationService;
import katachi.spring.exercise.domain.model.Item;
import katachi.spring.exercise.domain.model.LoginUser;
import katachi.spring.exercise.domain.model.User;
import katachi.spring.exercise.domain.service.ItemService;
import katachi.spring.exercise.domain.service.UserService;
import katachi.spring.exercise.form.AddressForm;
import katachi.spring.exercise.form.CartForm;
import katachi.spring.exercise.form.CartItemForm;

@Controller
@SessionAttributes(types = CartForm.class)
public class ShopController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserApplicationService userApplicationService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;
	
	@ModelAttribute("cartForm")
	public CartForm getCartForm() {
		return new CartForm();
	}

	/**
	 * 商品一覧画面
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String list(Model model) {

		List<Item> itemList = itemService.getAll();
		model.addAttribute("itemList", itemList);

		return "/shop/list";
	}

	/**
	 * 商品詳細画面
	 * @param id
	 * @param cartForm
	 * @param model
	 * @return
	 */
	@GetMapping("/item/{id}")
	public String getItemDetails(@PathVariable("id") int id, @ModelAttribute CartForm cartForm, Model model) {
		Item item = itemService.get(id);
		model.addAttribute("item", item);
		if (!model.containsAttribute("cartItemForm")) {
			CartItemForm cartItemForm = new CartItemForm(id, cartForm.getCountOfItem(id));
			model.addAttribute("cartItemForm", cartItemForm);
		}
		return "/shop/details";
	}

	/**
	 * 商品詳細からカートに追加
	 * @param id
	 * @param cartForm
	 * @param cartItemForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/item/{id}", params="details", method = RequestMethod.POST)
	public String addToCart(@PathVariable("id") int id, @ModelAttribute CartForm cartForm, @ModelAttribute @Validated CartItemForm cartItemForm, BindingResult bindingResult, Model model) {

		validateCount(cartItemForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return getItemDetails(id, cartForm, model);
		}

		store(id, cartForm, cartItemForm);
		return "redirect:/";
	}

	/**
	 * カートの表示
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping("list/cart")
	public String getItemCart(@ModelAttribute CartItemForm form, Model model) {
		return "/shop/cart";
	}

	/**
	 * カート内の商品個数変更
	 * @param id
	 * @param cartForm
	 * @param cartItemForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/item/{id}", params="cart", method = RequestMethod.POST)
	public String changeCount(@PathVariable("id") int id, @ModelAttribute CartForm cartForm, @ModelAttribute @Validated CartItemForm cartItemForm, BindingResult bindingResult, Model model) {

		validateCount(cartItemForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return getItemCart(cartItemForm, model);
		}

		store(id, cartForm, cartItemForm);
		return "redirect:/list/cart";
	}

	/**
	 * 購入個数のバリデーション
	 * @param cartItemForm
	 * @param bindingResult
	 */
	private void validateCount(CartItemForm cartItemForm, BindingResult bindingResult)  {
		if (cartItemForm.getCount() > itemService.getStockQuantity(cartItemForm.getId())) {
			bindingResult.rejectValue("count", "cartItemForm.count.over");
		}
	}

	/**
	 * カートに入れる商品をセッションに保存
	 * @param id
	 * @param cartForm
	 * @param cartItemForm
	 */
	private void store(int id, CartForm cartForm, CartItemForm cartItemForm) {
		Item item = itemService.get(id);
		cartForm.store(item, cartItemForm.getCount());
	}

	/**
	 * お届け先入力画面
	 * @param addressForm
	 * @param model
	 * @return
	 */
	@GetMapping("/list/addressForm")
	public String getAddress(@ModelAttribute AddressForm addressForm, Model model,
			@AuthenticationPrincipal LoginUser loginUser ) {

		model.addAttribute("list", userApplicationService.getPrefectureList());

		if (loginUser != null) {
			User user = userService.getUser(loginUser.getEmail());
			addressForm = mapper.map(user, AddressForm.class);
			model.addAttribute("addressForm", addressForm);
		}

		return "/shop/address";
	}

	/**
	 * お届け先入力から注文内容確認画面へ
	 * @param addressForm
	 * @param bindingResult
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/list/addressForm")
	public String postAddress(
			@ModelAttribute @Validated AddressForm addressForm,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes
	) {
		if (bindingResult.hasErrors()) {
			return getAddress(addressForm, model, null);
		}

		redirectAttributes.addFlashAttribute("addressForm", addressForm);
		return "/shop/confirm";
	}

	/**
	 * 注文受付
	 * @param cartForm
	 * @param addressForm
	 * @param sessionStatus
	 * @return
	 */
	@PostMapping("/list/voucher")
	public String postOrder(@ModelAttribute CartForm cartForm, @ModelAttribute AddressForm addressForm, SessionStatus sessionStatus) {

		// TODO 現在の在庫数で注文数のバリデーションをやり直すべき

		// 注文した数だけ在庫を減らす
		cartForm.forEach((item, count) -> itemService.deliver(item.getId(), count));

		// Cart情報をセッションから削除
		sessionStatus.setComplete();

		return "/shop/save";
	}
}
