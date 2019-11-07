package com.example.todoSevice.repos;

import com.example.todoSevice.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    /* Метод для поиска в БД по Метке */
    List<Message> findByTag(String tag);
}
