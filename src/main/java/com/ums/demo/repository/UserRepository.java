package com.ums.demo.repository;

import com.ums.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.firstName = :firstName")
    List<User> getByFirstName(@Param("firstName") String firstName);

    @Query(value = "SELECT * FROM users WHERE email LIKE '%@example.com'", nativeQuery = true)
    List<User> getUsersWithExampleDomain();
}
