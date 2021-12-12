package ru.otus.dao;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Question;
import ru.otus.exception.FromCSVException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestDaoImpl implements QuestDao {

    public List<Question> listQuestions = new ArrayList<>();
    private final ParserFromCSVToQuestion parserFromCSVToQuestion;
    private final BeanForInputStream beanForInputStream;


    public QuestDaoImpl(ParserFromCSVToQuestion parserFromCSVToQuestion, BeanForInputStream beanForInputStream) {
        this.parserFromCSVToQuestion = parserFromCSVToQuestion;
        this.beanForInputStream = beanForInputStream;
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

        try (CSVReader reader = new CSVReader(new InputStreamReader(
                beanForInputStream.getResource().getInputStream()))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                Question question = parserFromCSVToQuestion.parse(lineInArray);
                listQuestions.add(question);
            }
        }
    }
}
