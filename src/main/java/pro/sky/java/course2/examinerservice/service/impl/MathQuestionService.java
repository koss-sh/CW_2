package pro.sky.java.course2.examinerservice.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;

import pro.sky.java.course2.examinerservice.repository.impl.MathQuestionRepository;
import pro.sky.java.course2.examinerservice.service.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
@Service
public class MathQuestionService implements QuestionService {
    private final Random random = new Random();
    private final MathQuestionRepository questionRepository;

    public MathQuestionService(MathQuestionRepository questionRepository) {
                this.questionRepository = questionRepository;
    }


    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        return new ArrayList<>(questionRepository.getAll())
                .get(random.nextInt(questionRepository.getAll().size()));
    }

}

