package katachi.spring.exercise.domain.model;

public class CartItem {
	private Item item;
	private int count;
	
	public CartItem(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}
	
	public int getSubTotal() {
		return item.getPrice() * count;
	}
	
	/**
	 * 在庫切れかどうか
	 * @return
	 */
	public boolean isOutOfStock() {
		if (count > item.getStockQuantity()) {
			return true;
		} else {
			return false;
		}
	}
}
