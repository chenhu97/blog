package edu.blog.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.DbFun;

import edu.blog.bean.Article;
import edu.blog.dao.ArticleDao;
import edu.util.DbUtil;

public class ArticleDaoImpl implements ArticleDao {

	@Override
	public Long insert(Article bean) {
		// TODO Auto-generated method stub
		Long result = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Insert Into Article ");
		sb.append(" ( ");
		sb.append(" Title,Folder,CatId,MemberId,NickName,PublishOn,UpdateOn,Content ");
		sb.append(" ) ");
		sb.append(" values (?,?,?,?, ?,?,?,?)");
		paramsList.add(bean.getTitle());
		paramsList.add(bean.getFolder());
		paramsList.add(bean.getCatId());
		paramsList.add(bean.getMemberId());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getPublishOn());
		paramsList.add(bean.getUpdateOn());
		paramsList.add(bean.getContent());

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

		sb.append(" Delete From Article");
		sb.append(" Where ArticleId = ? ");
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
	public Long update(Article bean) {
		// TODO Auto-generated method stub
		Long result = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Update Article ");
		sb.append(" Set Title = ?, Folder = ?, CatId = ?, MemberId = ? ");
		sb.append(" ,NickName = ?, PublishOn = ?, UpdateOn = ?, Content = ? ");
		sb.append(" Where ArticleId = ? ");

		paramsList.add(bean.getTitle());
		paramsList.add(bean.getFolder());
		paramsList.add(bean.getCatId());
		paramsList.add(bean.getMemberId());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getPublishOn());
		paramsList.add(bean.getUpdateOn());
		paramsList.add(bean.getContent());
		paramsList.add(bean.getArticleId());

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
	public List<Article> list() {
		// TODO Auto-generated method stub
		List<Article> list = new ArrayList<Article>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From Article ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			while (rs.next()) {
				list.add(toBeanEx(rs));
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
	public Article load(Long id) {
		// TODO Auto-generated method stub
		Article bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select * From Article ");
		sb.append(" Where ArticleId = ? ");
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
	public Article loadByName(String name) {
		// TODO Auto-generated method stub
		Article bean = null;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select * From Article ");
		sb.append(" Where NickName Like ? ");
		sb.append(" Order By ArticleId Asc ");
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
		sb.append("Select Count(1) From Article");

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
		sb.append(" Select Count(1) From Article ");
		sb.append(" Where NickName Like ? ");
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
	public List<Article> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Article> list = new ArrayList<Article>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		/**
		 * select a.* ,ac.CatName FROM article a INNER join articlecat ac ON a.CatId =
		 * ac.CatId
		 * 
		 */
		sb.append(" Select A.*,AC.CatName From Article A  ");
		sb.append(" inner join ArticleCat AC on ");
		sb.append(" A.CatId = AC.CatId ");
		sb.append(" Order By ArticleId Asc ");

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
				list.add(toBeanEx(rs));
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
	public List<Article> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		List<Article> list = new ArrayList<Article>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select * From Article ");
		sb.append(" Where NickName Like ?");
		sb.append(" Order By ArticleId Asc ");
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
	public Long countBySearch(Long memberId, Long catId, String title, String folder) {
		// TODO Auto-generated method stub
		Long result = 0L;
		StringBuffer sbSQL = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sbSQL.append(" Select count(1) from Article ");
		sbSQL.append(" Where 1=1 ");

		if (memberId != null) {
			sbSQL.append(" And MemberId = ? ");
			paramsList.add(memberId);
		}
		if (catId != null) {
			sbSQL.append(" And CatId = ? ");
			paramsList.add(catId);
		}
		if (title != null) {
			sbSQL.append("And Title like ? ");
			title = "%" + title + "%";
			paramsList.add(title);
		}
		if (folder != null) {
			sbSQL.append("And BlogFolder = ? ");
			paramsList.add(folder);
		}

		String sql = sbSQL.toString();
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
	public List<Article> pagerBySearch(Long memberId, Long catId, String title,String folder, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sbSQL = new StringBuffer();
		List<Article> list = new ArrayList<Article>();
		List<Object> paramsList = new ArrayList<Object>();

		sbSQL.append("Select a.*,ac.CatName From article a");
		sbSQL.append(" inner join articlecat ac On ");
		sbSQL.append(" a.catId = ac.catId ");
		sbSQL.append(" Where 1=1 ");

		if (memberId != null) {
			sbSQL.append(" And ac.MemberId = ? ");
			paramsList.add(memberId);
		}
		if (catId != null) {
			sbSQL.append(" And ac.CatId = ? ");
			paramsList.add(catId);
		}
		if (title != null) {
			sbSQL.append("And a.Title like ? ");
			title = "%" + title + "%";
			paramsList.add(title);
		}
		if(folder != null) {
			sbSQL.append("And a.folder = ? ");
			paramsList.add(folder);
		}

		if (pageNum < 1) {
			pageNum = 1L;
		}
		if (pageSize < 1) {
			pageSize = 10L;
		}

		Long startIndex = (pageNum - 1) * pageSize;
		sbSQL.append(" limit " + startIndex + "," + pageSize);

		String sql = sbSQL.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			while (rs.next()) {
				list.add(toBeanEx(rs));
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

	public Article toBean(ResultSet rs) {
		// TODO Auto-generated method stub
		Article bean = new Article();
		try {
			bean.setArticleId(rs.getLong("ArticleId"));
			bean.setTitle(rs.getString("Title"));
			bean.setFolder(rs.getString("Folder"));
			bean.setCatId(rs.getLong("CatId"));
			bean.setMemberId(rs.getLong("MemberId"));
			bean.setNickName(rs.getString("NickName"));
			bean.setPublishOn(rs.getDate("PublishOn"));
			bean.setUpdateOn(rs.getDate("UpdateOn"));
			bean.setContent(rs.getString("Content"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return bean;
	}

	public Article toBeanEx(ResultSet rs) {
		Article bean = new Article();
		try {
			bean.setArticleId(rs.getLong("ArticleId"));
			bean.setTitle(rs.getString("Title"));
			bean.setFolder(rs.getString("Folder"));
			bean.setCatId(rs.getLong("CatId"));
			bean.setMemberId(rs.getLong("MemberId"));
			bean.setNickName(rs.getString("NickName"));
			bean.setPublishOn(rs.getDate("PublishOn"));
			bean.setUpdateOn(rs.getDate("UpdateOn"));
			bean.setContent(rs.getString("Content"));
			bean.setCatName(rs.getString("CatName"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return bean;
	}

	@Override
	public Article loadWithCatName(Long id) {
		// TODO Auto-generated method stub
		Article bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select A.*,AC.CatName From Article A ");
		sb.append(" inner join ArticleCat AC ");
		sb.append(" On AC.CatId = A.CatId ");
		sb.append(" Where ArticleId = ? ");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DbUtil.getConn();
			rs = DbFun.query(conn, sql, params);
			if (rs.next()) {
				bean = toBeanEx(rs);
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
