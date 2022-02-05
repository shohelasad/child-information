package com.bd.childinfo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bd.childinfo.domain.PasswordResetToken;
import com.bd.childinfo.domain.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

}
