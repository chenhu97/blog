package edu.blog.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.DbFun;
import com.liuvei.common.SysFun;

import edu.blog.bean.News;
import edu.blog.dao.NewsDao;
import edu.util.DbUtil;

public class NewsDaoImpl implements NewsDao {

	@Override
	public Long insert(News bean) {
		// TODO Auto-generated method stub
		Long result = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Insert Into News ");
		sb.append(" ( ");
		sb.append(
				" CatId,Title,Author,Summary,PicPath, Status,Remark,SortNum,IsDeleted,CreateBy, UpdateBy,CreateOn,UpdateOn");
		sb.append(" ) ");
		sb.append(" values (?,?,?,?,?, ?,?,?,?,?, ?,?,?)");
		paramsList.add(bean.getCatId());
		paramsList.add(bean.getTitle());
		paramsList.add(bean.getAuthor());
		paramsList.add(bean.getSummary());
		paramsList.add(bean.getPicPath());
		paramsList.add(bean.getStatus());
		paramsList.add(bean.getRemark());
		paramsList.add(bean.getSortNum());
		paramsList.add(bean.getIsDeleted());
		paramsList.add(bean.getCreateBy());
		paramsList.add(bean.getUpdateBy());
		paramsList.add(bean.getCreateOn());
		paramsList.add(bean.getUpdateOn());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		try {
			conn = DbUtil.getConn();
			Long num = DbFun.update(conn, sql, params);
			if (num > 0) {
				sql = " Select @@identity";
				result = DbFun.queryScalarLong(conn, sql);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}

		return result;
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		Long result = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Delete From News");
		sb.append(" Where newsId = ? ");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		try {
			conn = DbUtil.getConn();
			result = DbFun.update(conn, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return result;
	}

	@Override
	public Long update(News bean) {
		// TODO Auto-generated method stub
		Long result = 0L;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Update News ");
		sb.append(" Set CatId = ?, Title = ?, Author = ?, Summary = ? ");
		sb.append(" ,PicPath = ?, Status = ?, Remark = ?, SortNum = ?");
		sb.append(" ,IsDeleted = ?, CreateBy = ?, UpdateBy = ?, CreateOn = ?,UpdateOn = ?");
		sb.append(" Where NewsId = ? ");
		paramsList.add(bean.getCatId());
		paramsList.add(bean.getTitle());
		paramsList.add(bean.getAuthor());
		paramsList.add(bean.getSummary());
		paramsList.add(bean.getPicPath());
		paramsList.add(bean.getStatus());
		paramsList.add(bean.getRemark());
		paramsList.add(bean.getSortNum());
		paramsList.add(bean.getIsDeleted());
		paramsList.add(bean.getCreateBy());
		paramsList.add(bean.getUpdateBy());
		paramsList.add(bean.getCreateOn());
		paramsList.add(bean.getUpdateOn());
		paramsList.add(bean.getNewsId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		try {
			conn = DbUtil.getConn();
			result = DbFun.update(conn, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return result;
	}

	@Override
	public List<News> list() {
		// TODO Auto-generated method stub
		List<News> list = new ArrayList<News>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From News ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			while (rs.next()) {
				list.add(toBean(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return list;
	}

	@Override
	public News load(Long id) {
		// TODO Auto-generated method stub
		News bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select N.*,C.CatName From News N");
		sb.append(" left join NewsCat C on N.CatId=C.CatId ");
		sb.append(" Where N.NewsId = ? ");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			if (rs.next()) {
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}

		return bean;
	}

	@Override
	public News loadByName(String name) {
		// TODO Auto-generated method stub
		News bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From News ");
		sb.append(" Where Title Like ? ");
		sb.append(" Order By NewsId Asc ");
		name = "%" + name + "%";
		paramsList.add(name);
		
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			if (rs.next()) {
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return bean;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		Long result = 0L;
		StringBuffer sb = new StringBuffer();

		sb.append("Select Count(1) From News");
		String sql = sb.toString();

		Connection conn = null;
		try {
			conn = DbUtil.getConn();
			result = DbFun.queryScalarLong(conn, sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return result;
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		Long result = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select Count(1) From News ");
		sb.append(" Where Title Like ? ");
		name = "%" + name + "%";
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		try {
			conn = DbUtil.getConn();
			result = DbFun.queryScalarLong(conn, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return result;
	}

	@Override
	public List<News> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<News> list = new ArrayList<News>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select N.*,C.CatName From News N");
		sb.append(" left join NewsCat C on N.CatId=C.CatId ");
		sb.append(" Order By N.NewsId Asc ");

		if (pageNum < 1) {
			pageNum = 1L;
		}
		if (pageSize < 1) {
			pageSize = 10L;
		}
		Long startIndex = (pageNum - 1) * pageSize;
		
		sb.append(" limit " + startIndex + "," + pageSize);
		
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			while (rs.next()) {
				list.add(toBean(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}

		return list;
	}

	@Override
	public List<News> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<News> list = new ArrayList<News>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select N.*,C.CatName From News N ");
		sb.append(" left join NewsCat C on N.CatId=C.CatId ");
		sb.append(" Where N.Title Like ?");
		sb.append(" Order By N.NewsId Asc ");
		name = "%" + name + "%";
		paramsList.add(name);

		if (pageNum < 1) {
			pageNum = 1L;
		}
		if (pageSize < 1) {
			pageSize = 10L;
		}
		Long startIndex = (pageNum - 1) * pageSize;
		sb.append(" limit " + startIndex + "," + pageSize);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			while (rs.next()) {
				list.add(toBean(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<News> listBySearch(Long catId, String name) {
		// TODO Auto-generated method stub
		List<News> list = new ArrayList<News>();
		List<Object> paramsList = new ArrayList<Object>();

		StringBuilder sb = new StringBuilder();
		sb.append(" Select * from News ");
		sb.append(" Where 1=1 ");
		if (catId != null && catId != 0) {
			sb.append(" and catId = ? ");
			paramsList.add(catId);
		}
		if (!SysFun.isNullOrEmpty(name)) {
			sb.append(" and title like ? ");
			name = "%" + name + "%";
			paramsList.add(name);
		}

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			while (rs.next()) {
				list.add(toBean(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return list;
	}

	@Override
	public Long countBySearch(Long catId, String name) {
		// TODO Auto-generated method stub
		Long result = 0L;

		List<Object> paramsList = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder();

		sb.append(" Select count(1) from news ");
		sb.append(" Where 1=1 ");

		if (catId != null && catId != 0) {
			sb.append(" and CatId = ? ");
			paramsList.add(catId);
		}
		if (!SysFun.isNullOrEmpty(name)) {
			sb.append(" and title like ? ");
			name = "%" + name + "%";
			paramsList.add(name);
		}
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;

		try {
			conn = DbUtil.getConn();
			result = DbFun.queryScalarLong(conn, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			DbUtil.close(conn);
		}

		return result;
	}

	@Override
	public List<News> pagerBySearch(Long catId, String name, Long pageSize, Long pageNum) {
		// TODO Auto-generated method stub
		List<News> list = new ArrayList<News>();
		List<Object> paramsList = new ArrayList<Object>();

		StringBuilder sb = new StringBuilder();

		// sb.append(" Select N.*,C.CatName From News N ");
		// sb.append(" left join NewsCat C on N.CatId=C.CatId ");
		sb.append(" Select * from News");
		sb.append(" Where 1=1 ");

		if (catId != null && catId != 0) {
			sb.append(" and CatId = ? ");
			paramsList.add(catId);
		}
		if (!SysFun.isNullOrEmpty(name)) {
			sb.append(" and title like ? ");
			name = "%" + name + "%";
			paramsList.add(name);
		}
		if (pageNum < 1) {
			pageNum = 1L;
		}
		if (pageSize < 1) {
			pageSize = 10L;
		}
		Long startIndex = (pageNum - 1) * pageSize;
		// sb.append(" Order By N.NewsId Asc ");
		sb.append(" limit " + startIndex + "," + pageSize);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			while (rs.next()) {
				list.add(toBeans(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<News> listByCatId_limit_n(Long catId, Long n) {
		// TODO Auto-generated method stub
		List<News> list = new ArrayList<News>();
		List<Object> paramsList = new ArrayList<Object>();

		StringBuilder sb = new StringBuilder();
		sb.append(" Select N.*,C.CatName from News N ");
		sb.append(" left join NewsCat C on N.CatId = C.CatId ");
		sb.append(" where 1=1 ");
		if (catId != null && catId != 0L) {
			sb.append("and C.CatId = ? ");
			paramsList.add(catId);
		}
		if (n == null || n < 0) {
			n = 1L;
		}
		sb.append(" Order By N.NewsId Asc ");
		sb.append(" limit " + n);
		
		
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			while (rs.next()) {
				list.add(toBeans(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return list;
	}

	public News toBean(ResultSet rs) {
		// TODO Auto-generated method stub
		News bean = new News();
		try {
			bean.setNewsId(rs.getLong("NewsId"));
			bean.setCatId(rs.getLong("CatId"));
			bean.setCatName(rs.getString("CatName"));
			bean.setTitle(rs.getString("Title"));
			bean.setAuthor(rs.getString("Author"));
			bean.setSummary(rs.getString("Summary"));
			bean.setPicPath(rs.getString("PicPath"));
			bean.setStatus(rs.getString("Status"));
			bean.setRemark(rs.getString("Remark"));
			bean.setSortNum(rs.getLong("SortNum"));
			bean.setIsDeleted(rs.getLong("IsDeleted"));
			bean.setCreateBy(rs.getLong("CreateBy"));
			bean.setUpdateBy(rs.getLong("UpdateBy"));
			bean.setCreateOn(rs.getDate("CreateOn"));
			bean.setUpdateOn(rs.getDate("UpdateOn"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return bean;
	}

	public News toBeans(ResultSet rs) {
		// TODO Auto-generated method stub
		News bean = new News();
		try {
			bean.setNewsId(rs.getLong("NewsId"));
			bean.setCatId(rs.getLong("CatId"));

			bean.setTitle(rs.getString("Title"));
			bean.setAuthor(rs.getString("Author"));
			bean.setSummary(rs.getString("Summary"));
			bean.setPicPath(rs.getString("PicPath"));
			bean.setStatus(rs.getString("Status"));
			bean.setRemark(rs.getString("Remark"));
			bean.setSortNum(rs.getLong("SortNum"));
			bean.setIsDeleted(rs.getLong("IsDeleted"));
			bean.setCreateBy(rs.getLong("CreateBy"));
			bean.setUpdateBy(rs.getLong("UpdateBy"));
			bean.setCreateOn(rs.getDate("CreateOn"));
			bean.setUpdateOn(rs.getDate("UpdateOn"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return bean;
	}

}
