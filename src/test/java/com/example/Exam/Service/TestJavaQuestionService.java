package com.example.Exam.Service;

import com.example.Exam.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestJavaQuestionService {
    JavaQuestionService javaQuestionService = new JavaQuestionService();

    @BeforeEach
    void init() {
        javaQuestionService.add("Что такое ООП?", "Объектно-ориентированное программирование");
        javaQuestionService.add("Какие типы данных есть в Java?", "byte, short, int, long, float, double, char, boolean");
        javaQuestionService.add("java это компилируемый или интерпретируемый язык программирования?", "компилируемый ");
    }

    @Test
    void remove() {
        int size = javaQuestionService.getAllQuestions().size();
        javaQuestionService.remove("Какие типы данных есть в Java?");
        assertEquals(size - 1, javaQuestionService.getAllQuestions().size());
    }

    @Test
    void findAll() {
        int size = javaQuestionService.getAllQuestions().size();
        assertEquals(size, javaQuestionService.getAllQuestions().size());
    }
    @Test
    void add() {
        int size = javaQuestionService.getAllQuestions().size();
        javaQuestionService.add("Когда был создан язык Java", "1995");
        assertEquals(size + 1, javaQuestionService.getAllQuestions().size());
    }
}
