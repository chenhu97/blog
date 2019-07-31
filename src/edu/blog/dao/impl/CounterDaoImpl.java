package edu.blog.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.DbFun;

import edu.blog.bean.Counter;
import edu.blog.dao.CounterDao;
import edu.util.DbUtil;

public class CounterDaoImpl implements CounterDao {

	@Override
	public Long insert(Counter bean) {
		// TODO Auto-generated method stub
		Long result = 0L;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Insert into Counter ");
		sb.append("(");
		sb.append(" num");
		sb.append(")");
		sb.append("values(?)");
		paramsList.add(bean.getNum());

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
		return null;
	}

	@Override
	public Long update(Counter bean) {
		// TODO Auto-generated method stub
		Long result = 0L;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append("Update Counter ");
		sb.append(" Set Num = ?");
		sb.append(" where CounterId = ? ");
		paramsList.add(bean.getNum());
		paramsList.add(bean.getCountId());

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
	public List<Counter> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Counter load(Long id) {
		// TODO Auto-generated method stub
		Counter bean = null;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		
		sb.append("Select * from counter ");
		sb.append(" where CounterId = ?");
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
		}finally {
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
		sb.append("Select Count(1) From counter");

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
	public Counter loadByNum(Integer num) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Counter> pager(Integer pageSize, Long pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Counter> pagerByName(Integer pageSize, Long pageNum, String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public Counter toBean(ResultSet rs) {
		Counter bean = new Counter();
		try {
			bean.setCountId(rs.getLong("CounterId"));
			bean.setNum(rs.getLong("Num"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return bean;
	}

}
