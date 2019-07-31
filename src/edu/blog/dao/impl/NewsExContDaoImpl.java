package edu.blog.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.DbFun;

import edu.blog.bean.*;
import edu.blog.dao.*;
import edu.util.DbUtil;

public class NewsExContDaoImpl implements NewsExContDao {

	@Override
	public Long insert(NewsExCont bean) {
		// TODO Auto-generated method stub

		Long result = 0L;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into NewsExCont ");
		sb.append(" ( ");
		sb.append(" NewsId,Content,Status,Remark,SortNum,IsDeleted,CreateBy,UpdateBy,CreateOn,UpdateOn ");
		sb.append(" ) ");
		sb.append(" values(?,?,?,?,?, ?,?,?,?,?) ");
		paramsList.add(bean.getNewsId());
		paramsList.add(bean.getContent());
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

		sb.append(" Delete From NewsExCont");
		sb.append(" Where Id = ? ");

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
	public Long update(NewsExCont bean) {
		// TODO Auto-generated method stub
		Long result = 0L;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Update NewsExCont ");
		sb.append(" Set NewsId = ?,Content = ?,Status = ?,Remark = ?,SortNum = ? ");
		sb.append(" ,IsDeleted = ?,CreateBy = ?,UpdateBy = ?,CreateOn = ?,UpdateOn = ? ");
		sb.append(" Where Id = ? ");
		paramsList.add(bean.getNewsId());
		paramsList.add(bean.getContent());
		paramsList.add(bean.getStatus());
		paramsList.add(bean.getRemark());
		paramsList.add(bean.getSortNum());
		paramsList.add(bean.getIsDeleted());
		paramsList.add(bean.getCreateBy());
		paramsList.add(bean.getUpdateBy());
		paramsList.add(bean.getCreateOn());
		paramsList.add(bean.getUpdateOn());
		paramsList.add(bean.getId());

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
	public NewsExCont loadByNewsId(Long NewsId) {
		// TODO Auto-generated method stub
		NewsExCont bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From NewsExCont  ");
		sb.append(" Where NewsId = ? ");
		sb.append(" Order By Id Asc ");

		paramsList.add(NewsId);

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

	public NewsExCont toBean(ResultSet rs) {
		NewsExCont bean = new NewsExCont();

		try {
			bean.setId(rs.getLong("Id"));
			bean.setNewsId(rs.getLong("NewsId"));
			bean.setContent(rs.getString("Content"));
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

	@Override
	public NewsExCont load(Long id) {
		// TODO Auto-generated method stub
		NewsExCont bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From NewsExCont ");
		sb.append(" Where Id = ? ");
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

}
