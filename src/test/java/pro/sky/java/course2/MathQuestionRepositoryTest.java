package pro.sky.java.course2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.QuestionAlreadyExistsException;
import pro.sky.java.course2.examinerservice.exception.QuestionNotFoundException;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;
import pro.sky.java.course2.examinerservice.repository.impl.MathQuestionRepository;

public class MathQuestionRepositoryTest {

    private final QuestionRepository questionRepository = new MathQuestionRepository();

    @BeforeEach
    public void beforeEach() {
        questionRepository.init();
    }

    @Test
    public void initTest() {
        Assertions.assertThat(questionRepository.getAll()).hasSize(3);
    }

    @Test
    public void addTest() {
        int before = questionRepository.getAll().size();
        Question question = new Question("MathQuestion-4", "MathAnswer-4");
        Assertions.assertThat(questionRepository.add(question))
                .isEqualTo(question)
                .isIn(questionRepository.getAll());
        Assertions.assertThat(questionRepository.getAll()).hasSize(before + 1);
    }

    @Test
    public void addNegativeTest() {
        Question question = new Question("MathQuestion-3", "MathAnswer-3");
        Assertions.assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionRepository.add(question));
    }

    @Test
    public void removeTest() {
        int before = questionRepository.getAll().size();
        Question question = new Question("MathQuestion-3", "MathAnswer-3");
        Assertions.assertThat(questionRepository.remove(question))
                .isEqualTo(question)
                .isNotIn(questionRepository.getAll());
        Assertions.assertThat(questionRepository.getAll()).hasSize(before - 1);
    }

    @Test
    public void removeNegativeTest() {
        Question question = new Question("MathQuestion-4", "MathAnswer-4");
        Assertions.assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionRepository.remove(question));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(questionRepository.getAll())
                .containsExactlyInAnyOrder(
                        new Question("MathQuestion-2", "MathAnswer-2"),
                        new Question("MathQuestion-1", "MathAnswer-1"),
                        new Question("MathQuestion-3", "MathAnswer-3")
                )
                .hasSize(3);
    }
}

