package com.mysite.sbb;


import com.mysite.sbb.answer.dao.AnswerRepository;
import com.mysite.sbb.answer.domain.Answer;
import com.mysite.sbb.question.dao.QuestionRepository;
import com.mysite.sbb.question.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testFindBySubjectAndContent() {
		Question q = this.questionRepository.findBySubjectAndContent(
				"질문 1", "질문내용 1");
		assertEquals(1, q.getId());
	}

	@Test
	void testSearchQuestion() {
		List<Question> qList = this.questionRepository.findBySubjectLike("질문%");
		Question q = qList.get(0);
		assertEquals("질문 1", q.getSubject());
	}

	@Test
	void testUpdateQuestion() {
		Optional<Question> oq = this.questionRepository.findById(1L);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}

	@Test
	void testDeleteQuestion() {
		assertEquals(3, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1L);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(2, this.questionRepository.count());
	}

	@Test
	void testCreateAnswer() {
		Optional<Question> oq = this.questionRepository.findById(2L);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("답변입니다.");
		a.setQuestion(q); // 어떤 질문의 답변인지 알기위해 Question 객체가 필요
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}

	@Test
	void testShowAnswer() {
		Optional<Answer> oa = this.answerRepository.findById(4L);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}

	@Transactional
	@Test
	void answerToQuestion() {
		Optional<Question> oq = this.questionRepository.findById(2L);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(2, answerList.size());
		assertEquals("답변내용 2", answerList.get(0).getContent());
	}
}
