package edu.blog.dao;

import java.util.List;

import edu.blog.bean.*;

public interface MemberDao {
	Long insert(Member bean);

	Long delete(Long id);

	Long update(Member bean);

	List<Member> list();

	Member load(Long id);

	Member loadByName(String name);

	Long count();//分页相关

	List<Member> pager(Long pageNum, Long pageSize);//分页相关

	Long countByName(String name);//搜索分页
	
	List<Member> pagerByName(String name, Long pageNum, Long pageSize);//搜索分页

}
