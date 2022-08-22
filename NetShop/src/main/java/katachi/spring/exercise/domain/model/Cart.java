package katachi.spring.exercise.domain.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Cart {
	private Map<Integer, CartItem> cartItemMap;

	public Cart() {
		cartItemMap = new HashMap<>();
	}
	
	public Integer[] getItemIds() {
		return cartItemMap.keySet().toArray(new Integer[0]);
	}
	
	public Collection<CartItem> getCartItems() {
		return cartItemMap.values();
	}
	
	public boolean containsItem(int id) {
		return cartItemMap.containsKey(id);
	}

	public CartItem getCartItem(int id) {
		return Optional.ofNullable(cartItemMap.get(id))
				.orElseThrow(NoSuchElementException::new);
	}

	public int getCount() {
		return cartItemMap.size();
	}
	
	public int getTotal() {
		return cartItemMap.values().stream()
				.reduce(0, (total, cartItem) -> total + cartItem.getSubTotal(), Integer::sum);
	}

	public void store(int id, Item item, int count) {
		if (count > 0) {
			CartItem cartItem = new CartItem(item, count);
			if (cartItemMap.containsKey(id)) {
				cartItemMap.replace(id, cartItem);
			} else {
				cartItemMap.put(id, cartItem);
			}
		} else {
			cartItemMap.remove(item.getId());
		}
	}
}
