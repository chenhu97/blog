package edu.blog.service.impl;

import java.util.List;

import edu.blog.bean.Blogger;
import edu.blog.dao.BloggerDao;
import edu.blog.dao.impl.BloggerDaoImpl;
import edu.blog.service.BloggerService;

public class BloggerServiceImpl implements BloggerService {
	public BloggerDao bloggerDao = new BloggerDaoImpl();
	@Override
	public Long insert(Blogger bean) {
		// TODO Auto-generated method stub
		return bloggerDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return bloggerDao.delete(id);
	}

	@Override
	public Long update(Blogger bean) {
		// TODO Auto-generated method stub
		return bloggerDao.update(bean);
	}

	@Override
	public List<Blogger> list() {
		// TODO Auto-generated method stub
		return bloggerDao.list();
	}

	@Override
	public Blogger load(Long id) {
		// TODO Auto-generated method stub
		return bloggerDao.load(id);
	}

	@Override
	public Blogger loadByName(String name) {
		// TODO Auto-generated method stub
		return bloggerDao.loadByName(name);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return bloggerDao.count();
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return bloggerDao.countByName(name);
	}

	@Override
	public List<Blogger> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return bloggerDao.pager(pageNum, pageSize);
	}

	@Override
	public List<Blogger> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return bloggerDao.pagerByName(name, pageNum, pageSize);
	}

	@Override
	public List<Blogger> listByIsEnable(Long isEnable) {
		// TODO Auto-generated method stub
		return bloggerDao.listByIsEnable(isEnable);
	}

	@Override
	public Blogger loadByNickName(String nickName) {
		// TODO Auto-generated method stub
		return bloggerDao.loadByNickName(nickName);
	}

	@Override
	public Blogger loadByFolder(String blogFolder) {
		// TODO Auto-generated method stub
		return bloggerDao.loadByFolder(blogFolder);
	}

}
