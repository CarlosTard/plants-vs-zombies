package tp.pr3.commands;

import tp.pr3.gameLogic.Game;
import tp.pr3.exceptions.CommandExecuteException;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand(){
		super("exit","e","[E]xit: Terminates the program");
	}
	
	public boolean execute(Game game)throws CommandExecuteException{
		game.setExit();
		return false;
	}

}
