package microservice.module.eurekaclienttodo.repository;

import microservice.module.eurekaclienttodo.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<TodoEntity, Long>
{}
