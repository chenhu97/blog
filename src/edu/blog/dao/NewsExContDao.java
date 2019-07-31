package edu.blog.dao;

import edu.blog.bean.*;

public interface NewsExContDao {

	Long insert(NewsExCont bean);

	Long delete(Long id);

	Long update(NewsExCont bean);

	NewsExCont load(Long id);

	NewsExCont loadByNewsId(Long NewsId);
}
