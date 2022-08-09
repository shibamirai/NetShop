package katachi.spring.exercise.domain.user.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import katachi.spring.exercise.domain.user.service.ShopService;
import katachi.spring.exercise.domain.user.service.UserService;
import lombok.Data;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<CartItem> itemList = new ArrayList<CartItem>();

	@Autowired
	UserService service;

	@Autowired
	ShopService shopService;

	@Autowired
	Errors errors;

	public void add(Integer id, Integer inventory) {
		//在庫数よりも多い注文だとアラート表示
		if (inventory > shopService.inventoryOne(id)) {
			errors.rejectValue("cartItemInventory", "cartItemInventory.over");
			return;
		}

		// カウンタを宣言
		int i = 0;
		for (CartItem item : itemList) {
			if (id == item.getId()) {
				// 今の個数を取得
				CartItem itemNew = itemList.get(i);
				Integer inventItem = inventory + itemNew.getCartItemInventory();

				itemNew.setCartItemInventory(inventItem);

				// 配列内のカウンタ番目の値を更新
				itemList.set(i, itemNew);

				return;
			}
			// カウンタを更新
			i++;
		}
		CartItem item = shopService.cartItemOne(id);
		item.setCartItemInventory(inventory);
		itemList.add(item);
	}

	public void clear() {
		itemList.clear();
	}

	public Integer count() {
		return itemList.size();
	}

	//合計金額
	public Integer money() {
		int total = 0;
		for (CartItem mItem : itemList) {
			total += mItem.getItemPrice() * mItem.getCartItemInventory();
		}
		return total;
	}

	public boolean change(Integer id, Integer inventory) {

		//在庫数よりも多い注文だとアラート表示
		if (inventory > shopService.inventoryOne(id)) {
			errors.rejectValue("cartItemInventory", "cartItemInventory.over");
			return false;
		}

		// カウンタを宣言
		int i = 0;
		for (CartItem item : itemList) {

			if (id == item.getId()) {
				// 今の個数を取得
				CartItem itemNew = itemList.get(i);
				itemNew.setCartItemInventory(inventory);

				// 配列内のカウンタ番目の値を更新
				itemList.set(i, itemNew);

			}
			// カウンタを更新
			i++;
		}
		return true;
	}

	public void deleted(Integer id) {
		//拡張for文中に削除を行うとエラーが出るので、イテレーターを使う
		Iterator<CartItem> iterator = itemList.iterator();
		while (iterator.hasNext()) {
			CartItem item = iterator.next();
			if (id == item.getId()) {
				System.out.println(id);
				iterator.remove();
			}
		}

		/*
		// カウンタを宣言
		int i = 0;
		for (CartItem item : itemList) {
		
			if (id == item.getId()) {
				// 配列内のカウンタ番目の値を削除
				itemList.remove(i);
		
			}
			// カウンタを更新
			i++;
		}  */
	}

}
