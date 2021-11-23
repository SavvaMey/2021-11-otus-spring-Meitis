package ru.otus.service;

import ru.otus.dao.QuestDao;
import ru.otus.domain.Question;
import ru.otus.exception.FromCSVException;

import java.util.ArrayList;
import java.util.List;

public class QuestionsServiceImple implements QuestionsService{
    private final QuestDao questionsDao;

    public QuestionsServiceImple(QuestDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    @Override
    public List<Question> showQuestions() {
        try {
            return questionsDao.getQuestions();
        } catch (FromCSVException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

}
