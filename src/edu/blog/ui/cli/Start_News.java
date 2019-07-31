package edu.blog.ui.cli;

import java.util.List;

import edu.blog.bean.News;
import edu.blog.service.NewsService;
import edu.blog.service.impl.NewsServiceImpl;

public class Start_News {
	public static void main(String[] args) {
		list();
	}
	private static NewsService newsService = new NewsServiceImpl();
		
	public static void list() {
		// TODO Auto-generated method stub
		List<News> list = newsService.pager(2L, 5L);
		System.out.println("NewsId\tCatId\tTitle");
		
		for(News item : list) {
			System.out.print(item.getNewsId() + "\t" );
			System.out.print(item.getCatId() + "\t" );
			System.out.print(item.getTitle() + "\n");
		}
	}
}
