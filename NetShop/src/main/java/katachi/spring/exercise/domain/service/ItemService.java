package katachi.spring.exercise.domain.service;

import java.util.List;

import katachi.spring.exercise.domain.model.Item;

public interface ItemService {

	public List<Item> getAll();
	public Item get(int id);
	public int getStockQuantity(int id);
	public void store(Item item);
	public void deliver(int id, int num);
}
