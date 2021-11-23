package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.exception.FromCSVException;

import static org.junit.jupiter.api.Assertions.*;

class QuestDaoImplTest {
    @Test
    void whenGetWhen5() throws FromCSVException {
        QuestDao questDao = new QuestDaoImpl("/questions.csv");
        assertEquals(5,  questDao.getQuestions().size());

    }

    @Test
    void whenGetWhenEx() {
        QuestDao questDao = new QuestDaoImpl("/questions.csvu");
        FromCSVException from = assertThrows( FromCSVException.class,
                questDao::getQuestions,
                "problem with CSV"
        );
        assertEquals(from.getMessage(), "problem with CSV");
    }


}