package com.example.TodoApp.repository;

import com.example.TodoApp.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo , String> {
     // MongoRepository gives us basic CRUD operations (findAll, findById, save, delete)
}
