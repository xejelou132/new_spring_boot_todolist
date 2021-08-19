package com.example.todolist.Exception;

public class TodoNotFound extends  RuntimeException{
    String message;
    public TodoNotFound(String message) {
        super(message);
    }
}
