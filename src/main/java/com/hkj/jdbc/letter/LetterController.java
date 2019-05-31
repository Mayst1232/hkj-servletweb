package com.hkj.jdbc.letter;

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
public class LetterController {

	@Autowired
	LetterDao letterDao;

	static final Logger logger = LogManager.getLogger();

	
	@RequestMapping("/letter/Addletter")
	public void articleAddForm(@SessionAttribute("MEMBER") Member member) {
	}
	
	@PostMapping("/letter/AddSuccess")
	public String letterAdd(Letter letter, 
			@SessionAttribute("MEMBER") Member member) {
		letter.setSenderId(member.getMemberId());
		letter.setSenderName(member.getName());
		letter.setReceiverId(member.getMemberId());
		letter.setReceiverName(member.getName());
		letterDao.send(letter);
		return "redirect:/app/Sends";
}
	
	@GetMapping("/letter/Mails")
	public void List(@RequestParam("letterId") String letterId,
			Model model) {
		Letter letter = letterDao.getmail(letterId);
		model.addAttribute("letter", letter);
	}

	@GetMapping("/letter/Sends")
	public void sends(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		final int COUNT = 100;
		int offset = (page - 1) * COUNT;
		List<Letter> articleList = letterDao.selectsend(offset, COUNT);
		int totalCount = letterDao.countAll();
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("articles", articleList);
	}
	
	@GetMapping("/letter/Receives")
	public void receives(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		final int COUNT = 100;
		int offset = (page - 1) * COUNT;
		List<Letter> articleList = letterDao.selectreceive(offset, COUNT);
		int totalCount = letterDao.countAll();
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("articles", articleList);
	}
	
	@GetMapping("/letter/DeleteMail")
	public String deleteArticle(@RequestParam("letterId") String letterId, 
			@SessionAttribute("MEMBER") Member member) {
		int updatedRows = letterDao.deleteletter(member.getMemberId(),
				member.getMemberId(), letterId);
		if(updatedRows == 0)
			throw new RuntimeException("No Authority!");
		logger.debug("편지를 삭제했습니다. letterId={}", letterId);
		return "redirect:/app/letter/Letters";
	}
	
}
