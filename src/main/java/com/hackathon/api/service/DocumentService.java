package com.hackathon.api.service;

import com.hackathon.api.client.DiadokClient;
import com.hackathon.api.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DiadokClient diadokClient;


    public List<Document> getAllDocuments() {

        List<Document> documents = diadokClient.getAllNeedSigningDocuments();

        // save to db
        // return to FE

        return Collections.emptyList();
    }


    public List<Document> signAllDocuments() {

        List<Document> documents = Collections.emptyList();
        // get documents from DB

//        diadokClient.signDocuments();

        // sign one by one ???
        // to make it stoppable

//        diadokClient.signDocument();


        return Collections.emptyList();
    }


    public List<Document> stopSigning() {

        return Collections.emptyList();
    }



}
