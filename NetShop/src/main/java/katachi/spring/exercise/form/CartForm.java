package katachi.spring.exercise.form;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CartForm {

	private List<CartItemForm> itemFormList;
	
	public CartForm() {
		itemFormList = new ArrayList<>();
	}
	
	public CartItemForm getCartItemForm(int id) {
		return itemFormList.stream()
				.filter(form -> form.getId() == id)
				.findFirst()
				.orElse(new CartItemForm(id));
	}

	/**
	 * 引数の商品をリストに追加する
	 * 引数の商品と同じものが既にリストに存在していれば、それを削除してから追加する
	 * @param cartItemForm
	 */
	public void store(CartItemForm cartItemForm) {
		itemFormList.removeIf(form -> form.getId() == cartItemForm.getId());
		if (cartItemForm.getCount() > 0) {
			itemFormList.add(cartItemForm);
		}
	}
}
