package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestingServiceImplTest {
    @Mock
    QuestionsService questionsService;

    @Mock
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