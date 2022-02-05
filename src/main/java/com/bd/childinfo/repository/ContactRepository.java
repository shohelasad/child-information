package com.bd.childinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd.childinfo.domain.Contact;

import java.util.Optional;
import java.util.Set;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {



    Optional<Contact> findOneById(Long id);
}
