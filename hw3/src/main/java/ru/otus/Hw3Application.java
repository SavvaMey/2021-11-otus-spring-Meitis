package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.service.TestingService;

@SpringBootApplication
public class Hw3Application {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(Hw3Application.class, args);
        var service = context.getBean(TestingService.class);
        service.testStudent();
    }

}
