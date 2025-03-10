package microservice.module.eurekaclientuser.repository;

import microservice.module.eurekaclientuser.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<UserEntity, Long>
{
    UserEntity findByUserName (String username);

    @Modifying
    @Query("UPDATE UserEntity u set u.password = :password, u.userName = :userName where u.id = :id")
    void updateUserQuery (@Param(value = "id") Long id, @Param(value = "password") String password, @Param(value = "userName") String userName);
    //void updateUserQuery(Long id, String password, String username);
}