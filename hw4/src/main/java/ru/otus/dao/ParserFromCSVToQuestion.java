package ru.otus.dao;

import ru.otus.domain.Question;


public interface ParserFromCSVToQuestion {
    Question parse (String[] lineInArray);
}
