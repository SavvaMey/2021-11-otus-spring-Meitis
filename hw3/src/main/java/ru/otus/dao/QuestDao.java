package ru.otus.dao;

import ru.otus.domain.Question;
import ru.otus.exception.FromCSVException;

import java.util.List;

public interface QuestDao {

    List<Question> getQuestions() throws FromCSVException;

}
