package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.dao.QuestDao;
import ru.otus.domain.Question;
import ru.otus.exception.FromCSVException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionsServiceImplTest {
    @MockBean
    QuestDao questDao;

    @Test
    void whenShowThenTrue() throws FromCSVException {
        List<Question> questions = new ArrayList<>();
        when(questDao.getQuestions()).thenReturn(questions);
        QuestionsService questionsService = new QuestionsServiceImpl(questDao);
        assertEquals(questions,  questionsService.showQuestions());

    }

    @Test
    void whenShowWithExThenArraySize0() throws FromCSVException {
        when(questDao.getQuestions()).thenThrow(new FromCSVException("bad"));
        QuestionsService questionsService = new QuestionsServiceImpl(questDao);
        assertEquals(0,  questionsService.showQuestions().size());
    }
}
