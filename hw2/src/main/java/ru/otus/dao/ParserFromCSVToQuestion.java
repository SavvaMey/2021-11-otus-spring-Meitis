package ru.otus.dao;

import org.springframework.stereotype.Component;
import ru.otus.domain.Question;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class ParserFromCSVToQuestion {
    public Question parse (String[] lineInArray) {
        Question question = new Question();
        question.setQuestion(lineInArray[0]);
        question.setAnswers(Arrays.stream(lineInArray[1].split(";")).collect(Collectors.toList()));
        question.setTrueAnswer(lineInArray[2]);
        return question;
    }
}
