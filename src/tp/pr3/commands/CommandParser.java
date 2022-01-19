package tp.pr3.commands;
import tp.pr3.exceptions.CommandParseException;

public class CommandParser {
	private static Command[] availableCommands = {
			new AddCommand(),
			new AddZombieCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new SaveCommand(),
			new LoadCommand(),
			new ListPlantCommand(),
			new ListZombieCommand(),
			new PrintMode(),
			new UpdateCommand(),
			new SaveCommand(),
			new LoadCommand(),
	};
	public static Command parseCommand(String[] commandWords) throws CommandParseException{
		for(Command c: availableCommands) {
			Command com = c.parse(commandWords);
			if(com != null) return com;
		}
		return null;
	}
	public static String commandHelp() {
		StringBuilder str = new StringBuilder();
		for(Command c:availableCommands) {
			str.append(c.helpText() + System.lineSeparator());
		}
		return str.toString();
	}
}
