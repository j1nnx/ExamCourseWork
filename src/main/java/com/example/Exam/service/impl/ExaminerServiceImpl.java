package com.example.Exam.service.impl;

import com.example.Exam.model.Question;
import com.example.Exam.service.ExaminerService;
import com.example.Exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
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
    public Question getRandomQuestion() {
        Collection<Question> allQuestions = questionService.getAllQuestions();
        if (allQuestions.isEmpty()) {
            throw new RuntimeException("Нет доступных вопросов");
        }
        Question[] questionArray = allQuestions.toArray(new Question[0]);
        Random random = new Random();
        int randomIndex = random.nextInt(questionArray.length);
        return questionArray[randomIndex];
    }

    @Override
    public Question addQuestion(String question, String answer) {
        return questionService.add(question, answer);
    }

    @Override
    public Question removeQuestion(String question) {
        return questionService.remove(question);
    }

    @Override
    public Question findQuestion(String question) {
        return questionService.find(question);
    }

    @Override
    public Collection<Question> getAllQuestions(int amount) {
        List<Question> allQuestions = new ArrayList<>(questionService.getAllQuestions());
        Collections.shuffle(allQuestions);
        return allQuestions.stream().limit(amount).collect(Collectors.toList());
    }
}