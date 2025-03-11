package com.example.Exam.controller;

import com.example.Exam.model.Question;
import com.example.Exam.service.JavaQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    @Autowired
    private JavaQuestionService javaQuestionService;

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return javaQuestionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question) {
        return javaQuestionService.remove(question);
    }


    @GetMapping("/find")
    public Question findQuestion(@RequestParam String question) {
        return javaQuestionService.find(question);
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return javaQuestionService.getAllQuestions();
    }
    @GetMapping("/random")
    public Question getRandomQuestion() {
        return javaQuestionService.getRandomQuestion();
    }
}