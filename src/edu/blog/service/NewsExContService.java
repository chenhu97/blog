package edu.blog.service;

import edu.blog.bean.*;

public interface NewsExContService {

	Long insert(NewsExCont bean);

	Long delete(Long id);

	Long update(NewsExCont bean);

	NewsExCont load(Long id);
	
	NewsExCont loadByNewsId(Long NewsId);
}
