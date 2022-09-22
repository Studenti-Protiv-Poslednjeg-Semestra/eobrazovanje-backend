package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority getAuthorityByName(String name);
}
