package edu.blog.ui.cli;

import java.util.List;

import edu.blog.bean.Article;
import edu.blog.service.ArticleService;
import edu.blog.service.impl.ArticleServiceImpl;

public class Start_Article {
	public static void main(String[] args) {
		list();
	}
	private static ArticleService articleService = new ArticleServiceImpl();
	public static void list() {
		// TODO Auto-generated method stub
		List<Article> list = articleService.pager(1L, 3L);
		System.out.println("ArticleId\tTitle\tFolder\tCatId\tMemberId\tNickName\tPublishOn\tUpdateOn\tContent");
		for(Article item : list) {
			System.out.print(item.getArticleId() + "\t");
			System.out.print(item.getTitle() + "\t");
			System.out.print(item.getFolder() + "\t");
			System.out.print(item.getCatId() + "\t");
			System.out.print(item.getMemberId() + "\t");
			System.out.print(item.getNickName() + "\t");
			System.out.print(item.getPublishOn() + "\t");
			System.out.print(item.getUpdateOn() + "\t");
			System.out.print(item.getContent() + "\n");
		}
	}  
}
