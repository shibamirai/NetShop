package katachi.spring.exercise.domain.model;

import java.util.Date;
import java.util.Objects;

import lombok.Data;

@Data
public class Item {

	private int id;
	private String name;
	private String image;
	private int price;
	private String description;
	private boolean isDeleted;
	private Date createDateTime;
	private Date updateDateTime;
	private int stockQuantity;

	public Item(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
