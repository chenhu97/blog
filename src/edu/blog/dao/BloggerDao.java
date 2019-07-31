package edu.blog.dao;

import java.util.List;

import edu.blog.bean.*;

public interface BloggerDao {

	Long insert(Blogger bean);

	Long delete(Long id);

	Long update(Blogger bean);

	List<Blogger> list();
	
	List<Blogger> listByIsEnable(Long isEnable);

	Blogger load(Long id);

	Blogger loadByName(String name);
	
	Blogger loadByNickName(String nickName);
	
	Blogger loadByFolder(String blogFolder);

	Long count();

	Long countByName(String name);

	List<Blogger> pager(Long pageNum, Long pageSize);

	List<Blogger> pagerByName(String name, Long pageNum, Long pageSize);


}
