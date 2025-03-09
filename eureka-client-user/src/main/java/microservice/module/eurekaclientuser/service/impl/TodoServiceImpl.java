package microservice.module.eurekaclientuser.service.impl;

import microservice.module.eurekaclientuser.entity.TodoEntity;
import microservice.module.eurekaclientuser.entity.UserEntity;
import microservice.module.eurekaclientuser.exception.TodoNotFoundException;
import microservice.module.eurekaclientuser.model.Todo;
import microservice.module.eurekaclientuser.repository.TodoRepo;
import microservice.module.eurekaclientuser.repository.UserRepo;
import microservice.module.eurekaclientuser.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Todo createTodo (TodoEntity todo, Long userid)
    {
        UserEntity user = userRepo.findById(userid).get();
        todo.setUser(user);
        return Todo.toModel(todoRepo.save(todo));
    }

    @Override
    public Todo complete (Long id) throws TodoNotFoundException
    {
        entityExistenceCheck(id);
        TodoEntity todo = todoRepo.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return Todo.toModel(todoRepo.save(todo));
    }

    @Override
    public Todo getOneTodo (Long id) throws TodoNotFoundException
    {
        entityExistenceCheck(id);
        return Todo.toModel(todoRepo.findById(id).get());
    }

    @Override
    public Long deleteTodo (Long id) throws TodoNotFoundException
    {
        entityExistenceCheck(id);
        todoRepo.deleteById(id);
        return id;
    }

    @Override
    public Todo updateTodoByID (Long id, TodoEntity todoEntity) throws TodoNotFoundException
    {
        entityExistenceCheck(id);

        TodoEntity todoFromDB = todoRepo.findById(id).get();

            todoFromDB.setTitle(todoEntity.getTitle());
            todoFromDB.setCompleted(todoEntity.getCompleted());
            todoFromDB.setUser(todoEntity.getUser());

        return Todo.toModel(todoRepo.save(todoFromDB));
    }

    private void entityExistenceCheck (Long id) throws TodoNotFoundException
    {
        if (!todoRepo.existsById(id))
            throw new TodoNotFoundException("Todo not found!");
    }
}