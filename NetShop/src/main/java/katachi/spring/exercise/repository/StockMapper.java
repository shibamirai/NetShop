package katachi.spring.exercise.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper {

	public Integer selectQuantity(int id);
}
