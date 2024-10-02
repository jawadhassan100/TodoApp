package com.example.TodoApp.model;

// import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Data  // Automatically generates getters, setters, and other utility methods (e.g., toString(), equals()).
@Document(collection = "todos") //Tells Spring Data MongoDB that this is a document in the MongoDB collection called "todos."

public class ToDo {
    @Id // Marks the id field as the primary key in MongoDB.
    private String id;    // MongoDB automatically generates this ID
    private String title;
    private String description;
    private boolean completed;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}


