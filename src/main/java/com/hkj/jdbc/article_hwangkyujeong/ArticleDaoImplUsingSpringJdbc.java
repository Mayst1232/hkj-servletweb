package com.hkj.jdbc.article_hwangkyujeong;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 인터페이스 MemberDao의 구현체. SpringJdbc를 사용해서 구현
 * 
 * @author HKJ
 */
@Repository("articleDao")
public class ArticleDaoImplUsingSpringJdbc implements ArticleDao {

	static final String INSERT = "INSERT INTO article (title,content,userId,name) VALUES(?, ?, ?, ?)";

	static final String SELECT_ALL = "SELECT articleId, title, content, userId, name, cdate FROM article ORDER BY articleId desc LIMIT ?,?";

	static final String COUNT_ALL = "SELECT count (articleId) count FROM article";
	
	static final String GET_ARTICLE = "Select * from article where articleid = ?";
	
	static final String UPDATE_ARTICLE = "UPDATE article SET title=?, content=? WHERE articleId=?";
	
	static final String DELETE_ARTICLE = "DELETE FROM article WHERE userId=?";

	@Autowired
	JdbcTemplate jdbcTemplate;

	final RowMapper<Article> memberRowMapper = new BeanPropertyRowMapper<>(
										Article.class);

	/**
	 * p.201 [리스트 8.12]의 insert() 메서드 수정
	 */
	@Override
	public void insert(Article article) {
		jdbcTemplate.update(INSERT, article.getTitle(), article.getContent(), article.getUserId(), article.getName());
	}

	/**
	 * p.195 [리스트 8.9] selectAll() 메서드 수정
	 */
	@Override
	public List<Article> selectAll(int offset, int count) {
		return jdbcTemplate.query(SELECT_ALL, memberRowMapper, offset, count);
	}

	@Override
	public int countAll() {
		return jdbcTemplate.queryForObject(COUNT_ALL, Integer.class);
	}
	
	public Article getArticle(String articleId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(GET_ARTICLE,
				new BeanPropertyRowMapper<>(Article.class), articleId);
	}

	@Override
	public int updateArticle(String title, 
			String content, String userId) {
		return jdbcTemplate.update(UPDATE_ARTICLE, title, content, userId);
	}

	@Override
	public int deleteArticle(String userId) {
		return jdbcTemplate.update(DELETE_ARTICLE, userId);
	}
}
