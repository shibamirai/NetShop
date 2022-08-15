package katachi.spring.exercise.domain.model;

import java.util.Date;

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
}
