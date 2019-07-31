package edu.blog.service.impl;

import java.util.List;

import edu.blog.bean.Counter;
import edu.blog.dao.CounterDao;
import edu.blog.dao.impl.CounterDaoImpl;
import edu.blog.service.CounterService;

public class CounterServiceImpl implements CounterService {
	public CounterDao counterDao = new CounterDaoImpl();
	@Override
	public Long insert(Counter bean) {
		// TODO Auto-generated method stub
		return counterDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return counterDao.delete(id);
	}

	@Override
	public Long update(Counter bean) {
		// TODO Auto-generated method stub
		return counterDao.update(bean);
	}

	@Override
	public List<Counter> list() {
		// TODO Auto-generated method stub
		return counterDao.list();
	}

	@Override
	public Counter load(Long id) {
		// TODO Auto-generated method stub
		return counterDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return counterDao.count();
	}

	@Override
	public Counter loadByNum(Integer num) {
		// TODO Auto-generated method stub
		return counterDao.loadByNum(num);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return counterDao.countByName(name);
	}

	@Override
	public List<Counter> pager(Integer pageSize, Long pageNum) {
		// TODO Auto-generated method stub
		return counterDao.pager(pageSize, pageNum);
	}

	@Override
	public List<Counter> pagerByName(Integer pageSize, Long pageNum, String name) {
		// TODO Auto-generated method stub
		return counterDao.pagerByName(pageSize, pageNum, name);
	}

	@Override
	public Counter readCount() {
		// TODO Auto-generated method stub
		Counter item = null;
		item = load(1L);
		if(item==null) {
			//不存在,则添加
			item = new Counter();
			item.setCountId(1L);
			item.setNum(0L);
			insert(item);
		}
		return item;
	}

	@Override
	public Long writeCount(Counter bean) {
		// TODO Auto-generated method stub
		Counter item = null;
		item = load(1L);
		if(item==null) {
			item = new Counter();
			item.setCountId(1L);
			item.setNum(0L);
			insert(item);
		}
		item.setNum(bean.getNum());
		return update(item);
	}

}
