package edu.blog.service.impl;

import java.util.List;

import edu.blog.bean.BloggerEx;
import edu.blog.dao.BloggerExDao;
import edu.blog.dao.impl.BloggerExDaoImpl;
import edu.blog.service.BloggerExService;

public class BloggerExServiceImpl implements BloggerExService {
	public BloggerExDao bloggerExDao = new BloggerExDaoImpl();

	@Override
	public Long insert(BloggerEx bean) {
		// TODO Auto-generated method stub
		return bloggerExDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return bloggerExDao.delete(id);
	}

	@Override
	public Long update(BloggerEx bean) {
		// TODO Auto-generated method stub
		return bloggerExDao.update(bean);
	}

	@Override
	public List<BloggerEx> list() {
		// TODO Auto-generated method stub
		return bloggerExDao.list();
	}

	@Override
	public BloggerEx load(Long id) {
		// TODO Auto-generated method stub
		return bloggerExDao.load(id);
	}

	@Override
	public BloggerEx loadById(Long id) {
		// TODO Auto-generated method stub
		return bloggerExDao.loadById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return bloggerExDao.count();
	}

	@Override
	public Long countById(Long id) {
		// TODO Auto-generated method stub
		return bloggerExDao.countById(id);
	}

	@Override
	public List<BloggerEx> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return bloggerExDao.pager(pageNum, pageSize);
	}

	@Override
	public List<BloggerEx> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return bloggerExDao.pagerByName(name, pageNum, pageSize);
	}

	@Override
	public BloggerEx loadBySearch(Long memberId, String contentType) {
		// TODO Auto-generated method stub
		return bloggerExDao.loadBySearch(memberId, contentType);

	}

	@Override
	public Boolean edit(Long memberId, String contentType, String content) {
		// TODO Auto-generated method stub
		Boolean vIsOk = false;
		Long num = 0L;
		BloggerEx bean = loadBySearch(memberId, contentType);
		if (bean == null) {
			bean = new BloggerEx();
			bean.setMemberId(memberId);
			bean.setContentType(contentType);
			bean.setContent(content);
			num = bloggerExDao.insert(bean);
		} else {
			bean.setContent(content);
			num = bloggerExDao.update(bean);
		}
		if (num > 0) {
			vIsOk = true;
		}
		return vIsOk;
	}

}
