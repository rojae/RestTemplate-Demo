package kr.rojae.rest;

import kr.rojae.rest.http.HttpHeader;
import kr.rojae.rest.http.RestProvider;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
@Slf4j
public class RestTemplateTest {

    @Autowired
    private RestProvider restProvider;

    @Autowired
    private ApiProp apiProp;

    @Test
    public void getTest1(){
        ResponseEntity<Person[]> response = restProvider.send(HttpMethod.GET, apiProp.getUrl1, Person[].class);
        Person[] persons = response.getBody();
        assert persons != null;
        Arrays.stream(persons).forEach(p -> log.info(
            p.toString()
        ));
    }

    @Test
    public void getTest2(){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("postId", Collections.singletonList("1"));

        ResponseEntity<Comment[]> response = restProvider.send(HttpMethod.GET, apiProp.getUrl2, params, new HttpHeader(MediaType.APPLICATION_JSON), Comment[].class);
        Comment[] comments = response.getBody();
        assert comments != null;
        Arrays.stream(comments).forEach(c -> log.info(
                c.toString()
        ));
    }

    @Test
    public void postTest1(){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("postId", Collections.singletonList("bodyValue..."));

        ResponseEntity<PersonEnrollResponse> response = restProvider.send(HttpMethod.POST, apiProp.postUrl, params, new HttpHeader(MediaType.APPLICATION_JSON), PersonEnrollResponse.class);
        PersonEnrollResponse data = response.getBody();
        assert data != null;
        log.info(data.toString());
    }

    @Test
    public void putTest1(){
        ResponseEntity<PersonEnrollResponse> response = restProvider.send(HttpMethod.PUT, apiProp.putUrl, new HttpHeader(MediaType.APPLICATION_JSON), PersonEnrollResponse.class);
        PersonEnrollResponse data = response.getBody();
        assert data != null;
        log.info(data.toString());
    }

    @Test
    public void patchTest1(){
        ResponseEntity<Person> response = restProvider.send(HttpMethod.PATCH, apiProp.patchUrl, new HttpHeader(MediaType.APPLICATION_JSON), Person.class);
        Person data = response.getBody();
        assert data != null;
        log.info(data.toString());
    }

    @Test
    public void deleteTest1(){
        ResponseEntity<PersonEnrollResponse> response = restProvider.send(HttpMethod.DELETE, apiProp.deleteUrl, new HttpHeader(MediaType.APPLICATION_JSON), PersonEnrollResponse.class);
    }

}
