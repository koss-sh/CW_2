package pro.sky.java.course2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.IncorrectAmountException;
import pro.sky.java.course2.examinerservice.service.QuestionService;
import pro.sky.java.course2.examinerservice.service.impl.ExaminerServiceImpl;
import pro.sky.java.course2.examinerservice.service.impl.JavaQuestionService;
import pro.sky.java.course2.examinerservice.service.impl.MathQuestionService;

import java.util.Collection;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Collection<Question> javaQuestions = Set.of(
            new Question("JavaQuestion-1", "JavaAnswer-1"),
            new Question("JavaQuestion-2", "JavaAnswer-2"),
            new Question("JavaQuestion-3", "JavaAnswer-3"),
            new Question("JavaQuestion-4", "JavaAnswer-4"),
            new Question("JavaQuestion-5", "JavaAnswer-5")
    );
    private final Collection<Question> mathQuestions = Set.of(
            new Question("MathQuestion-1", "MathAnswer-1"),
            new Question("MathQuestion-2", "MathAnswer-2"),
            new Question("MathQuestion-3", "MathAnswer-3"),
            new Question("MathQuestion-4", "MathAnswer-4"),
            new Question("MathQuestion-5", "MathAnswer-5")
    );

    @Test
    public void getQuestionsNegativeTest() {
        Mockito.when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        Mockito.when(mathQuestionService.getAll()).thenReturn(mathQuestions);
        Assertions.assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(11));
        Assertions.assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(0));
    }
    @Test
    public void getQuestionsTest() {
        Mockito.when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        Mockito.when(mathQuestionService.getAll()).thenReturn(mathQuestions);
        Mockito.when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("JavaQuestion-2", "JavaAnswer-2"),
                new Question("JavaQuestion-2", "JavaAnswer-2"),
                new Question("JavaQuestion-3", "JavaAnswer-3"),
                new Question("JavaQuestion-4", "JavaAnswer-4")
        );
        Mockito.when(mathQuestionService.getRandomQuestion()).thenReturn(
                new Question("MathQuestion-2", "MathAnswer-2"),
                new Question("MathQuestion-5", "MathAnswer-5"),
                new Question("MathQuestion-5", "MathAnswer-5"),
                new Question("MathQuestion-5", "MathAnswer-5")
        );
        Assertions.assertThat(examinerService.getQuestions(5))
                .hasSize(5)
                .containsExactlyInAnyOrder(
                        new Question("MathQuestion-5", "MathAnswer-5"),
                        new Question("MathQuestion-2", "MathAnswer-2"),
                        new Question("JavaQuestion-2", "JavaAnswer-2"),
                        new Question("JavaQuestion-3", "JavaAnswer-3"),
                        new Question("JavaQuestion-4", "JavaAnswer-4")
                );
    }


}
