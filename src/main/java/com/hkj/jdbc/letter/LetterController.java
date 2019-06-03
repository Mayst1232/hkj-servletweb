package com.hkj.jdbc.letter;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.hkj.jdbc.chap11.Member;

@Controller
public class LetterController {

	@Autowired
	LetterDao letterDao;

	@GetMapping("/letter/Receives")
	public void Receives(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@SessionAttribute("MEMBER") Member member, Model model) {
		
		// 페이지당 행의 수와 페이지의 시작점
		final int ROWS_PER_PAGE = 20;
		int offset = (page - 1) * ROWS_PER_PAGE;

		List<Letter> letters = letterDao.listLettersReceived(
				member.getMemberId(), offset, ROWS_PER_PAGE);
		int count = letterDao.countReceived(member.getMemberId());

		model.addAttribute("letters", letters);
		model.addAttribute("count", count);
	}

	@GetMapping("/letter/Sends")
	public void Sends(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@SessionAttribute("MEMBER") Member member, Model model) {

		// 페이지당 행의 수와 페이지의 시작점
		final int ROWS_PER_PAGE = 20;
		int offset = (page - 1) * ROWS_PER_PAGE;

		List<Letter> letters = letterDao.listLettersSent(member.getMemberId(),
				offset, ROWS_PER_PAGE);
		int count = letterDao.countSent(member.getMemberId());

		model.addAttribute("letters", letters);
		model.addAttribute("count", count);
	}

	@GetMapping("/letter/Mails")
	public void Mails(@RequestParam("letterId") String letterId,
			@SessionAttribute("MEMBER") Member member, Model model) {

		// 자신의 편지가 아닐 경우 EmptyResultDataAccessException 발생함
		Letter letter = letterDao.getLetter(letterId, member.getMemberId());
		model.addAttribute("letter", letter);
	}
	
	@RequestMapping("/letter/Addletter")
	public void articleAddForm(@SessionAttribute("MEMBER") Member member) {
	}

	@PostMapping("/letter/AddSuccess")
	public String AddLetter(Letter letter,
			@SessionAttribute("MEMBER") Member member) {
		letter.setSenderId(member.getMemberId());
		letter.setSenderName(member.getName());
		letterDao.addLetter(letter);
		return "redirect:/app/letter/Sends";
	}

	@GetMapping("/letter/delete")
	public String delete(
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam("letterId") String letterId,
			@SessionAttribute("MEMBER") Member member) {
		int updatedRows = letterDao.deleteLetter(letterId,
				member.getMemberId());
		if (updatedRows == 0)
			// 자신의 편지가 아닐 경우 삭제되지 않음
			throw new RuntimeException("No Authority!");

		if ("SEND".equals(mode))
			return "redirect:/app/letter/Sends";
		else
			return "redirect:/app/letter/Receives";
	}
}
