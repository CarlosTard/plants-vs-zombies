package tp.pr3.commands;

import tp.pr3.gameLogic.Game;
import java.lang.Exception;
import tp.pr3.exceptions.CommandExecuteException;

public class ResetCommand extends NoParamsCommand{

	public ResetCommand(){
		super("reset","r","[R]eset: Starts a new game.");
	}

	@Override
	public boolean execute(Game game){
		game.reset();
		return true;
	}
	
}