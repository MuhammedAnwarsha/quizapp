package com.example.quizapp.service;

import com.example.quizapp.dao.QuestionDAO;
import com.example.quizapp.dao.QuizDao;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionWrapper;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDAO questionDAO;

    public String createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return "Successfully create a quiz";
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz =quizDao.findById(id);
        List<Question> questionsFromDB =quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUSer = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUSer.add(qw);
        }

        return questionsForUSer;
    }

    public Integer calculateResult(Integer id, List<Response> responses) {

        Quiz quiz =quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
            right++;

            i++;
        }
        return right;
    }
}
