package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.QuestDao;
import ru.otus.domain.Question;
import ru.otus.exception.FromCSVException;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionsServiceImplTest {

    @Mock
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