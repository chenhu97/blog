package edu.blog.service.impl;

import java.util.List;

import edu.blog.bean.News;
import edu.blog.bean.NewsExCont;
import edu.blog.dao.NewsDao;
import edu.blog.dao.NewsExContDao;
import edu.blog.dao.impl.NewsDaoImpl;
import edu.blog.dao.impl.NewsExContDaoImpl;
import edu.blog.service.NewsService;

public class NewsServiceImpl implements NewsService {
	public NewsDao newsDao = new NewsDaoImpl();
	public NewsExContDao newsExContDao = new NewsExContDaoImpl();

	@Override
	public Long insert(News bean, String content) {
		// TODO Auto-generated method stub
		Long result = 0L;
		Long id = newsDao.insert(bean);

		if (id > 0) {
			NewsExCont beanEx = new NewsExCont();

			beanEx.setNewsId(id);
			beanEx.setContent(content);

			result = newsExContDao.insert(beanEx);

		}

		return result;
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return newsDao.delete(id);
	}

	@Override
	public Long update(News bean, String content) {
		// TODO Auto-generated method stub
		Long result = 0L;
		Long result2 = newsDao.update(bean);
		if (result2 > 0) {
			NewsExCont beanEx = newsExContDao.loadByNewsId(bean.getNewsId());
			beanEx.setContent(content);
			result = newsExContDao.update(beanEx);
		}

		return result;
	}

	@Override
	public List<News> list() {
		// TODO Auto-generated method stub
		return newsDao.list();
	}

	@Override
	public News load(Long id) {
		// TODO Auto-generated method stub
		return newsDao.load(id);
	}

	@Override
	public News loadByName(String name) {
		// TODO Auto-generated method stub
		return newsDao.loadByName(name);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return newsDao.count();
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return newsDao.countByName(name);
	}

	@Override
	public List<News> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return newsDao.pager(pageNum, pageSize);
	}

	@Override
	public List<News> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return newsDao.pagerByName(name, pageNum, pageSize);
	}

	@Override
	public List<News> listBySearch(Long catId, String name) {
		// TODO Auto-generated method stub
		return newsDao.listBySearch(catId, name);
	}

	@Override
	public Long countBySearch(Long catId, String name) {
		// TODO Auto-generated method stub
		return newsDao.countBySearch(catId, name);
	}

	@Override
	public List<News> pagerBySearch(Long catId, String name, Long pageSize, Long pageNum) {
		// TODO Auto-generated method stub
		return newsDao.pagerBySearch(catId, name, pageSize, pageNum);
	}

	@Override
	public List<News> listByCatId_limit_n(Long catId, Long n) {
		// TODO Auto-generated method stub
		return newsDao.listByCatId_limit_n(catId, n);
	}

}
