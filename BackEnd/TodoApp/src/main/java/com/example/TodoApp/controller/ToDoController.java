package com.example.TodoApp.controller;

import com.example.TodoApp.model.ToDo;
import com.example.TodoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:5174")
public class ToDoController {
    
    @Autowired
    private ToDoService toDoService;

     // Get all to-dos
    @GetMapping
    public List<ToDo> getAllToDos() {
        return toDoService.getAllToDos();
    }

    // Get a single to-do by ID
    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable String id) {
        Optional<ToDo> toDo = toDoService.getToDoById(id);
        return toDo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new to-do
    @PostMapping
    public ToDo createToDo(@RequestBody ToDo toDo) {
        return toDoService.createToDo(toDo);
    }

    // Update an existing to-do
    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable String id, @RequestBody ToDo toDoDetails) {
        ToDo updatedToDo = toDoService.updateToDo(id, toDoDetails);
        return ResponseEntity.ok(updatedToDo);
    }

    // Delete a to-do
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable String id) {
        toDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }
}

