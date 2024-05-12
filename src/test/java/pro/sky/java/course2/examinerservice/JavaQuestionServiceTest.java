package pro.sky.java.course2.examinerservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.IncorrectAmountException;
import pro.sky.java.course2.examinerservice.exception.QuestionAlreadyExistsException;
import pro.sky.java.course2.examinerservice.exception.QuestionNotFoundException;
import pro.sky.java.course2.examinerservice.service.QuestionService;
import pro.sky.java.course2.examinerservice.service.impl.JavaQuestionService;

import java.util.HashSet;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        questionService.add(new Question("Q1", "A1"));
        questionService.add(new Question("Q2", "A2"));
        questionService.add(new Question("Q3", "A3"));
    }

    public void afterEach() {
        new HashSet<>(questionService.getAll()).forEach(questionService::remove);
    }

    @Test
    public void add1Test() {
        int before = questionService.getAll().size();
        Question question = new Question("Q4", "A4");
        Assertions.assertThat(questionService.add(question))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        Assertions.assertThat(questionService.getAll()).hasSize(before + 1);
    }

    @Test
    public void add2Test() {
        int before = questionService.getAll().size();
        Question question = new Question("Q4", "A4");
        Assertions.assertThat(questionService.add("Q4", "A4"))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        Assertions.assertThat(questionService.getAll()).hasSize(before + 1);
    }

    @Test
    public void addNegativeTest() {
        Question question = new Question("Q1", "A1");
        Assertions.assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(question));
    }

    @Test
    public void removeTest() {
        int before = questionService.getAll().size();
        Question question = new Question("Q1", "A1");
        Assertions.assertThat(questionService.remove(question))
                .isEqualTo(question)
                .isNotIn(questionService.getAll());
        Assertions.assertThat(questionService.getAll()).hasSize(before - 1);
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(questionService.getAll())
                .containsExactlyInAnyOrder(
                        new Question("Q1", "A1"),
                        new Question("Q2", "A2"),
                        new Question("Q3", "A3")
                )
                .hasSize(3);
    }


    @Test
    public void getRandomQuestionTest() {
        Assertions.assertThat(questionService.getRandomQuestion())
                .isNotNull()
                .isIn(questionService.getAll());
    }
    @Test
    public void getRandomQuestionNegativeTest() {
        afterEach();
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> questionService.getRandomQuestion());
    }
}
