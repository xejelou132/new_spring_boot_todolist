package com.example.todolist.IntergrationTest;

import com.example.todolist.Model.Todo;
import com.example.todolist.Repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_list_of_todo_when_get_all_todo_given_get_request() throws Exception {

        final Todo todo = new Todo("First List todo");
        todoRepository.save(todo);

        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value("First List todo"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    void should_update_todo_when_put_specific_todo_given_put_employee_request_and_details() throws Exception {
        //given
        final Todo todo = new Todo("First List todo");
        final Todo savedTodo = todoRepository.save(todo);

        String todoWithNewInfo = "{\n" +
//                "       \"text\": \"First update todo\",\n" +
                "        \"done\": true\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}", savedTodo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoWithNewInfo))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.text").value("First update todo"))
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    void should_create_todos_when_create_given_todo_request() throws Exception {

        //given
        String todo = "{\n" +
                "        \"text\": \"First List todo\",\n" +
                "        \"done\": false\n" +
                "}";

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("First List todo"))
                .andExpect(jsonPath("$.done").value(false));

    }

    @Test
    void should_delete_todo_when_delete_request_given_delete_todo() throws Exception {
        final Todo todo = new Todo("First List todo");
        todoRepository.save(todo);

        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{id}", todo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").doesNotExist())
                .andExpect(jsonPath("$[0].text").doesNotExist())
                .andExpect(jsonPath("$[0].done").doesNotExist());
    }

}
