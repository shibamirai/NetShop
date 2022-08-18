package katachi.spring.exercise.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper {

	public Integer getQuantity(int id);
	public void increase(int id, int count);
	public void decrease(int id, int count);
}
