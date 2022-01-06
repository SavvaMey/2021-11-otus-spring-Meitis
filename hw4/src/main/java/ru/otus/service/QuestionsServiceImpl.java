package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestDao;
import ru.otus.domain.Question;
import ru.otus.exception.FromCSVException;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService{
    private final QuestDao questionsDao;

    @Autowired
    public QuestionsServiceImpl(QuestDao questionsDao) {
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
