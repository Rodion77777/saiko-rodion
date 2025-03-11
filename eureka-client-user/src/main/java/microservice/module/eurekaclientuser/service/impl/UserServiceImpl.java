package microservice.module.eurekaclientuser.service.impl;

import microservice.module.eurekaclientuser.entity.UserEntity;
import microservice.module.eurekaclientuser.exception.UserAlreadyExistException;
import microservice.module.eurekaclientuser.exception.UserNotFoundException;
import microservice.module.eurekaclientuser.model.User;
import microservice.module.eurekaclientuser.repository.UserRepo;
import microservice.module.eurekaclientuser.service.UserService;
import microservice.module.eurekaclientuser.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepo userRepo;

    @Override
    public String login (UserEntity user) throws UserNotFoundException
    {
        UserEntity candidate = userRepo.findByUserName(user.getUserName());
        // проверка наличия пользователя, если нет - выбрасываем исключение
        if (candidate == null)
            throw new UserNotFoundException("User not found");

        // если пользователь есть, сравниваем пароль
        boolean isMatchPass = new BCryptPasswordEncoder().matches(user.getPassword(), candidate.getPassword());
        return isMatchPass ? JwtUtil.generateToken(user.getUserName()) : null;
    }

    @Override
    public UserEntity registration (UserEntity user) throws UserAlreadyExistException
    {
        // Проверка наличия такого пользователя
        if (userRepo.findByUserName(user.getUserName()) != null)
            throw new UserAlreadyExistException("User already exists!");
        // Так как пользователь не найден, шифруем его пароль перед регистрацией пользователя
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        // Сохраняем объект в базу
        return userRepo.save(user);
    }

    @Override
    public User getOne (Long id) throws UserNotFoundException
    {
        if (userRepo.findById(id).isEmpty())
            throw new UserNotFoundException("User not found!");
        return User.toModel(userRepo.findById(id).get());
    }

    @Override
    public Long deleteUser (Long id) throws UserNotFoundException
    {
        if (!userRepo.existsById(id))
            throw new UserNotFoundException("User not found!");
        userRepo.deleteById(id);
        return id;
    }

    @Override
    public UserEntity updateUserByID (Long id, UserEntity userEntity) throws UserNotFoundException
    {
        UserEntity userFromDB = userRepo.findById(id).get();

            userFromDB.setUserName(userEntity.getUserName());
            userFromDB.setPassword(userEntity.getPassword());

        return userRepo.save(userFromDB);
    }

    @Override
    public void updateUserByIDQuery (Long id, UserEntity userEntity) throws UserNotFoundException
    {
        if (!userRepo.existsById(id))
            throw new UserNotFoundException("User not found");
        userRepo.updateUserQuery(id, userEntity.getPassword(), userEntity.getUserName());
    }
}