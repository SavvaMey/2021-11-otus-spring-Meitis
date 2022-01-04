package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class TestingServiceImplTests {

    @MockBean
    QuestionsService questionsService;
    @MockBean
    StudentService studentService;

    @Test
    void testStudentThenPass() {
        TestingServiceImpl testingService = new TestingServiceImpl(
                questionsService, studentService, 1);
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestion("ask");
        List<String> answers = new ArrayList<>();
        answers.add("answer");
        question.setAnswers(answers);
        question.setTrueAnswer("true");
        questions.add(question);
        when(questionsService.showQuestions()).thenReturn(questions);
        when(studentService.askAnswer()).thenReturn("true");
        testingService.testStudent();
        verify(studentService, times(1)).askName();
        verify(questionsService, times(1)).showQuestions();
        verify(studentService, times(1)).askAnswer();

    }

}
