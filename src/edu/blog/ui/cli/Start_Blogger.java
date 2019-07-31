package edu.blog.ui.cli;

import java.util.List;

import edu.blog.bean.Blogger;
import edu.blog.service.BloggerService;
import edu.blog.service.impl.BloggerServiceImpl;

public class Start_Blogger {
	public static void main(String[] args) {
		list();
	}

	private static BloggerService bloggerService = new BloggerServiceImpl();

	public static void list() {
		// TODO Auto-generated method stub
		List<Blogger> list = bloggerService.pager(1L, 5L);
		System.out.println("MemberId\tNickName\tBlogTitle\tBlogTitleEx\tBlogFolder\tIsEnable");
		for (Blogger item : list) {
			System.out.print(item.getMemberId() + "\t");
			System.out.print(item.getNickName() + "\t");
			System.out.print(item.getBlogTitle() + "\t");
			System.out.print(item.getBlogTitleEx() + "\t");
			System.out.print(item.getBlogFolder() + "\t");
			System.out.print(item.getIsEnable() + "\n");
		}
	}
}
