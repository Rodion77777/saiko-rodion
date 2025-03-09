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

/*
*   @MODIFYING
* Аннотация @Modifying используется в Spring Data JPA для пометки метода в репозитории как запроса на изменение данных.
* Эта аннотация сообщает Spring Data JPA, что метод должен быть обработан как изменяющий запрос
* (например, запрос на обновление, удаление, вставку), а не как запрос на чтение данных.
* Обычно эта аннотация используется вместе с аннотацией @Query для выполнения пользовательского запроса на изменение данных в базе данных.
*
*   @PARAM
* Аннотация @Param в Spring используется для связывания параметров метода с именами параметров в SQL запросах,
* которые определены в аннотациях @Query, @Procedure и т. д.
* Это позволяет использовать именованные параметры в SQL запросах вместо позиционных параметров.
* Обратите внимание, что если имена параметров метода и имена параметров в SQL запросе совпадают,
* то @Param можно опустить.
*
* */