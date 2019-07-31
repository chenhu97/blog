package edu.blog.service.impl;

import java.util.List;

import edu.blog.bean.ArticleCat;
import edu.blog.dao.ArticleCatDao;
import edu.blog.dao.impl.ArticleCatDaoImpl;
import edu.blog.service.ArticleCatService;

public class ArticleCatServiceImpl implements ArticleCatService {
	public ArticleCatDao articleCatDao = new ArticleCatDaoImpl();

	@Override
	public Long insert(ArticleCat bean) {
		// TODO Auto-generated method stub
		return articleCatDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return articleCatDao.delete(id);
	}

	@Override
	public Long update(ArticleCat bean) {
		// TODO Auto-generated method stub
		return articleCatDao.update(bean);
	}

	@Override
	public List<ArticleCat> list() {
		// TODO Auto-generated method stub
		return articleCatDao.list();
	}

	@Override
	public ArticleCat load(Long id) {
		// TODO Auto-generated method stub
		return articleCatDao.load(id);
	}

	@Override
	public ArticleCat loadByName(String name) {
		// TODO Auto-generated method stub
		return articleCatDao.loadByName(name);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return articleCatDao.count();
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return articleCatDao.countByName(name);
	}

	@Override
	public List<ArticleCat> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return articleCatDao.pager(pageNum, pageSize);
	}

	@Override
	public List<ArticleCat> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return articleCatDao.pagerByName(name, pageNum, pageSize);
	}

	@Override
	public Long countByMemberIdAndName(Long memberId, String name) {
		// TODO Auto-generated method stub
		return articleCatDao.countByMemberIdAndName(memberId, name);
	}

	@Override
	public List<ArticleCat> pagerByMemberIdAndName(Long memberId, String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return articleCatDao.pagerByMemberIdAndName(memberId, name, pageNum, pageSize);
	}

	@Override
	public List<ArticleCat> listByMemberId(Long id) {
		// TODO Auto-generated method stub
		return articleCatDao.listByMemberId(id);
	}

	@Override
	public List<ArticleCat> listByArticleId(Long id) {
		// TODO Auto-generated method stub
		return articleCatDao.listByArticleId(id);
	}



}
