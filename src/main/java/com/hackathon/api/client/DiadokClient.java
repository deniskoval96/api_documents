package com.hackathon.api.client;

import com.hackathon.api.domain.Document;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class DiadokClient {

    // ToDo:
    // move to config
    // and autowire here
    private RestTemplate restTemplate = new RestTemplate();

    public void getDocuments() {

        String fooResourceUrl = "http://localhost:8080/spring-rest/foos";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);


//        Foo foo = restTemplate.getForObject(fooResourceUrl + "/1", Foo.class);


//        HttpEntity<Foo> request = new HttpEntity<>(new Foo("bar"));
//        Foo foo = restTemplate.postForObject(fooResourceUrl, request, Foo.class);



        String url = "";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

    }


    public String getAuthToken() {
        String url = "https://diadoc-api.kontur.ru/v3/Authenticate?type=password";

        // add headers
        // post

        return "";
    }



    //        которые находятся в статусе “Требуется подписание
    public List<Document> getAllNeedSigningDocuments() {


        return Collections.emptyList();
    }



    public List<Document> signDocuments(List<Document> documents) {

        return Collections.emptyList();
    }





}
