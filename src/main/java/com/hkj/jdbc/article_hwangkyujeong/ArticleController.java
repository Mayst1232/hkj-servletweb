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
	@RequestMapping("/article/Add")
	public void FirstSpace() {
	}

	/**
	 * p.282 [리스트 11.11] handleStep3()
	 */
	@PostMapping("/article/Success")
	public String addArticle(Article article) {
			articleDao.insert(article);
			logger.debug("글을 작성했습니다. {}", article);
			return "article/Success";
	}
	
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
}
