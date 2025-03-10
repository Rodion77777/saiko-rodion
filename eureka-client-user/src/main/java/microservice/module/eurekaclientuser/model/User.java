package microservice.module.eurekaclientuser.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.module.eurekaclientuser.entity.UserEntity;

import java.util.List;

@NoArgsConstructor
@Data
public class User
{
    private Long id;
    private String username;
    private List<Long> todoId;

    public static User toModel (UserEntity entity)
    {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUserName());
        model.setTodoId(entity.getTodoId());
        return model;
    }
}
