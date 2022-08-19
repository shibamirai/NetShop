package katachi.spring.exercise.form;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class CartItemForm {

	private int id;
	
	@Min(value = 0)
	private int count;
	
	public CartItemForm() {
		
	}
	
	public CartItemForm(int id, int count) {
		this.id = id;
		this.count = count;
	}
}
