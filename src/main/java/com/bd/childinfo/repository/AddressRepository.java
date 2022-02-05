package com.bd.childinfo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd.childinfo.domain.Address;
import com.bd.childinfo.domain.User;

/**
 * @author Md. Asaduzzaman
 * @date 4/26/15.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Set<Address> findByUser(User currentLoggedInUser);
}
