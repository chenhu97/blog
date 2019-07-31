package edu.blog.service.impl;

import java.util.List;

import edu.blog.bean.NewsCat;
import edu.blog.dao.NewsCatDao;
import edu.blog.dao.impl.NewsCatDaoImpl;
import edu.blog.service.NewsCatService;

public class NewsCatServiceImpl implements NewsCatService {
	public NewsCatDao newsCatDao = new NewsCatDaoImpl();
	@Override
	public Long insert(NewsCat bean) {
		// TODO Auto-generated method stub
		return newsCatDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return newsCatDao.delete(id);
	}

	@Override
	public Long update(NewsCat bean) {
		// TODO Auto-generated method stub
		return newsCatDao.update(bean);
	}

	@Override
	public NewsCat load(Long id) {
		// TODO Auto-generated method stub
		return newsCatDao.load(id);
	}

	@Override
	public List<NewsCat> list() {
		// TODO Auto-generated method stub
		return newsCatDao.list();
	}

	@Override
	public NewsCat loadByName(String name) {
		// TODO Auto-generated method stub
		return newsCatDao.loadByName(name);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return newsCatDao.count();
	}

	@Override
	public List<NewsCat> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return newsCatDao.pager(pageNum, pageSize);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return newsCatDao.countByName(name);
	}

	@Override
	public List<NewsCat> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return newsCatDao.pagerByName(name, pageNum, pageSize);
	}

}
