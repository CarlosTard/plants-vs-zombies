package tp.pr3.commands;
import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.exceptions.CommandParseException;
import tp.pr3.gameLogic.Game;


public abstract class Command{
	private final String helpInfo;
	protected final String commandName;
	protected final String idStr;
	
	public Command(String commandName,String idStr,String helpInfo){
		this.commandName = commandName;
		this.helpInfo = helpInfo;
		this.idStr = idStr;
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException;
	
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
	
	public String helpText(){
		return " " + helpInfo ;
	}
	
	
}