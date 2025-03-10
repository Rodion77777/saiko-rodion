package microservice.module.eurekaclienttodo.exception;

public class TodoNotFoundException extends Exception
{
    public TodoNotFoundException (String message)
    {
        super(message);
    }
}
