package com.example.springtutorial.survey.controller;

import com.example.springtutorial.question.model.Question;
import com.example.springtutorial.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId) {
        return surveyService.retrieveQuestions(surveyId);
    }

    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveDetailsForQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
        return surveyService.retrieveQuestion(surveyId, questionId);
    }

    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity addQuestionToTheSurvey(@PathVariable String surveyId, @RequestBody Question question) {
        Question newQuestion = surveyService.addQuestion(surveyId, question);
        if (newQuestion == null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().
                        path("/{id}")
                .buildAndExpand(newQuestion.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
