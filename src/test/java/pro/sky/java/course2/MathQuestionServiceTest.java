package pro.sky.java.course2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.impl.JavaQuestionRepository;
import pro.sky.java.course2.examinerservice.repository.impl.MathQuestionRepository;
import pro.sky.java.course2.examinerservice.service.impl.JavaQuestionService;
import pro.sky.java.course2.examinerservice.service.impl.MathQuestionService;

import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {
    @Mock
    private MathQuestionRepository questionRepository;

    @InjectMocks
    private MathQuestionService mathQuestionService;

    private Set<Question> questions;

    @BeforeEach
    public void beforeEach() {
        questions = Set.of(
                new Question("MathQuestion-1", "MathAnswer-1"),
                new Question("MathQuestion-2", "MathAnswer-2"),
                new Question("MathQuestion-3", "MathAnswer-3")
        );

    }

    @Test
    public void add1Test() {
        Question question = new Question("MathQuestion-4", "MathAnswer-4");
        when(questionRepository.add(question))
                .thenReturn(new Question("MathQuestion-4", "MathAnswer-4"));
        Assertions.assertThat(mathQuestionService.add("MathQuestion-4", "MathAnswer-4"))
                .isEqualTo(question);
    }

    @Test
    public void add2Test() {
        Question question = new Question("MathQuestion-4", "MathAnswer-4");
        when(questionRepository.add(question))
                .thenReturn(new Question("MathQuestion-4", "MathAnswer-4"));
        Assertions.assertThat(mathQuestionService
                        .add(new Question("MathQuestion-4", "MathAnswer-4")))
                .isEqualTo(question);
    }

    @Test
    public void removeTest() {
        Question question = new Question("MathQuestion-1", "MathAnswer-1");
        when(questionRepository.remove(question))
                .thenReturn(new Question("MathQuestion-1", "MathAnswer-1"));
        Assertions.assertThat(mathQuestionService
                        .remove(new Question("MathQuestion-1", "MathAnswer-1")))
                .isEqualTo(question);
    }

    @Test
    public void getAllTest() {
        when(questionRepository.getAll()).thenReturn(questions);
        Assertions.assertThat(mathQuestionService.getAll())
                .containsExactlyInAnyOrderElementsOf(questions);
    }

    @Test
    public void getRandomQuestion() {
        when(questionRepository.getAll()).thenReturn(questions);
        Assertions.assertThat(mathQuestionService.getRandomQuestion())
                .isIn(questions);
    }

}

