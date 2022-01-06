package ru.otus.service;

public interface StudentService {
    String askName();
    String askAnswer();
    void ansPass(String nameStudent);
    void ansFail(String nameStudent);
}
