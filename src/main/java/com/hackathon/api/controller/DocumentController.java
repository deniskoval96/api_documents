package com.hackathon.api.controller;

import com.hackathon.api.domain.Document;
import com.hackathon.api.domain.User;
import com.hackathon.api.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;


//    GET /documents  -- get documents from service and store to DB
//                need to see progress on UI
//                OR showing progress wheel (Please wait)

    @GetMapping
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }


//  need to show progess for UI during signing files
//  OR showing progress wheel (Please wait)

    @PutMapping("/sign")
    public List<Document> signAllDocuments() {
        return documentService.signAllDocuments();
    }


    @PutMapping("/stop")
    public List<Document> stopSigning() {
        return documentService.stopSigning();
    }


}
