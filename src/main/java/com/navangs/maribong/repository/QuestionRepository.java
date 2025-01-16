package com.navangs.maribong.repository;

import com.navangs.maribong.domain.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByQuizIdIn(List<Integer> quizIds);
}
