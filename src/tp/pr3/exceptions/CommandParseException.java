package tp.pr3.exceptions;
import java.lang.Exception;

public class CommandParseException extends Exception{
	public CommandParseException(String infoError){
		super(infoError);
	}
}