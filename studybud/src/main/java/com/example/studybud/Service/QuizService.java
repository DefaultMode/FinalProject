package com.example.studybud.Service;

import com.example.studybud.Repository.QuizRepo;
import com.example.studybud.ResourceNotFoundException;
import com.example.studybud.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class QuizService {

    private final QuizRepo quizRepo;

    @Autowired
    public QuizService(QuizRepo quizRepo) {
        this.quizRepo = quizRepo;
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }


    public List<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }


    public Quiz getQuizById(Long id) {
        return quizRepo.findById(id).orElse(null);
    }


    public void deleteQuiz(Long id) {
        quizRepo.deleteById(id);
    }
    public Quiz updateQuiz(Long id, Quiz quizDetails) {
        return quizRepo.findById(id)
                .map(quiz -> {
                    quiz.setTitle(quizDetails.getTitle());
                    quiz.setDescription(quizDetails.getDescription());
                    quiz.setCategory(quizDetails.getCategory());
                    quiz.setDifficulty(quizDetails.getDifficulty());
                    quiz.setQuestions(quizDetails.getQuestions());
                    quiz.setAnswers(quizDetails.getAnswers());
                    return quizRepo.save(quiz);
                }).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id " + id));
    }



}
