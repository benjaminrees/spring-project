package com.example.springproject;

import com.example.springproject.Enums.AviosResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringProjectITests {

        @Autowired
        private TestRestTemplate template;

        @Test
        public void getHello() throws Exception {
            ResponseEntity<String> response = template.getForEntity("/", String.class);
            assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
        }

        @Test
        public void getSingleAviosResponse() throws Exception {
                ResponseEntity<List<AviosResponse>> response = template.exchange("/returnAvios?dep=LHR&arr=LAX&code=M", HttpMethod.GET, null, new ParameterizedTypeReference<List<AviosResponse>>() {});
                AviosResponse responseBody = response.getBody().get(0);
                assertEquals(responseBody.getCabinCode(), "M");
                assertEquals(responseBody.getPoints(), 4500);
        }

        @Test
        public void getMultipleAviosReponse() throws Exception {
                ResponseEntity<List<AviosResponse>> response = template.exchange("/returnAvios?dep=LHR&arr=LAX", HttpMethod.GET, null, new ParameterizedTypeReference<List<AviosResponse>>() {});
                List<AviosResponse> responseBody = response.getBody();
                assertEquals(4, responseBody.size());
                assertEquals("M", responseBody.get(0).getCabinCode());
                assertEquals(4500, responseBody.get(0).getPoints());
                assertEquals("W", responseBody.get(1).getCabinCode());
                assertEquals(5400, responseBody.get(1).getPoints());
                assertEquals("J", responseBody.get(2).getCabinCode());
                assertEquals(6750, responseBody.get(2).getPoints());
                assertEquals("F", responseBody.get(3).getCabinCode());
                assertEquals(9000, responseBody.get(3).getPoints());

        }


}
