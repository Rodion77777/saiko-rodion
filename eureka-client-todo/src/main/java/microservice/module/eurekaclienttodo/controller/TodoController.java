package microservice.module.eurekaclienttodo.controller;

import microservice.module.eurekaclienttodo.entity.TodoEntity;
import microservice.module.eurekaclienttodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController
{
    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity createTodo (@RequestBody TodoEntity todo, @RequestParam Long userid)
    {
        try {
            return ResponseEntity.ok(todoService.createTodo(todo, userid));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getOneTodo (@RequestParam Long id)
    {
        try {
            return ResponseEntity.ok(todoService.getOneTodo(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity completeTodo (@RequestParam Long id)
    {
        try {
            return ResponseEntity.ok(todoService.complete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo (@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(todoService.deleteTodo(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @PutMapping("/upd")
    public ResponseEntity updateTodo (@RequestParam Long id, @RequestBody TodoEntity todoEntity)
    {
        try {
            return ResponseEntity.ok(todoService.updateTodoByID(id, todoEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }
}
