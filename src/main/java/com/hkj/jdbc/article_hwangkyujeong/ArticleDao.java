package com.hkj.jdbc.article_hwangkyujeong;

import java.util.List;


/**
 * p.184 [리스트 8.2] MemberDao 를 interface로 수정<br>
 * 회원 테이블을 조작하는 Data Access Object
 * 
 * @author Jacob
 */
public interface ArticleDao {
	/**
	 * 회원정보 저장
	 */
	void insert(Article member);
	
	/**
	 * 회원 목록
	 */
	List<Article> selectAll(int offset, int count);
	
	/**
	 * 회원 수
	 */
	int countAll();
	
	Article getArticle(String articleId);
}
