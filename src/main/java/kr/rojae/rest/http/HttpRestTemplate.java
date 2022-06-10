package kr.rojae.rest.http;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class HttpRestTemplate {
    private HttpClient httpClient;

    public HttpRestTemplate(HttpClient httpClient){
        this.httpClient = httpClient;
    }

    public HttpRestTemplate(@NonNull HttpMethod httpMethod, String url){
        httpClient = new HttpClient(httpMethod, url);
    }

    public HttpRestTemplate(@NonNull HttpMethod httpMethod, String url,
                            MultiValueMap<String, String> params){
        httpClient = new HttpClient(httpMethod, url, params);
    }

    public HttpRestTemplate(@NonNull HttpMethod httpMethod, String url,
                            HttpHeader headers){
        httpClient = new HttpClient(httpMethod, url, headers);
    }

    public HttpRestTemplate(@NonNull HttpMethod httpMethod, String url,
                            MultiValueMap<String, String> params,
                            HttpHeader headers){
        httpClient = new HttpClient(httpMethod, url, params, headers);
    }

    public void addHeader(String key, String value){
        this.httpClient.getHeaders().addHeader(key, value);
    }

    public void addHeader(Map<String, String> newKeyValue) {
        this.httpClient.getHeaders().addHeader(newKeyValue);
    }

    public void addParam(String key, String value){
        this.httpClient.addParam(key, value);
    }

    public <T> ResponseEntity<T> send(Class<T> responseClassType){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            log.info("HTTP : {} || Destination : {} || Header : {} || Body : {} ",
                    request.getMethod(), request.getURI(), request.getHeaders(), new String(body, StandardCharsets.UTF_8));
            return execution.execute(request, body);
        });

        if(this.httpClient.getMethod().equals(HttpMethod.GET)){
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(this.httpClient.getUrl());
            if(this.httpClient.getParams() != null) this.httpClient.getParams().forEach(uriBuilder::queryParam);
            return restTemplate.getForEntity(uriBuilder.toUriString(), responseClassType);
        }
        else if(this.httpClient.getMethod().equals(HttpMethod.POST)){
            return restTemplate.postForEntity(this.httpClient.getUrl(), this.httpClient.getParams(), responseClassType);
        }
        else if(this.httpClient.getMethod().equals(HttpMethod.PUT)){
            return restTemplate.exchange(this.httpClient.getUrl(), HttpMethod.PUT, this.httpClient.toEntity(), responseClassType);
        }
        else if(this.httpClient.getMethod().equals(HttpMethod.PATCH)){
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            return restTemplate.exchange(this.httpClient.getUrl(), HttpMethod.PATCH, this.httpClient.toEntity(), responseClassType);
        }
        else if(this.httpClient.getMethod().equals(HttpMethod.DELETE)) {
            return restTemplate.exchange(this.httpClient.getUrl(), HttpMethod.DELETE, this.httpClient.toEntity(), responseClassType);
        }
        else{
            log.info("Not yet implement Method : {} ", this.httpClient.getMethod());
            return null;
        }
    }

}
