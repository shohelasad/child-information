package com.bd.childinfo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bd.childinfo.domain.Organization;
import com.bd.childinfo.domain.Registration;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

   
}
