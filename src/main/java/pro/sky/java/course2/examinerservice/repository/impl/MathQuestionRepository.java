package pro.sky.java.course2.examinerservice.repository.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.QuestionAlreadyExistsException;
import pro.sky.java.course2.examinerservice.exception.QuestionNotFoundException;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Repository
public class MathQuestionRepository implements QuestionRepository {

    private final Set<Question> questions = new HashSet<>();

    public MathQuestionRepository() {
    }
    @PostConstruct
    public void init() {
        questions.add(new Question("MathQuestion-1", "MathAnswer-1"));
        questions.add(new Question("MathQuestion-2", "MathAnswer-2"));
        questions.add(new Question("MathQuestion-3", "MathAnswer-3"));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyExistsException();
        }
        return question;
    }


    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }
}
