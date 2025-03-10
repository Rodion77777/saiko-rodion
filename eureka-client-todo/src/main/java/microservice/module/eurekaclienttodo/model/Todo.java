package microservice.module.eurekaclienttodo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.module.eurekaclienttodo.entity.TodoEntity;

@NoArgsConstructor
@Data
public class Todo
{
    private Long id;
    private String title;
    private Boolean completed;

    public static Todo toModel (TodoEntity todoEntity)
    {
        Todo model = new Todo();
        model.setId(todoEntity.getId());
        model.setTitle(todoEntity.getTitle());
        model.setCompleted(todoEntity.getCompleted());
        return model;
    }
}
