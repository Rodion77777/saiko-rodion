package microservice.module.eurekaclienttodo.exception;

public class TodoAlreadyExistException extends Exception
{
    public TodoAlreadyExistException (String message)
    {
        super(message);
    }
}
