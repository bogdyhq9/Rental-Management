package repository;

public class NonExistingIDException extends Exception{
    public NonExistingIDException(String message)
    {
        super(message);
    }
}
