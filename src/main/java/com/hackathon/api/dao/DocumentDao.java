package com.hackathon.api.dao;

import com.hackathon.api.domain.Document;

import java.util.List;

public interface DocumentDao {


    List<Document> getAllSignableDocuments();

}
