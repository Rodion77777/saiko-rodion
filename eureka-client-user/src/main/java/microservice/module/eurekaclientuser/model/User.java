package microservice.module.eurekaclientuser.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.module.eurekaclientuser.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class User
{
    private Long id;
    private String username;
    private List<Todo> todos;

    public static User toModel (UserEntity entity)
    {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUserName());
        model.setTodos(entity.getTodos().stream().map(Todo::toModel).collect(Collectors.toList()));
        return model;
    }
}
