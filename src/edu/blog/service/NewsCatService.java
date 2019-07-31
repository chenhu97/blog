package edu.blog.service;

import java.util.List;

import edu.blog.bean.NewsCat;

public interface NewsCatService {


	Long insert(NewsCat bean);

	Long delete(Long id);

	Long update(NewsCat bean);

	NewsCat load(Long id);

	List<NewsCat> list();

	NewsCat loadByName(String name);

	Long count();

	List<NewsCat> pager(Long pageNum, Long pageSize);

	Long countByName(String name);

	List<NewsCat> pagerByName(String name, Long pageNum, Long pageSize);


}
