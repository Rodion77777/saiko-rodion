package microservice.module.eurekaclienttodo.service;

import microservice.module.eurekaclienttodo.entity.TodoEntity;
import microservice.module.eurekaclienttodo.exception.TodoNotFoundException;
import microservice.module.eurekaclienttodo.model.Todo;
import org.springframework.stereotype.Service;

@Service
public interface TodoService
{
    Todo createTodo             (TodoEntity todo, Long userid);
    Todo complete               (Long id)                           throws TodoNotFoundException;
    Todo getOneTodo             (Long id)                           throws TodoNotFoundException;
    Long deleteTodo             (Long id)                           throws TodoNotFoundException;
    Todo updateTodoByID         (Long id, TodoEntity todoEntity)    throws TodoNotFoundException;
}
