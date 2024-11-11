public class InvalidPasswordException extends RuntimeException{
    /**
     * Makes new InvalidPasswordException with inputted message
     * @param message The input message
     */
    public InvalidPasswordException(String message){
        super(message);
    }
}