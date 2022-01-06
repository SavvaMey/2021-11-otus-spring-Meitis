package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.exception.FromCSVException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class QuestDaoImplTest {

    @MockBean
    ParserFromCSVToQuestion parserFromCSVToQuestion;

    @Test
    void whenGetWhen5() throws FromCSVException {
        BeanForInputStream beanForInputStream = new BeanForInputStream("/questions.csv");
        QuestDao questDao = new QuestDaoImpl(parserFromCSVToQuestion, beanForInputStream);
        assertEquals(5,  questDao.getQuestions().size());

    }

    @Test
    void whenGetWhenEx() {
        BeanForInputStream beanForInputStream = new BeanForInputStream("/questions1.csv");
        QuestDao questDao = new QuestDaoImpl(parserFromCSVToQuestion, beanForInputStream);
        FromCSVException from = assertThrows( FromCSVException.class,
                questDao::getQuestions,
                "problem with CSV"
        );
        assertEquals(from.getMessage(), "problem with CSV");
    }
}
