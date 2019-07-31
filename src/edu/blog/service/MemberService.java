package edu.blog.service;

import java.util.List;

import edu.blog.bean.Member;

public interface MemberService {

	Long insert(Member bean);

	Long delete(Long id);

	Long update(Member bean);

	List<Member> list();

	Member load(Long id);

	Member loadByName(String name);

	Long count();

	Long countByName(String name);

	List<Member> pager(Long pageNum, Long pageSize);

	List<Member> pagerByName(String name, Long pageNum, Long pageSize);


}
