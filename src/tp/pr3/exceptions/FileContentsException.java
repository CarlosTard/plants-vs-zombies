package tp.pr3.exceptions;
import java.lang.Exception;

public class FileContentsException extends Exception{
	public FileContentsException(String infoError){
		super(infoError);
	}
}
