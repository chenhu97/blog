package edu.blog.ui.cli;

import java.util.List;


import edu.blog.bean.ArticleCat;
import edu.blog.service.ArticleCatService;
import edu.blog.service.impl.*;

public class Start_ArticleCat {
	public static void main(String[] args) {
		list();
	}
	private static ArticleCatService articleService = new ArticleCatServiceImpl();
	public static void list() {
		// TODO Auto-generated method stub
		List<ArticleCat> list =articleService.pager(1l,4L);
		System.out.println("CatId\tCatName\tMemberId");
		for(ArticleCat item : list) {
			System.out.print(item.getCatId() + "\t");
			System.out.print(item.getCatName() + "\t");
			System.out.print(item.getCatId() + "\t");
		}
	}
}
