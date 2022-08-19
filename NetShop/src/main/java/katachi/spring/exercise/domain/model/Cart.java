package katachi.spring.exercise.domain.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;

public class Cart {

	private Map<Item, Integer> itemMap;
	
	public Cart() {
		itemMap = new HashMap<>();
	}
	
	public int getCount() {
		return itemMap.size();
	}

	public int getCountOfItem(int id) {
		return Optional.ofNullable(itemMap.get(new Item(id))).orElse(0);
	}
	
	public int getSubTotal(Item item) {
		int count = Optional.ofNullable(itemMap.get(item)).orElse(0);
		return item.getPrice() * count;
	}
	
	public int getTotal() {
		return itemMap.entrySet().stream()
				.mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
				.sum();
	}

	public void store(Item item, int count) {
		if (count > 0) {
			itemMap.put(item, count);
		} else {
			itemMap.remove(item);
		}
	}
	
	public Set<Item> getCartItems() {
		return itemMap.keySet();
	}
	
	public void forEach(BiConsumer<? super Item, ? super Integer> action) {
		itemMap.forEach(action);
	}
}
