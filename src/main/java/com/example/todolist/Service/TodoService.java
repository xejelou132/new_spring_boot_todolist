package com.example.todolist.Service;

import com.example.todolist.Exception.TodoNotFound;
import com.example.todolist.Model.Todo;
import com.example.todolist.Repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TodoService {
    @Resource
    TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodos(Todo todos) {
        return todoRepository.save(todos);
    }

    public Todo updateTodo(String id, Todo updateTodo) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFound("Todo not found"));
       if(updateTodo.getDone() != todo.getDone()) {
           todo.setDone(updateTodo.getDone());
       }
       if(updateTodo.getText() != null) {
           todo.setText(updateTodo.getText());
       }
        return todoRepository.save(todo);
    }

    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }
}
