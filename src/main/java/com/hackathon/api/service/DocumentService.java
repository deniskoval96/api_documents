package com.hackathon.api.service;

import com.hackathon.api.client.DiadokClient;
import com.hackathon.api.dao.DocumentDao;
import com.hackathon.api.domain.Document;
import com.hackathon.api.domain.SignedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DiadokClient diadokClient;

    @Autowired
    private DocumentDao documentDao;


    public List<Document> getAllDocuments() {

        List<Document> documents = diadokClient.getAllNeedSigningDocuments();

        // get metainfo by documents
        documents.forEach(document -> document.setSignedStatus(getStatusForDocumentPrice(document)));


        // get documents

        // save to db
        // return to FE

        return Collections.emptyList();
    }

    private SignedStatus getStatusForDocumentPrice(Document document) {
        return document.getPrice() > 5000 ?  SignedStatus.WAIT_SIGNING : SignedStatus.NOT_SIGNABLE;
    }


    public List<Document> signAllDocuments() {

        List<Document> documents = documentDao.getAllSignableDocuments();

//        diadokClient.signDocuments();

        // sign one by one ???
        // to make it stoppable

//        diadokClient.signDocument();


        return Collections.emptyList();
    }




    // Need to figure out the way of stopping sign method
    // interruption flag ???
    public List<Document> stopSigning() {

        return Collections.emptyList();
    }

    public ResponseEntity<byte[]> downloadDocument(Integer documentId) {

        return ResponseEntity.of(Optional.of(new byte[10]));
    }



}
