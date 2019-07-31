package edu.blog.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.*;

import edu.blog.bean.*;
import edu.blog.dao.*;


import edu.util.DbUtil;

public class NewsCatDaoImpl implements NewsCatDao {

	@Override
	public Long insert(NewsCat bean) {
		// TODO Auto-generated method stub
		Long result = 0L;
		// 0)定义变量
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		// 1)组合sql
		sb.append(" Insert Into NewsCat ");
		sb.append(" ( ");
		sb.append(" CatName,CatDesc,Status,IsDeleted,CreateBy,UpdateBy,CreateOn,UpdateOn ");
		sb.append(" ) ");
		sb.append(" values(?,?, ?,?, ?,?, ?,?)");
		// 2)添加参数
		paramsList.add(bean.getCatName());
		paramsList.add(bean.getCatDesc());
		paramsList.add(bean.getStatus());
		paramsList.add(bean.getIsDeleted());
		paramsList.add(bean.getCreateBy());
		paramsList.add(bean.getUpdateBy());
		paramsList.add(bean.getCreateOn());
		paramsList.add(bean.getUpdateOn());
		// 3)转换类型
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		try {
			// 4)取得连接对象
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

		sb.append(" Delete From NewsCat");
		sb.append(" Where CatId = ? ");

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
	public Long update(NewsCat bean) {
		// TODO Auto-generated method stub
		Long result = 0L;
		// 0)定义变量
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		// 1)组合SQL
		sb.append(" Update NewsCat ");
		sb.append(" Set CatName = ?, CatDesc = ?, Status = ?, isDeleted = ? ");
		sb.append(" ,CreateBy = ? , UpdateBy = ?, CreateOn = ?, UpdateOn = ? ");
		sb.append(" Where CatId = ? ");
		// 2)添加参数
		paramsList.add(bean.getCatName());
		paramsList.add(bean.getCatDesc());
		paramsList.add(bean.getStatus());
		paramsList.add(bean.getIsDeleted());
		paramsList.add(bean.getCreateBy());
		paramsList.add(bean.getUpdateBy());
		paramsList.add(bean.getCreateOn());
		paramsList.add(bean.getUpdateOn());
		paramsList.add(bean.getCatId());
		// 3)转换类型
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;

		try {
			// 4)取得连接对象
			conn = DbUtil.getConn();
			// 5)执行sql语句
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
	public NewsCat load(Long id) {
		// TODO Auto-generated method stub
		NewsCat bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From NewsCat ");
		sb.append(" Where CatId = ? ");
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
	public List<NewsCat> list() {
		// TODO Auto-generated method stub
		List<NewsCat> list = new ArrayList<NewsCat>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From NewsCat ");

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
	public NewsCat loadByName(String name) {
		// TODO Auto-generated method stub
		NewsCat bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From NewsCat ");
		sb.append(" Where CatName Like ? ");
		sb.append(" Order By CatId Asc ");

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
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select Count(1) From NewsCat");
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
	public List<NewsCat> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<NewsCat> list = new ArrayList<NewsCat>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From NewsCat ");
		sb.append(" Order By CatId Asc");

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
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		Long result = 0L;
		
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select Count(1) From NewsCat ");
		sb.append(" Where CatName Like ? ");
		name ="%"+ name +"%";
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
		}finally {
			DbUtil.close(conn);
		}
		return result;
	}

	@Override
	public List<NewsCat> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<NewsCat> list = new ArrayList<NewsCat>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select * From NewsCat ");
		sb.append(" Where CatName Like ?");
		sb.append(" Order By CatId Asc ");
		paramsList.add("%" + name + "%");
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

	private NewsCat toBean(ResultSet rs) {
		// TODO Auto-generated method stub
		NewsCat bean = new NewsCat();
		try {
			bean.setCatId(rs.getLong("CatId"));
			bean.setCatName(rs.getString("CatName"));
			bean.setCatDesc(rs.getString("CatDesc"));
			bean.setStatus(rs.getString("Status"));
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
