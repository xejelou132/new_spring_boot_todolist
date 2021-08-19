package com.example.todolist.Advice;

import com.example.todolist.Exception.TodoNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponse employeeNotFoundExceptionHandling(TodoNotFound todoNotFound) {
        return new ErrorResponse(todoNotFound.getMessage(), HttpStatus.NOT_FOUND.name());
    }
}
