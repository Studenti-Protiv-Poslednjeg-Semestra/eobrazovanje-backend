package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Document;

import java.util.List;

public interface IDocumentService {

    Document findOne(Long id);

    List<Document> findAll();

    Document save(Document document);

    void delete(Long id);
}
