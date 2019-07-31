package edu.blog.service;

import java.util.List;

import edu.blog.bean.*;

public interface BloggerExService {

	Long insert(BloggerEx bean);

	Long delete(Long id);

	Long update(BloggerEx bean);

	List<BloggerEx> list();

	BloggerEx load(Long id);

	BloggerEx loadById(Long id);

	Long count();

	Long countById(Long id);

	List<BloggerEx> pager(Long pageNum, Long pageSize);

	List<BloggerEx> pagerByName(String name, Long pageNum, Long pageSize);

	BloggerEx loadBySearch(Long memberId, String contentType);

	Boolean edit(Long memberId, String contentType, String content);
}
