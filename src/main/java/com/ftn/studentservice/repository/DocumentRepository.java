package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}