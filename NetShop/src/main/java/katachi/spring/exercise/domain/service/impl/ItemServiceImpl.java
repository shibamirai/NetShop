package katachi.spring.exercise.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.exercise.domain.model.Item;
import katachi.spring.exercise.domain.service.ItemService;
import katachi.spring.exercise.repository.ItemMapper;
import katachi.spring.exercise.repository.StockMapper;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper mapper;

	@Autowired
	private StockMapper stockMapper;

	@Override
	public List<Item> getAll() {
		return mapper.selectAll();
	}

	@Override
	public Item get(int id) {
		return mapper.selectOne(id);
	}
	
	@Override
	public int getStockQuantity(int id) {
		return stockMapper.selectQuantity(id);
	}

	@Override
	public void store(Item item) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void deliver(int id, int num) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
