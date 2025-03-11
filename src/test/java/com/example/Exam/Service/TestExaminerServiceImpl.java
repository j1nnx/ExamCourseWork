package com.example.Exam.Service;

import com.example.Exam.model.Question;
import com.example.Exam.service.QuestionService;
import com.example.Exam.service.impl.ExaminerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private List<Question> mockQuestions;

    @BeforeEach
    void setUp() {
        // Подготовка тестовых данных
        mockQuestions = List.of(
                new Question("Что такое Java?", "Java — это язык программирования."),
                new Question("Что такое JVM?", "JVM — это виртуальная машина Java."),
                new Question("Что такое Spring?", "Spring — это фреймворк для Java.")
        );
    }

    @Test
    void getAllQuestions_ShouldReturnCorrectAmountOfQuestions() {
        // Arrange
        when(questionService.getAllQuestions()).thenReturn(mockQuestions);
        int amount = 2;

        // Act
        Collection<Question> result = examinerService.getAllQuestions(amount);

        // Assert
        assertNotNull(result);
        assertEquals(amount, result.size()); // Проверяем, что возвращено ровно amount вопросов
        assertTrue(mockQuestions.containsAll(result)); // Проверяем, что все вопросы из mockQuestions
        verify(questionService, times(1)).getAllQuestions(); // Проверяем, что метод вызван 1 раз
    }

    @Test
    void getAllQuestions_ShouldThrowException_WhenNotEnoughQuestions() {
        // Arrange
        when(questionService.getAllQuestions()).thenReturn(mockQuestions);
        int amount = 4; // Запрашиваем больше, чем есть

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            examinerService.getAllQuestions(amount);
        });
        assertEquals("Недостаточно вопросов для выбора", exception.getMessage());
        verify(questionService, times(1)).getAllQuestions(); // Проверяем, что метод вызван 1 раз
    }

    @Test
    void getAllQuestions_ShouldReturnAllQuestions_WhenAmountEqualsSize() {
        // Arrange
        when(questionService.getAllQuestions()).thenReturn(mockQuestions);
        int amount = mockQuestions.size(); // Запрашиваем все вопросы

        // Act
        Collection<Question> result = examinerService.getAllQuestions(amount);

        // Assert
        assertNotNull(result);
        assertEquals(amount, result.size()); // Проверяем, что возвращено ровно amount вопросов
        assertTrue(mockQuestions.containsAll(result)); // Проверяем, что все вопросы из mockQuestions
        verify(questionService, times(1)).getAllQuestions();
    }
}
