package edu.blog.ui.cli;

import java.util.List;
import edu.blog.bean.*;
import edu.blog.service.MemberService;
import edu.blog.service.impl.MemberServiceImpl;

public class Start_Member {
	public static void main(String[] args) {
		list();
	}
	private static MemberService memberService = new MemberServiceImpl(); 
	public static void list() {
		// TODO Auto-generated method stub
		List<Member> list = memberService.pager(1L,5L);
		System.out.println("UserId\tUserName\tUserPass");
		for(Member item : list) {
			System.out.print(item.getUserId() + "\t" );
			System.out.print(item.getUserName() + "\t");
			System.out.print(item.getUserPass() + "\n");
			
		}
	} 
}
