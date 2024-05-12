package pro.sky.java.course2.examinerservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.IncorrectAmountException;
import pro.sky.java.course2.examinerservice.service.ExaminerService;
import pro.sky.java.course2.examinerservice.service.QuestionService;
import pro.sky.java.course2.examinerservice.service.impl.ExaminerServiceImpl;
import pro.sky.java.course2.examinerservice.service.impl.JavaQuestionService;

import java.util.Collection;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Collection<Question> questions = Set.of(
            new Question("Q1", "Q1"),
            new Question("Q2", "Q2"),
            new Question("Q3", "Q3"),
            new Question("Q4", "Q4"),
            new Question("Q5", "Q5")
    );

    @Test
    public void getQuestionsNegativeTest() {
        Mockito.when(questionService.getAll()).thenReturn(questions);
        Assertions.assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(6));
        Assertions.assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(0));
    }
    @Test
    public void getQuestionsTest() {
        Mockito.when(questionService.getAll()).thenReturn(questions);
        Mockito.when(questionService.getRandomQuestion()).thenReturn(
                new Question("Q2", "Q2"),
                new Question("Q2", "Q2"),
                new Question("Q4", "Q4"),
                new Question("Q3", "Q3")
        );
        Assertions.assertThat(examinerService.getQuestions(3))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Q2", "Q2"),
                        new Question("Q4", "Q4"),
                        new Question("Q3", "Q3")
                );
    }



}
