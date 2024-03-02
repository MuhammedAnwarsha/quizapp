package com.example.quizapp.service;

import com.example.quizapp.dao.QuestionDAO;
import com.example.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getAllQuestions() {

        return questionDAO.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {

        return questionDAO.findByCategory(category);
    }

    public String addQuestion(Question question) {

        questionDAO.save(question);
        return "Successfully add Question";
    }

    public String deleteQuestionById(Integer id) {

        questionDAO.deleteById(id);
        return "Question deleted successfully";
    }
}
