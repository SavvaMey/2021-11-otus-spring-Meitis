package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final Scanner scanner = new Scanner(System.in);
    private final Localizator localizator;

    @Override
    public String askName() {
        System.out.println(localizator.getMsg("strings.name", new String[]{}));
        return scanner.nextLine();
    }

    @Override
    public String askAnswer() {
        System.out.println(localizator.getMsg("strings.answer", new String[]{}));
        return scanner.nextLine();
    }

    @Override
    public void ansPass(String nameStudent) {
        System.out.println(localizator.getMsg("strings.result-pass", new String[]{nameStudent}));

    }

    @Override
    public void ansFail(String nameStudent) {
        System.out.println(localizator.getMsg("strings.result-false", new String[]{nameStudent}));
    }
}
