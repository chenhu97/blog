package edu.blog.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.*;

import edu.blog.bean.Member;
import edu.blog.dao.MemberDao;
import edu.util.DbUtil;

public class MemberDaoImpl implements MemberDao {

	@Override
	public Long insert(Member bean) {
		// TODO Auto-generated method stub
		Long result = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Insert Into Member ");
		sb.append(" ( ");
		sb.append(" UserName,UserPass,NickName,Email,Mobile, MyId,MyIdKey,RegIp,RegDate,LastLoginIp, LastLoginTime,Salt,Secques,Status,Remark, SortNum,IsDeleted,CreateBy,UpdateBy,CreateOn, UpdateOn");
		sb.append(" ) ");
		sb.append(" values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?) ");

		paramsList.add(bean.getUserName());
		paramsList.add(bean.getUserPass());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getEmail());
		paramsList.add(bean.getMobile());
		
		paramsList.add(bean.getMyId());
		paramsList.add(bean.getMyIdKey());
		paramsList.add(bean.getRegIp());
		paramsList.add(bean.getRegDate());
		paramsList.add(bean.getLastLoginIp());
		
		paramsList.add(bean.getLastLoginTime());
		paramsList.add(bean.getSalt());
		paramsList.add(bean.getSecques());
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
		
		sb.append(" Delete From Member");
		sb.append(" Where UserId = ? ");
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
	public Long update(Member bean) {
		// TODO Auto-generated method stub
		Long result = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Update Member ");
		sb.append(" Set UserName = ?, UserPass = ?, NickName = ?, Email = ?");
		sb.append(" ,Mobile = ?, MyId = ?, MyIdKey = ?, RegIp = ?");
		sb.append(" ,RegDate = ?, LastLoginIp = ?, LastLoginTime = ?, Salt = ?");
		sb.append(" ,Secques = ?,Status = ?, Remark = ?, SortNum = ?,IsDeleted = ?");
		sb.append(" ,CreateBy = ?, UpdateBy = ?, CreateOn = ?,UpdateOn = ? ");
		sb.append(" Where UserId = ? ");
		paramsList.add(bean.getUserName());
		paramsList.add(bean.getUserPass());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getEmail());
		paramsList.add(bean.getMobile());
		paramsList.add(bean.getMyId());
		paramsList.add(bean.getMyIdKey());
		paramsList.add(bean.getRegIp());
		paramsList.add(bean.getRegDate());
		paramsList.add(bean.getLastLoginIp());
		paramsList.add(bean.getLastLoginTime());
		paramsList.add(bean.getSalt());
		paramsList.add(bean.getSecques());
		paramsList.add(bean.getStatus());
		paramsList.add(bean.getRemark());
		paramsList.add(bean.getSortNum());
		paramsList.add(bean.getIsDeleted());
		paramsList.add(bean.getCreateBy());
		paramsList.add(bean.getUpdateBy());
		paramsList.add(bean.getCreateOn());
		paramsList.add(bean.getUpdateOn());
		paramsList.add(bean.getUserId());

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
	public List<Member> list() {
		// TODO Auto-generated method stub
		List<Member> list = new ArrayList<Member>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From Member ");
		
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
	public Member load(Long id) {
		// TODO Auto-generated method stub
		Member bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From Member ");
		sb.append(" Where UserId = ? ");
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
	public Member loadByName(String name) {
		// TODO Auto-generated method stub
		Member bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From Member ");
		sb.append(" Where UserName Like ? ");
		sb.append(" Order By UserId Asc ");
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
		sb.append("Select Count(1) From Member");
		
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
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		Long result = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select Count(1) From Member ");
		sb.append(" Where UserName Like ? ");
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
	public List<Member> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Member> list = new ArrayList<Member>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From Member");
		sb.append(" Order By UserId Asc ");

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
	public List<Member> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Member> list = new ArrayList<Member>();
		
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		
		sb.append(" Select * From Member ");
		sb.append(" Where UserName Like ?");
		sb.append(" Order By UserId Asc ");
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

	public Member toBean(ResultSet rs) {
		// TODO Auto-generated method stub
		Member bean = new Member();
		try {
			bean.setUserId(rs.getLong("UserId"));
			bean.setUserName(rs.getString("UserName"));
			bean.setUserPass(rs.getString("UserPass"));
			bean.setNickName(rs.getString("NickName"));
			bean.setEmail(rs.getString("Email"));
			bean.setMobile(rs.getString("Mobile"));
			bean.setMyId(rs.getString("MyId"));
			bean.setMyIdKey(rs.getString("MyIdKey"));
			bean.setRegIp(rs.getString("RegIp"));
			bean.setRegDate(rs.getDate("RegDate"));
			bean.setLastLoginIp(rs.getString("LastLoginIp"));
			bean.setLastLoginTime(rs.getDate("LastLoginTime"));
			bean.setSalt(rs.getString("Salt"));
			bean.setSecques(rs.getString("Secques"));
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
