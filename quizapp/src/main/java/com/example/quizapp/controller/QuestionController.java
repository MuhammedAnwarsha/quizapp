package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){

        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){

        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question question){

        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestionById(@PathVariable Integer id){

        return questionService.deleteQuestionById(id);
    }
}
