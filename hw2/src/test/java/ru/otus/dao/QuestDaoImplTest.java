package ru.otus.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.exception.FromCSVException;

import static org.junit.jupiter.api.Assertions.*;

class QuestDaoImplTest {
    ParserFromCSVToQuestion parserFromCSVToQuestion = new ParserFromCSVToQuestion();

    @Test
    void whenGetWhen5() throws FromCSVException {

        QuestDao questDao = new QuestDaoImpl("/questions.csv",  parserFromCSVToQuestion);
        assertEquals(5,  questDao.getQuestions().size());

    }

    @Test
    void whenGetWhenEx() {
        QuestDao questDao = new QuestDaoImpl("/questions.csvu", parserFromCSVToQuestion);
        FromCSVException from = assertThrows( FromCSVException.class,
                questDao::getQuestions,
                "problem with CSV"
        );
        assertEquals(from.getMessage(), "problem with CSV");
    }

}