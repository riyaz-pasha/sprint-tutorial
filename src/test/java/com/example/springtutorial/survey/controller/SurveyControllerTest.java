package com.example.springtutorial.survey.controller;

import com.example.springtutorial.SpringTutorialApplication;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringTutorialApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void testRetrieveSurveyQuestion() throws JSONException {
        String url = "http://localhost:" + port + "/surveys/Survey1/questions/Question1";
        String expected = "{id:Question1,description:\"Largest Country in the World\",correctAnswer:Russia}";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }
}
