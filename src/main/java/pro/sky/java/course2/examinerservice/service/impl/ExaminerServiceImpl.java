package pro.sky.java.course2.examinerservice.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.IncorrectAmountException;
import pro.sky.java.course2.examinerservice.service.ExaminerService;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;


    public ExaminerServiceImpl(JavaQuestionService javaquestionService, MathQuestionService mathquestionService) {
        this.javaQuestionService = javaquestionService;
        this.mathQuestionService = mathquestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || amount > (javaQuestionService.getAll().size() + mathQuestionService.getAll().size())) {
            throw new IncorrectAmountException();
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            if (Math.random()<0.5) {
                result.add(javaQuestionService.getRandomQuestion());
            } else {
                result.add(mathQuestionService.getRandomQuestion());
            }
        }
        return result;
    }
}
