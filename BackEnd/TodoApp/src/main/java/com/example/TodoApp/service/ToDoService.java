package com.example.TodoApp.service;

import com.example.TodoApp.model.ToDo;
import com.example.TodoApp.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    
    @Autowired
    private ToDoRepository toDoRepository;


    //Get all to-dos
    public List<ToDo> getAllToDos(){
       return toDoRepository.findAll();
    }

    //Get to-do by id
    public Optional<ToDo> getToDoById(String id){
        return toDoRepository.findById(id);
    }

    // create a new to-do
    public ToDo createToDo(ToDo toDo){
        return toDoRepository.save(toDo);
    }

    // update a to-do
    public ToDo updateToDo(String id , ToDo toDoDetails){
        ToDo toDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        toDo.setTitle(toDoDetails.getTitle());
        toDo.setDescription(toDoDetails.getDescription());
        toDo.setCompleted(toDoDetails.isCompleted());
        return toDoRepository.save(toDo);

    }

    // Delete a to-do
    public void deleteToDo(String id) {
    toDoRepository.deleteById(id);

    }

}
