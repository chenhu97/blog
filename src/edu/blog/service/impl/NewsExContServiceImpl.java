package edu.blog.service.impl;

import edu.blog.bean.NewsExCont;
import edu.blog.dao.NewsExContDao;
import edu.blog.dao.impl.NewsExContDaoImpl;
import edu.blog.service.NewsExContService;

public class NewsExContServiceImpl implements NewsExContService {
	public NewsExContDao newsExContDao = new NewsExContDaoImpl();
	
	@Override
	public Long insert(NewsExCont bean) {
		// TODO Auto-generated method stub
		return newsExContDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return newsExContDao.delete(id);
	}

	@Override
	public Long update(NewsExCont bean) {
		// TODO Auto-generated method stub
		return newsExContDao.update(bean);
	}

	@Override
	public NewsExCont loadByNewsId(Long NewsId) {
		// TODO Auto-generated method stub
		return newsExContDao.loadByNewsId(NewsId);
	}

	@Override
	public NewsExCont load(Long id) {
		// TODO Auto-generated method stub
		return newsExContDao.load(id);
	}

}
