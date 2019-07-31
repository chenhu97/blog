package edu.blog.dao;

import java.util.List;

import edu.blog.bean.*;



public interface ArticleCatDao {

	Long insert(ArticleCat bean);

	Long delete(Long id);

	Long update(ArticleCat bean);

	List<ArticleCat> list();

	ArticleCat load(Long id);

	ArticleCat loadByName(String name);
		
	List<ArticleCat> listByMemberId(Long id);

	List<ArticleCat> listByArticleId(Long id);
	
	Long count(); 

	Long countByName(String name);

	List<ArticleCat> pager(Long pageNum, Long pageSize);

	List<ArticleCat> pagerByName(String name, Long pageNum, Long pageSize);
	
	Long countByMemberIdAndName(Long memberId,String name);
	
	List<ArticleCat> pagerByMemberIdAndName(Long memberId,String name,Long pageNum,Long pageSize);

}
