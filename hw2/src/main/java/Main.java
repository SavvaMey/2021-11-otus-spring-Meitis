import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.domain.Question;
import ru.otus.service.QuestionsService;
import ru.otus.service.TestingService;

import java.util.List;


@Configuration
@ComponentScan("ru.otus")
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        var service = context.getBean(TestingService.class);
        service.testStudent();
    }
}
