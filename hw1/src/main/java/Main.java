import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.domain.Question;
import ru.otus.service.QuestionsService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        var service = context.getBean(QuestionsService.class);
        List<Question> questionList = service.showQuestions();
        System.out.println(questionList);
    }
}
