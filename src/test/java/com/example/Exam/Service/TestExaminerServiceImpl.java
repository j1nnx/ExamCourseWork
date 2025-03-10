package com.example.Exam.Service;

import com.example.Exam.model.Question;
import com.example.Exam.service.ExaminerService;
import com.example.Exam.service.JavaQuestionService;
import com.example.Exam.service.QuestionService;
import com.example.Exam.service.impl.ExaminerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestExaminerServiceImpl {

    private QuestionService questionService;
    private ExaminerService examinerService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    void getRandomQuestion() {
        questionService.add("Вопрос 1", "Ответ 1");
        questionService.add("Вопрос 2", "Ответ 2");
        Question randomQuestion = examinerService.getRandomQuestion();
        assertNotNull(randomQuestion);
    }

    @Test
    void addQuestion() {
        Question question = examinerService.addQuestion("Вопрос", "Ответ");
        assertNotNull(question);
        assertEquals("Вопрос", question.getQuestion());
        assertEquals("Ответ", question.getAnswer());
    }

    @Test
    void removeQuestion() {
        examinerService.addQuestion("Вопрос для удаления", "Ответ");
        int size = questionService.getAllQuestions().size();
        examinerService.removeQuestion("Вопрос для удаления");
        assertEquals(size - 1, questionService.getAllQuestions().size());
    }

    @Test
    void findQuestion() {
        examinerService.addQuestion("Вопрос", "Ответ");
        Question foundQuestion = examinerService.findQuestion("Вопрос");
        assertNotNull(foundQuestion);
        assertEquals("Вопрос", foundQuestion.getQuestion());
    }
}
