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
import pro.sky.java.course2.examinerservice.service.impl.JavaQuestionService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {
    @Mock
    private JavaQuestionRepository questionRepository;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    private Set<Question> questions;

    @BeforeEach
    public void beforeEach() {
        questions = Set.of(
                new Question("JavaQuestion-1", "JavaAnswer-1"),
                new Question("JavaQuestion-2", "JavaAnswer-2"),
                new Question("JavaQuestion-3", "JavaAnswer-3")
        );

    }

    @Test
    public void add1Test() {
        Question question = new Question("JavaQuestion-4", "JavaAnswer-4");
        when(questionRepository.add(question))
                .thenReturn(new Question("JavaQuestion-4", "JavaAnswer-4"));
        Assertions.assertThat(javaQuestionService.add("JavaQuestion-4", "JavaAnswer-4"))
                .isEqualTo(question);
    }

    @Test
    public void add2Test() {
        Question question = new Question("JavaQuestion-4", "JavaAnswer-4");
        when(questionRepository.add(question))
                .thenReturn(new Question("JavaQuestion-4", "JavaAnswer-4"));
        Assertions.assertThat(javaQuestionService
                        .add(new Question("JavaQuestion-4", "JavaAnswer-4")))
                .isEqualTo(question);
    }

    @Test
    public void removeTest() {
        Question question = new Question("JavaQuestion-1", "JavaAnswer-1");
        when(questionRepository.remove(question))
                .thenReturn(new Question("JavaQuestion-1", "JavaAnswer-1"));
        Assertions.assertThat(javaQuestionService
                        .remove(new Question("JavaQuestion-1", "JavaAnswer-1")))
                .isEqualTo(question);
    }

    @Test
    public void getAllTest() {
        when(questionRepository.getAll()).thenReturn(questions);
        Assertions.assertThat(javaQuestionService.getAll())
                .containsExactlyInAnyOrderElementsOf(questions);
    }

    @Test
    public void getRandomQuestion() {
        when(questionRepository.getAll()).thenReturn(questions);
        Assertions.assertThat(javaQuestionService.getRandomQuestion())
                .isIn(questions);
    }

}
