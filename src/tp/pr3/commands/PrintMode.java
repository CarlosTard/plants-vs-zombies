package tp.pr3.commands;

import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.exceptions.CommandParseException;
import tp.pr3.gameLogic.Game;

public class PrintMode extends Command {
	String mode;

	public PrintMode() {
		super("printmode","p","[P]rintMode <mode>: change print mode [R]elease or [D]ebug.");
	}
	public PrintMode(String mode) {
		this();
		this.mode = mode;
	}

	@Override
	public boolean execute(Game game) 
	throws CommandExecuteException{
		switch(this.mode) {
		case "release": case "r":
			game.setPrinter("r");
			break;
		case "debug": case "d":
			game.setPrinter("d");
			break;
		default:
			throw new CommandExecuteException("Invalid Print Mode: " + this.mode);
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].toLowerCase().equals(this.commandName) ||
				   commandWords[0].toLowerCase().equals(this.idStr)) {
			if(commandWords.length != 2){
				throw new CommandParseException("Incorrect number of arguments for printmode command");
			}	
			String mode = commandWords[1].toLowerCase();
			return new PrintMode(mode);
		}else return null;
	}
}
