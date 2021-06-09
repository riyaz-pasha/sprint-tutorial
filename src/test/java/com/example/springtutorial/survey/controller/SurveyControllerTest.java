package com.example.springtutorial.survey.controller;

import com.example.springtutorial.SpringTutorialApplication;
import com.example.springtutorial.question.model.Question;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringTutorialApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Before
    public void beforeEach() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRetrieveSurveyQuestion() throws JSONException {
        String url = createURLWithPort("/surveys/Survey1/questions/Question1");
        String expected = "{id:Question1,description:\"Largest Country in the World\",correctAnswer:Russia}";
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }


    @Test
    public void addQuestionToSurvey() {
        String url = createURLWithPort("/surveys/Survey1/questions");
        Question question = new Question(
                "DOESNTMATTER",
                "Question1",
                "Russia",
                Arrays.asList("India", "Russia", "United States", "China")
        );
        HttpEntity<String> entity = new HttpEntity(question, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertTrue(actual.contains("/surveys/Survey1/questions/"));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
