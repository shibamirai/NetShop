package katachi.spring.exercise.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.exercise.domain.model.Cart;
import katachi.spring.exercise.domain.model.CartItem;
import katachi.spring.exercise.domain.model.MItem;
import katachi.spring.exercise.domain.service.ShopService;
import katachi.spring.exercise.form.AddressForm;
import katachi.spring.exercise.form.ItemAddForm;

@Controller
public class ShopController {

	@Autowired
	ShopService shopService;

	@Autowired
	ModelMapper mapper;

	@Autowired
	Cart cart;

	//商品一覧画面へ遷移
	@GetMapping("/list")
	public String getitemlist(@ModelAttribute MItem item, Model model) {
		//商品リストを取得
//		List<MItem> itemList = shopService.findMany();
//		model.addAttribute("itemList", itemList);
//
//		Integer count = cart.count();
//
//		model.addAttribute("count", count);

		return "/shop/list";
	}

	//商品詳細画面へ遷移
	@GetMapping("/list/details/{id}")
	public String getItemDetails(@PathVariable("id") String id, Model model) {
		MItem item = shopService.itemOne(id);
		model.addAttribute("item", item);
		return "/shop/details";
	}

	//カートに商品を入れる処理

	@PostMapping("/list/add")
	public String postItemAdd(@ModelAttribute ItemAddForm form, BindingResult bindingResult, Model model) {

		//ここでインスタンスを生成しなくてもIDとカートの中の商品の個数を引数で渡したほうが良い(疎結合なコードになるから)
		cart.add(form.getId(), form.getCartItemInventory());
		return "redirect:/list";
	}

	//カートの中を確認する画面へ遷移
	@GetMapping("list/cart")
	public String getItemCart(@ModelAttribute ItemAddForm form, Model model) {
		model.addAttribute("cart", cart);
		//合計金額
		int money = cart.money();
		model.addAttribute("money", money);
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

	//カートの中の商品数量変更
	@PostMapping(value = "/list/cartItem", params = "change")
	public String postChangeCartItem(@ModelAttribute ItemAddForm form, BindingResult bindingResult, Model model,
			Errors errors) {
		if (cart.change(form.getId(), form.getCartItemInventory()) == false) {
			errors.rejectValue("cartItemInventory", "cartItemInventory.over");
			bindingResult.hasErrors();
			return getItemCart(form, model);
		}
		return "redirect:/list/cart";
	}

	//カートの中の商品を削除
	@PostMapping(value = "/list/cartItem", params = "deleted")
	public String postDeletedCartItem(@ModelAttribute ItemAddForm form, Model model) {
		cart.deleted(form.getId());
		return "redirect:/list/cart";
	}

}
