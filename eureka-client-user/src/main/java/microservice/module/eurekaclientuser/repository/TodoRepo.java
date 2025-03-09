package microservice.module.eurekaclientuser.repository;

import microservice.module.eurekaclientuser.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<TodoEntity, Long>
{}
