package com.example.springtutorial.survey.controller;

import com.example.springtutorial.question.model.Question;
import com.example.springtutorial.survey.service.SurveyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(SurveyController.class)
public class SurveyControllerTest {

    @MockBean
    private SurveyService surveyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void retrieveDetailsForQuestion() throws Exception {
        String expected = "{id:Question1,description:\"Largest Country in the World\",correctAnswer:Russia}";
        Question question = new Question(
                "Question1",
                "Largest Country in the World",
                "Russia",
                Arrays.asList("India", "Russia", "United States", "China")
        );
        Mockito.when(surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(question);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/surveys/Survey1/questions/Question1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
