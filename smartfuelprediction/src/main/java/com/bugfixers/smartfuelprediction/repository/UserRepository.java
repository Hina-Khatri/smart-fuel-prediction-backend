package com.bugfixers.smartfuelprediction.repository;

import com.bugfixers.smartfuelprediction.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
