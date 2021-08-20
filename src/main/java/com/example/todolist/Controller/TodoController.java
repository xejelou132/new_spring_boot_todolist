package com.example.todolist.Controller;

import com.example.todolist.Model.Todo;
import com.example.todolist.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping()
    public List<Todo> getAllEmployees() {
        return todoService.getAllTodos();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        Todo addNewTodo = todoService.addTodos(todo);
        return addNewTodo;
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable String id, @RequestBody Todo updateTodo) {
        return todoService.updateTodo(id, updateTodo);

    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
    }

}
