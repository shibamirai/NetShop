package katachi.spring.exercise.domain.user.service;

import java.util.List;

import katachi.spring.exercise.domain.user.model.CartItem;
import katachi.spring.exercise.domain.user.model.MItem;

public interface ShopService {

	//商品一覧取得
	public List<MItem> findMany();

	//商品取得(1件)
	public MItem itemOne(String id);

	//在庫数更新
	public void updateOne(Integer itemInventory, Integer id);

	//在庫数取得(1件)
	public Integer inventoryOne(Integer id);

	//カートの中の商品取得(1件)
	public CartItem cartItemOne(Integer id);

}
