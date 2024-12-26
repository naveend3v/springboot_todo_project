package com.in28minutes.rest.webservices.restfulwebservices.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoRestController {

    private TodoService todoService ;

    public TodoRestController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping(path = "/users/{username}/todos")
    public List<Todo> getAllTodosByUsername(@PathVariable String username){
        return todoService.findByUsername(username);
    }

    @GetMapping(path = "/users/{username}/todos/{id}")
    public Todo getTodosByID(@PathVariable String username, @PathVariable Integer id){
        return todoService.findById(id);
    }

    @DeleteMapping(path = "/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Integer id){
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable Integer id, @RequestBody Todo todo){
        todoService.updateTodo(todo);
        return todo;
    }

    @PostMapping(path = "/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo){
        Todo newTodo = todoService.addTodo(username,todo.getDescription(),todo.getTargetDate(),todo.isDone());
        return newTodo;
    }
}
