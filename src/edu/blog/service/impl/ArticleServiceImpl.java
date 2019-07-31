package edu.blog.service.impl;

import java.util.List;

import edu.blog.bean.Article;
import edu.blog.dao.ArticleDao;
import edu.blog.dao.impl.ArticleDaoImpl;
import edu.blog.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
	public ArticleDao articleDao = new ArticleDaoImpl();

	@Override
	public Long insert(Article bean) {
		// TODO Auto-generated method stub
		return articleDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return articleDao.delete(id);
	}

	@Override
	public Long update(Article bean) {
		// TODO Auto-generated method stub
		return articleDao.update(bean);
	}

	@Override
	public List<Article> list() {
		// TODO Auto-generated method stub
		return articleDao.list();
	}

	@Override
	public Article load(Long id) {
		// TODO Auto-generated method stub
		return articleDao.load(id);
	}

	@Override
	public Article loadByName(String name) {
		// TODO Auto-generated method stub
		return articleDao.loadByName(name);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return articleDao.count();
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return articleDao.countByName(name);
	}

	@Override
	public List<Article> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return articleDao.pager(pageNum, pageSize);
	}

	@Override
	public List<Article> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return articleDao.pagerByName(name, pageNum, pageSize);
	}

	@Override
	public Long countBySearch(Long memberId, Long catId, String title, String folder) {
		// TODO Auto-generated method stub
		return articleDao.countBySearch(memberId, catId, title, folder);
	}

	@Override
	public List<Article> pagerBySearch(Long memberId, Long catId, String title, String folder, Long pageNum,
			Long pageSize) {
		// TODO Auto-generated method stub
		return articleDao.pagerBySearch(memberId, catId, title, folder,pageNum, pageSize);
	}

	@Override
	public Article loadWithCatName(Long id) {
		// TODO Auto-generated method stub
		return articleDao.loadWithCatName(id);
	}

}
