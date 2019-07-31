package edu.blog.dao;

import java.util.List;

import edu.blog.bean.Counter;

public interface CounterDao {
	Long insert(Counter bean);

	Long delete(Long id);
	
	Long update(Counter bean);
	
	List<Counter> list();
	
	Counter load(Long id);
	
	Long count();
	
	Counter loadByNum(Integer num);
	
	Long countByName(String name);
	
	List<Counter> pager(Integer pageSize,Long pageNum);
	
	List<Counter> pagerByName(Integer pageSize,Long pageNum,String name);
	
	
	
	
}
