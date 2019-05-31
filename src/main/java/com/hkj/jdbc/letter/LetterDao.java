package com.hkj.jdbc.letter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 인터페이스 MemberDao의 구현체. SpringJdbc를 사용해서 구현
 * 
 * @author Jacob
 */
@Repository("LetterDao")
public class LetterDao {

	static final String SEND_MAIL = "INSERT lette(title, content, senderId, senderName, receiverId, receiverName) values(?,?,?,?,?,?)";

	static final String SEND_SELECT = "SELECT letterId, title, receiverId, receiverName, cdate where senderId = ?";
	
	static final String RECEIVE_SELECT = "SELECT letterId, title, senderId, senderName, cdate where receiverId = ?";

	static final String COUNT_ALL = "SELECT count(memberId) count FROM member";
	
	static final String SELECT_DETAIL = "SELECT letterId, title, senderId, senderName, recieverId, receiverName, cdate from letter where letterId = ? and (senderId = ?, receiverId = ?)";
	
	static final String DELETE_MAIL = "DELETE from letter where letterId = ? and (senderId = ?, receiverId = ?)";

	@Autowired
	JdbcTemplate jdbcTemplate;

	final RowMapper<Letter> memberRowMapper = new BeanPropertyRowMapper<>(
										Letter.class);


	public List<Letter> selectsend(int offset, int count) {
		return jdbcTemplate.query(SEND_SELECT, memberRowMapper, offset, count);
	}
	
	public List<Letter> selectreceive(int offset, int count) {
		return jdbcTemplate.query(RECEIVE_SELECT, memberRowMapper, offset, count);
	}

	public int countAll() {
		return jdbcTemplate.queryForObject(COUNT_ALL, Integer.class);
	}
	
	public void send(Letter letter) {
		jdbcTemplate.update(SEND_MAIL, letter.getTitle(), letter.getContent(),
				letter.getSenderId(), letter.getSenderName(), letter.getReceiverId(), letter.getReceiverName());
	}
	
	public Letter getmail(String letterId) {
		return jdbcTemplate.queryForObject(SELECT_DETAIL, 
				new BeanPropertyRowMapper<>(Letter.class), letterId);
	}
	
	public int deleteletter(String senderId, String receiverId, String letterId) {
		return jdbcTemplate.update(DELETE_MAIL, senderId, receiverId, letterId);
	}
}
