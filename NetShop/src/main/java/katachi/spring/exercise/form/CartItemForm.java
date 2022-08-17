package katachi.spring.exercise.form;

import lombok.Data;

@Data
public class CartItemForm {

	private int id;
	private int count;
	
	public CartItemForm() {
		
	}
	
	public CartItemForm(int id, int count) {
		this.id = id;
		this.count = count;
	}
}
