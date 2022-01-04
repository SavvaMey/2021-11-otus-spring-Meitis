package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {

    private final QuestionsService questionsService;
    private final StudentService studentService;
    private final int countPassExam;

    @Autowired
    public TestingServiceImpl(QuestionsService questionsService,
                              StudentService studentService,
                              @Value("${count-answers}") int countPassExam) {
        this.questionsService = questionsService;
        this.studentService = studentService;
        this.countPassExam = countPassExam;
    }


    @Override
    public void testStudent() {
        String name = studentService.askName();
        List<Question> questions = questionsService.showQuestions();
        if (questions.size() == 0) {
            return;
        }
        int rightAnswers = 0;
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            question.getAnswers().forEach(System.out::println);
            String answer = studentService.askAnswer();
            if (answer.equals(question.getTrueAnswer())) {
                rightAnswers++;
            }
        }
        if (rightAnswers >= countPassExam) {
            studentService.ansPass(name);
        } else {
           studentService.ansFail(name);
        }
    }
}
