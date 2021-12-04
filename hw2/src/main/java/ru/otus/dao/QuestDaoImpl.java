package ru.otus.dao;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Question;
import ru.otus.exception.FromCSVException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestDaoImpl implements QuestDao {

    private final String csvName;
    public List<Question> listQuestions = new ArrayList<>();
    private final ParserFromCSVToQuestion parserFromCSVToQuestion;

    public QuestDaoImpl(@Value("${filename}") String csvName, ParserFromCSVToQuestion parserFromCSVToQuestion) {
        this.csvName = csvName;
        this.parserFromCSVToQuestion = parserFromCSVToQuestion;
    }


    @Override
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
                Question question = parserFromCSVToQuestion.parse(lineInArray);
                listQuestions.add(question);
            }
        }
    }
}
