package microservice.module.eurekaclientuser.service;

import microservice.module.eurekaclientuser.entity.TodoEntity;
import microservice.module.eurekaclientuser.exception.TodoNotFoundException;
import microservice.module.eurekaclientuser.model.Todo;
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
