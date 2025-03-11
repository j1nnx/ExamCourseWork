package com.example.Exam.service.impl;

import com.example.Exam.model.Question;
import com.example.Exam.service.ExaminerService;
import com.example.Exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    @Autowired
    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getAllQuestions(int amount) {
        List<Question> allQuestions = new ArrayList<>(questionService.getAllQuestions());
        if (allQuestions.size() < amount) {
            throw new RuntimeException("Недостаточно вопросов для выбора");
        }
        Collections.shuffle(allQuestions);
        return allQuestions.stream().limit(amount).collect(Collectors.toList());
    }
}