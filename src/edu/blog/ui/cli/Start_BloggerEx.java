package edu.blog.ui.cli;

import edu.blog.service.BloggerExService;
import edu.blog.service.impl.BloggerExServiceImpl;

import java.util.List;

import edu.blog.bean.*;

public class Start_BloggerEx {
	public static void main(String[] args) {
		list();
	}

	private static BloggerExService bloggerService = new BloggerExServiceImpl();

	public static void list() {
		// TODO Auto-generated method stub
		List<BloggerEx> list = bloggerService.pager(1L, 3L);
		System.out.println("FlowId\tMemberId\tContentType\tContent");
		for (BloggerEx item : list) {
			System.out.print(item.getFlowId() + "\t");
			System.out.print(item.getMemberId() + "\t");
			System.out.print(item.getContentType() + "\t");
			System.out.print(item.getContent() + "\n");
		}
	}

}
