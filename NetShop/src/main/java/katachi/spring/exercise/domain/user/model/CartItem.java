package katachi.spring.exercise.domain.user.model;

import lombok.Data;

@Data
public class CartItem {

	private Integer id;
	private String itemName;
	private String itemImage;
	private Integer itemPrice;
	private Integer cartItemInventory;
}
