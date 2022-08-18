package katachi.spring.exercise.domain.service;

import java.util.List;

import katachi.spring.exercise.domain.model.Item;

public interface ItemService {

	/** 全商品情報取得 */
	public List<Item> getAll();
	/** 商品情報取得 */
	public Item get(int id);
	/** 指定商品の在庫数取得 */
	public int getStockQuantity(int id);
	/** 入庫 */
	public void store(Item item);
	/** 出庫 */
	public void deliver(int id, int num);
}
