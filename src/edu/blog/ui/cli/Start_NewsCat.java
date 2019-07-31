package edu.blog.ui.cli;

import java.util.List;

import edu.blog.bean.NewsCat;
import edu.blog.service.NewsCatService;
import edu.blog.service.impl.NewsCatServiceImpl;

public class Start_NewsCat {
	public static void main(String[] args) {
		list();
	} 
	
	private static NewsCatService newsCatService = new NewsCatServiceImpl();
	public static void list() {
		// TODO Auto-generated method stub
		List<NewsCat> list  = newsCatService.pager(2L, 5L);
		System.out.println("CatId\tCatName\tCatDesc");
		for(NewsCat item : list) {
			System.out.print(item.getCatId() + "\t" );
			System.out.print(item.getCatName() + "\t");
			System.out.print(item.getCatDesc() + "\n");
		}
	}
}
