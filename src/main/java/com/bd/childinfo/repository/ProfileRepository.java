package com.bd.childinfo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd.childinfo.domain.Profile;
import com.bd.childinfo.domain.User;

import java.util.Optional;
import java.util.Set;

/**
 * @uathor Asaduzzaman
 * @since 5/26/15.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Set<Profile> findAllByUserAndProfileDataType(User user, String profileDataType);

    Set<Profile> findAllByNameAndProfileDataType(String name, String profileDataType);

    Set<Profile> findAllByUserAndNameAndProfileDataType(User user, String name, String profileDataType);

    Set<Profile> findAllByUserAndNameAndProfileDataTypeAndProfileName(User user, String name, String profileDataType, String profileName);

    Optional<Profile> findOneById(Long id);
}
