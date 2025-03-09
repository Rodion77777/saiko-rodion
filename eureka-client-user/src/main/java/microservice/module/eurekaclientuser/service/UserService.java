package microservice.module.eurekaclientuser.service;

import microservice.module.eurekaclientuser.entity.UserEntity;
import microservice.module.eurekaclientuser.exception.UserAlreadyExistException;
import microservice.module.eurekaclientuser.exception.UserNotFoundException;
import microservice.module.eurekaclientuser.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserEntity  registration        (UserEntity user)                throws UserAlreadyExistException;
    User        getOne              (Long id)                        throws UserNotFoundException;
    Long        deleteUser          (Long id)                        throws UserNotFoundException;
    UserEntity  updateUserByID      (Long id, UserEntity userEntity) throws UserNotFoundException;
    void        updateUserByIDQuery (Long id, UserEntity userEntity) throws UserNotFoundException;
}
