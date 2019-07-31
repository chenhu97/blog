package edu.blog.service.impl;

import java.util.List;

import edu.blog.bean.Member;
import edu.blog.dao.MemberDao;
import edu.blog.dao.impl.MemberDaoImpl;
import edu.blog.service.MemberService;

public class MemberServiceImpl implements MemberService {
	public MemberDao memberDao = new  MemberDaoImpl();
	@Override
	public Long insert(Member bean) {
		// TODO Auto-generated method stub
		return memberDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return memberDao.delete(id);
	}

	@Override
	public Long update(Member bean) {
		// TODO Auto-generated method stub
		return memberDao.update(bean);
	}

	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		return memberDao.list();
	}

	@Override
	public Member load(Long id) {
		// TODO Auto-generated method stub
		return memberDao.load(id);
	}

	@Override
	public Member loadByName(String name) {
		// TODO Auto-generated method stub
		return memberDao.loadByName(name);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return memberDao.count();
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return memberDao.countByName(name);
	}

	@Override
	public List<Member> pager(Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return memberDao.pager(pageNum, pageSize);
	}

	@Override
	public List<Member> pagerByName(String name, Long pageNum, Long pageSize) {
		// TODO Auto-generated method stub
		return memberDao.pagerByName(name, pageNum, pageSize);
	}

}
