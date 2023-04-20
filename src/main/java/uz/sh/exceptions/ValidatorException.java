package uz.sh.exceptions;

/**
 * @author Botirov Najmiddin, Tue 12:02 PM. 8/2/2022
 */
public class ValidatorException extends RuntimeException{
    public ValidatorException(String message, Long id) {
        super(message + id);
    }
    
    public ValidatorException(String message) {
        super(message);
    }
}
