package microservice.module.eurekaclientuser.exception;

public class TodoAlreadyExistException extends Exception
{
    public TodoAlreadyExistException (String message)
    {
        super(message);
    }
}
