package edu.blog.dao;

import java.util.List;

import edu.blog.bean.*;

public interface NewsDao {
	Long insert(News bean);

	Long delete(Long id);

	Long update(News bean);

	List<News> list();
	
	List<News> listByCatId_limit_n(Long catId,Long n);

	News load(Long id);

	News loadByName(String name);

	Long count();

	Long countByName(String name);

	List<News> pager(Long pageNum, Long pageSize);

	List<News> pagerByName(String name, Long pageNum, Long pageSize);

	List<News> listBySearch(Long catId,String name);
	
	Long countBySearch(Long catId,String name);
	
	List<News> pagerBySearch(Long catId,String name,Long pageSize,Long pageNum);
	
}
