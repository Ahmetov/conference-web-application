package com.ahmetov.conference.repository;

import com.ahmetov.conference.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    void deleteUserById(Long id);

    User findByUsername(String username);

}
