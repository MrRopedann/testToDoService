package com.example.todoSevice.repos;

import com.example.todoSevice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
