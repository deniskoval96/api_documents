package com.hackathon.api.client;

import com.hackathon.api.domain.Document;
import com.hackathon.api.domain.diadok.DiadokUserCreds;
import com.hackathon.api.domain.security.UserCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class DiadokClient {

    private final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final String BASE_DIADOK_TOKEN = "DiadocAuth ddauth_api_client_id=hackathon-27f5112d-3fe5-4fea-9f00-73d1782523d6";
    private final String ADDITIONAL_PART_OF_TOKEN = ",ddauth_token=";


    // ToDo:
    // move to config
    // and autowire here
    private RestTemplate restTemplate = new RestTemplate();

    // ToDo: move to db
    // private Map<String, String> userToken = new HashMap<>();
    // for simplifying
    private String fullUserDiadokToken;



    public void getDocuments() {

        String fooResourceUrl = "http://localhost:8080/spring-rest/foos";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);


//        Foo foo = restTemplate.getForObject(fooResourceUrl + "/1", Foo.class);


//        HttpEntity<Foo> request = new HttpEntity<>(new Foo("bar"));
//        Foo foo = restTemplate.postForObject(fooResourceUrl, request, Foo.class);



        String url = "";

//        RestTemplate restTemplate = new RestTemplate();

    }


    public String getAuthToken(UserCredentials creds) {
        String url = "https://diadoc-api.kontur.ru/v3/Authenticate?type=password";

        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.add("Content-Type", "application/json");
        headers.add(AUTHORIZATION_HEADER_NAME, BASE_DIADOK_TOKEN);
        HttpEntity<DiadokUserCreds> entity = new HttpEntity<>(getDiadokUserCreds(creds), headers);


        String token = "";
        try {
            ResponseEntity<String> authDiadokToken = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            System.out.println("+++++++++++++++++++++++++");
            System.out.println(authDiadokToken.getStatusCode());
            System.out.println(authDiadokToken.getBody());
            log.info("code {} body {}", authDiadokToken.getStatusCode(), authDiadokToken.getBody());

            token = authDiadokToken.getBody();
        } catch (HttpClientErrorException ex) {
            log.error("Exception", ex);
            throw new RuntimeException("Client is not reachable or you entered incorrect creds");
        }

        return token;
    }

    private DiadokUserCreds getDiadokUserCreds(UserCredentials creds) {
        return DiadokUserCreds.builder()
                    .login(creds.getUsername())
                    .password(creds.getPassword())
                    .build();
    }

    public String buildFullAuthToken(String ddauth_token) {
        StringBuilder diadokFullTokenBuilder = new StringBuilder();
        diadokFullTokenBuilder.append(BASE_DIADOK_TOKEN);
        diadokFullTokenBuilder.append(ADDITIONAL_PART_OF_TOKEN);
        diadokFullTokenBuilder.append(ddauth_token);
        return diadokFullTokenBuilder.toString();
    }

    public String saveToken(UserCredentials creds) {
        String diadokAuthToken = getAuthToken(creds);
        this.fullUserDiadokToken = buildFullAuthToken(diadokAuthToken);
        return this.fullUserDiadokToken;
    }

    public String getUserDiadokToken() {
        return fullUserDiadokToken;
    }


    //        которые находятся в статусе “Требуется подписание
    public List<Document> getAllNeedSigningDocuments() {


        return Collections.emptyList();
    }



    public List<Document> signDocuments(List<Document> documents) {

        return Collections.emptyList();
    }





}
