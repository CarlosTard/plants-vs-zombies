package tp.pr3.commands;

import tp.pr3.gameLogic.Game;

public class HelpCommand extends NoParamsCommand{

	public HelpCommand(){
		super("help","h","[H]elp: Prints this help message");
	}

	@Override
	public boolean execute(Game game) {
		System.out.print(CommandParser.commandHelp());
		return false;
	}
}