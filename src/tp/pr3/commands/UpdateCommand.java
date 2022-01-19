package tp.pr3.commands;

import tp.pr3.gameLogic.Game;
import tp.pr3.exceptions.CommandExecuteException;
import java.lang.Exception;

public class UpdateCommand extends NoParamsCommand{

	public UpdateCommand(){
		super("","n","[none]: Skips cycle.");
	}
	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}
	
}