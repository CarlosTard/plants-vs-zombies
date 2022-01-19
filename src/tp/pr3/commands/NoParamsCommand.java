package tp.pr3.commands;
 
import tp.pr3.exceptions.CommandParseException;

public abstract class NoParamsCommand extends Command{
	
	public NoParamsCommand(String commandName,String idStr, String helpInfo){
		super(commandName,idStr,helpInfo);
	}

	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords[0].isEmpty() && this.commandName.isEmpty() ||
		   commandWords[0].toLowerCase().equals(this.commandName.toLowerCase()) ||
		   commandWords[0].toLowerCase().equals(this.idStr.toLowerCase()) ){
			if(commandWords.length !=1) 
				throw new CommandParseException("Incorrect Number of arguments");
			return this;
		} else return null;
	}
}