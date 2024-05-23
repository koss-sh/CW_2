package pro.sky.java.course2.examinerservice.repository;

import org.springframework.stereotype.Repository;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.Collection;


public interface QuestionRepository {

    void init();
    Question add(Question question);


    Question remove(Question question);
    Collection<Question> getAll();
}
