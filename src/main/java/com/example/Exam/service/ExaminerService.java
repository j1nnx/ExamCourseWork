package com.example.Exam.service;

import com.example.Exam.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getAllQuestions(int amount);
}
