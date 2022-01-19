package tp.pr3.exceptions;
import java.lang.Exception;

public class CommandExecuteException extends Exception{
	public CommandExecuteException(String cause){
		super(cause);
	}
}