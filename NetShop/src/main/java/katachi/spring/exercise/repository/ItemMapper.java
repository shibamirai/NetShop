package katachi.spring.exercise.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.exercise.domain.model.Item;

@Mapper
public interface ItemMapper {

	public List<Item> selectAll();
	public Item selectOne(int id);
}
