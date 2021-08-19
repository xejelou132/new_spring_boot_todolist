package com.example.todolist.Advice;

public class ErrorResponse {
    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public ErrorResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
