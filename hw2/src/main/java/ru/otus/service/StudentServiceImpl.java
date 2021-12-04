package ru.otus.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class StudentServiceImpl implements StudentService {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askName() {
        System.out.println("wright your name");
        String name = scanner.nextLine();
        return name;
    }

    @Override
    public String askAnswer() {
        System.out.println("wright answer");
        String answer = scanner.nextLine();
        return answer;
    }
}
