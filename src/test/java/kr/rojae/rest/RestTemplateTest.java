package kr.rojae.rest;

import kr.rojae.rest.http.HttpHeader;
import kr.rojae.rest.http.HttpRestTemplate;
import kr.rojae.rest.model.Comment;
import kr.rojae.rest.model.Person;
import kr.rojae.rest.model.PersonEnrollResponse;
import kr.rojae.rest.props.ApiProp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;

@SpringBootTest
@Slf4j
public class RestTemplateTest {

    @Autowired
    private ApiProp apiProp;

    @Test
    public void getTest1(){
        HttpRestTemplate restTemplate = new HttpRestTemplate(HttpMethod.GET, apiProp.getUrl1);
        ResponseEntity<Person[]> response = restTemplate.send(Person[].class);
        Person[] persons = response.getBody();
        assert persons != null;
        Arrays.stream(persons).forEach(p -> log.info(
            p.toString()
        ));
    }

    @Test
    public void getTest2(){
        HttpRestTemplate restTemplate = new HttpRestTemplate(HttpMethod.GET, apiProp.getUrl2, new HttpHeader(MediaType.APPLICATION_JSON));
        restTemplate.addParam("postId", "1");
        ResponseEntity<Comment[]> response = restTemplate.send(Comment[].class);
        Comment[] comments = response.getBody();
        assert comments != null;
        Arrays.stream(comments).forEach(c -> log.info(
                c.toString()
        ));
    }

    @Test
    public void postTest1(){
        HttpRestTemplate restTemplate = new HttpRestTemplate(HttpMethod.POST, apiProp.postUrl, new HttpHeader(MediaType.APPLICATION_JSON));
        restTemplate.addParam("body", "bodyValue...");
        ResponseEntity<PersonEnrollResponse> response = restTemplate.send(PersonEnrollResponse.class);
        PersonEnrollResponse data = response.getBody();
        assert data != null;
        log.info(data.toString());
    }

    @Test
    public void putTest1(){
        HttpRestTemplate restTemplate = new HttpRestTemplate(HttpMethod.PUT, apiProp.putUrl, new HttpHeader(MediaType.APPLICATION_JSON));
        ResponseEntity<PersonEnrollResponse> response = restTemplate.send(PersonEnrollResponse.class);
        PersonEnrollResponse data = response.getBody();
        assert data != null;
        log.info(data.toString());
    }

    @Test
    public void patchTest1(){
        HttpRestTemplate restTemplate = new HttpRestTemplate(HttpMethod.PATCH, apiProp.patchUrl, new HttpHeader(MediaType.APPLICATION_JSON));
        ResponseEntity<Person> response = restTemplate.send(Person.class);
        Person data = response.getBody();
        assert data != null;
        log.info(data.toString());
    }

    @Test
    public void deleteTest1(){
        HttpRestTemplate restTemplate = new HttpRestTemplate(HttpMethod.DELETE, apiProp.deleteUrl, new HttpHeader(MediaType.APPLICATION_JSON));
        restTemplate.send(PersonEnrollResponse.class);
    }

}
