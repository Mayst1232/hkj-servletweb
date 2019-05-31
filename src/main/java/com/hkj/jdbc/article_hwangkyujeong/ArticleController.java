package com.hkj.jdbc.article_hwangkyujeong;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.hkj.jdbc.chap11.Member;

/**
 * 회원가입 컨트롤러

 */
@Controller
public class ArticleController {

	@Autowired
	ArticleDao articleDao;

	static final Logger logger = LogManager.getLogger();

	@RequestMapping("/main2")
	public void main() {
	}

	/**
	 * p.271 [리스트 11.5] handleStep1()
	 */
	@RequestMapping("/article/Addarticle")
	public String articleAddForm(@SessionAttribute("MEMBER") Member member) {
		return "article/Addarticle";
	}
	
	@PostMapping("/article/Success")
	public String articleAdd(Article article, 
			@SessionAttribute("MEMBER") Member member) {
		article.setUserId(member.getMemberId());
		article.setName(member.getName());
		articleDao.insert(article);
		return "redirect:/app/articles";
}
	
	
	 /* p.282 [리스트 11.11] handleStep3()
	 */

	
	@GetMapping("/article/List")
	public void List(@RequestParam("articleId") String articleId,
			Model model) {
		Article article = articleDao.getArticle(articleId);
		model.addAttribute("article", article);
	}

	@GetMapping("/articles")
	public void articles(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {

		// 페이지 당 가져오는 행의 수
		final int COUNT = 100;
		// 시작점
		int offset = (page - 1) * COUNT;

		List<Article> articleList = articleDao.selectAll(offset, COUNT);

		int totalCount = articleDao.countAll();

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("articles", articleList);
	}
	
	@GetMapping("/article/UpdateForm")
	public void updateArticle(@RequestParam("articleId") String articleId,
			@SessionAttribute("MEMBER") Member member, Model model) {
		Article article = articleDao.getArticle(articleId);
		
		if(!member.getMemberId().contentEquals(article.getUserId()))
			throw new RuntimeException("No Autority!");
		
		model.addAttribute("article",article);
	}
	@PostMapping("/article/Update")
	public String update(Article article,
			@SessionAttribute("MEMBER") Member member) {
		article.setUserId(member.getMemberId());
		int updatedRows = articleDao.updateArticle(article);
		if(updatedRows == 0)
			throw new RuntimeException("No Authority!");
		return "redirect:/app/article/List?articleId=" + article.getArticleId();
	}
	
	@GetMapping("/article/Delete")
	public String deleteArticle(@RequestParam("articleId") String articleId, 
			@SessionAttribute("MEMBER") Member member) {
		int updatedRows = articleDao.deleteArticle(articleId,
				member.getMemberId());
		if(updatedRows == 0)
			throw new RuntimeException("No Authority!");
		
		logger.debug("글을 삭제했습니다. articleId={}", articleId);
		return "redirect:/app/articles";
	}
	
}
