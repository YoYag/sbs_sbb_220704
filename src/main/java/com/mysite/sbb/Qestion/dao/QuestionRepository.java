package com.mysite.sbb.Qestion.dao;

import com.mysite.sbb.Qestion.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
