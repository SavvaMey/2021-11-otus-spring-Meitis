package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellService {

    private final TestingService testingService;

    @ShellMethod(value = "start the exam", key = {"start", "st"})
    public void start() {
       testingService.testStudent();
    }

}
