package com.example.Exam.service;

import com.example.Exam.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final List<Question> questions = new ArrayList<>() {
        {
            add(new Question("Что такое Java?", "Java — это объектно-ориентированный язык программирования."));
            add(new Question(" Какие типы данных в Java есть?", "byte, short, int, long, float, double, char, boolean"));
        }
    };

    @Override
    public Question add(String question, String answer) {
        Question addQuestion = new Question(question, answer);
        questions.add(addQuestion);
        return addQuestion;
    }

    @Override
    public Question remove(String question) {
        Iterator<Question> iterator = questions.iterator();
        while (iterator.hasNext()) {
            Question removeQuestion = iterator.next();
            if (removeQuestion.getQuestion().equals(question)) {
                iterator.remove();
                return removeQuestion;
            }
        }
        throw new RuntimeException("Такого вопроса нет");
    }

    @Override
    public Question find(String question) {
        for (Question findQuestion : questions) {
            if (findQuestion.getQuestion().equals(question)) {
                return findQuestion;
            }
        }
        throw new RuntimeException("Такого вопроса нет");
    }

    @Override
    public Collection<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new RuntimeException("Список вопросов пуст");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(questions.size());
        return questions.get(randomIndex);
    }
}