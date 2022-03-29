package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Document;
import com.ftn.studentservice.repository.DocumentRepository;
import com.ftn.studentservice.service.IDocumentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService implements IDocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document findOne(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public void delete(Long id) {
        documentRepository.deleteById(id);
    }
}
