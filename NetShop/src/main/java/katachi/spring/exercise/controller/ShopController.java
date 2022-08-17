package katachi.spring.exercise.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import katachi.spring.exercise.domain.model.Cart;
import katachi.spring.exercise.domain.model.CartItem;
import katachi.spring.exercise.domain.model.Item;
import katachi.spring.exercise.domain.service.ItemService;
import katachi.spring.exercise.domain.service.ShopService;
import katachi.spring.exercise.form.AddressForm;
import katachi.spring.exercise.form.CartForm;
import katachi.spring.exercise.form.CartItemForm;

@Controller
@SessionAttributes(types = CartForm.class)
public class ShopController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	ItemService itemService;

	@Autowired
	ShopService shopService;

	@Autowired
	Cart cart;
	
	@ModelAttribute("cartForm")
	public CartForm getCartForm() {
		return new CartForm();
	}

	//商品一覧画面へ遷移
	@GetMapping("/")
	public String list(Model model) {

		List<Item> itemList = itemService.getAll();
		model.addAttribute("itemList", itemList);

		return "/shop/list";
	}

	//商品詳細画面へ遷移
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

	//カートに商品を入れる処理
	@RequestMapping(value = "/item/{id}", params="details", method = RequestMethod.POST)
	public String addToCart(@PathVariable("id") int id, @ModelAttribute CartForm cartForm, @ModelAttribute @Validated CartItemForm cartItemForm, BindingResult bindingResult, Model model) {

		validateCount(cartItemForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return getItemDetails(id, cartForm, model);
		}

		store(id, cartForm, cartItemForm);
		return "redirect:/";
	}

	//カートの商品個数を変更する処理
	@RequestMapping(value = "/item/{id}", params="cart", method = RequestMethod.POST)
	public String changeCount(@PathVariable("id") int id, @ModelAttribute CartForm cartForm, @ModelAttribute @Validated CartItemForm cartItemForm, BindingResult bindingResult, Model model) {

		validateCount(cartItemForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return getItemCart(cartItemForm, model);
		}

		store(id, cartForm, cartItemForm);
		return "redirect:/list/cart";
	}

	// 購入個数のバリデーション
	private void validateCount(CartItemForm cartItemForm, BindingResult bindingResult)  {
		if (cartItemForm.getCount() > itemService.getStockQuantity(cartItemForm.getId())) {
			bindingResult.rejectValue("count", "cartItemForm.count.over");
		}
	}

	// カートへ商品を追加
	private void store(int id, CartForm cartForm, CartItemForm cartItemForm) {
		Item item = itemService.get(id);
		cartForm.store(item, cartItemForm.getCount());
	}

	//カートの中を確認する画面へ遷移
	@GetMapping("list/cart")
	public String getItemCart(@ModelAttribute CartItemForm form, Model model) {
		return "/shop/cart";
	}

	//住所入力画面へ遷移
	@GetMapping("/list/addressForm")
	public String getItemBuy(@ModelAttribute AddressForm addressForm, Model model) {
		model.addAttribute("addressForm", addressForm);

		List<String> list = Arrays.asList("北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
				"茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
				"新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", "岐阜県",
				"静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県",
				"奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県", "山口県",
				"徳島県", "香川県", "愛媛県", "高知県", "福岡県", "佐賀県", "長崎県",
				"熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県");

		model.addAttribute("list", list);

		return "/shop/addressForm";
	}

	//注文内容確認画面へ遷移
	@PostMapping("/list/addressForm")
	public String postItemBuy(@ModelAttribute AddressForm addressForm, Model model) {
		model.addAttribute("addressForm", addressForm);
		model.addAttribute("cart", cart);
		//合計金額
		int money = 0;
		money = cart.money();
		model.addAttribute("money", money);
		return "/shop/voucher";
	}

	//注文受付完了
	@PostMapping(value = "/list/voucher", params = "order")
	public String postItemOrder(@ModelAttribute AddressForm addressForm) {
		//カートの中の商品に応じて在庫数を減らす
		ArrayList<CartItem> itemList;
		itemList = cart.getItemList();

		for (CartItem item : itemList) {
			int itemInventory = shopService.inventoryOne(item.getId());
			itemInventory -= item.getCartItemInventory();
			shopService.updateOne(itemInventory, item.getId());
		}
		cart.clear();
		return "/shop/orderCompleted";
	}

	//注文内容修正
	@PostMapping(value = "/list/voucher", params = "revision")
	public String postItemOrderRevision(@ModelAttribute AddressForm addressForm, Model model) {
		model.addAttribute("addressForm", addressForm);
		List<String> list = Arrays.asList("北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
				"茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
				"新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", "岐阜県",
				"静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県",
				"奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県", "山口県",
				"徳島県", "香川県", "愛媛県", "高知県", "福岡県", "佐賀県", "長崎県",
				"熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県");
		model.addAttribute("list", list);
		return "/shop/addressForm";
	}

}
