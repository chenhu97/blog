package edu.blog.dao;

import java.util.List;

import edu.blog.bean.Article;

public interface ArticleDao {
	Long insert(Article bean);

	Long delete(Long id);

	Long update(Article bean);

	List<Article> list();

	Article load(Long id);

	Article loadByName(String name);

	Article loadWithCatName(Long id);

	Long count();

	Long countByName(String name);

	Long countBySearch(Long memberId, Long catId, String title, String folder);

	List<Article> pager(Long pageNum, Long pageSize);

	List<Article> pagerByName(String name, Long pageNum, Long pageSize);

	List<Article> pagerBySearch(Long memberId, Long catId, String title,String folder, Long pageNum, Long pageSize);
}
