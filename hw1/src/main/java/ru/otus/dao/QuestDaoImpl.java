package ru.otus.dao;

import au.com.bytecode.opencsv.CSVReader;
import ru.otus.domain.Question;
import ru.otus.exception.FromCSVException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestDaoImpl implements QuestDao {
    private final String csvName;
    public List<Question> listQuestions = new ArrayList<>();

    public QuestDaoImpl(String csvName) {
        this.csvName = csvName;
    }

    public List<Question> getQuestions() throws FromCSVException {
       if (sizeListQuestions()) {
           try {
               loadQuestions();
           } catch (Exception e) {
               throw new FromCSVException("problem with CSV");
           }
       }
        return listQuestions;
    }

    private boolean sizeListQuestions() {
        return listQuestions.size() == 0;
    }

    private void loadQuestions() throws IOException {
        InputStream in = QuestDaoImpl.class.getResourceAsStream(csvName);
        try (CSVReader reader = new CSVReader(new InputStreamReader(in))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                Question question = new Question();
                question.setQuestion(lineInArray[0]);
                question.setAnswers(Arrays.stream(lineInArray[1].split(";")).collect(Collectors.toList()));
                question.setTrueAnswer(lineInArray[2]);
                listQuestions.add(question);
            }
        }
    }
}
