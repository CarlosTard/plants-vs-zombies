package tp.pr3.commands;

import tp.pr3.exceptions.CommandParseException;
import tp.pr3.exceptions.FileContentsException;
import tp.pr3.gameLogic.Game;
import tp.pr3.exceptions.CommandExecuteException;
import java.lang.Exception;
import java.util.Scanner;
import java.io.*;
import tp.pr3.util.MyStringUtils;

public class LoadCommand extends Command {
	private String filename;

	public LoadCommand() {
		super("load", "load", "Load <namefile>: Loads game state from namefile.dat");
	}

	public LoadCommand(String filename) {
		this();
		this.filename = filename;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords[0].toLowerCase().equals(this.commandName)
				|| commandWords[0].toLowerCase().equals(this.idStr)) {
			if (commandWords.length != 2)
				throw new CommandParseException("Command LoadCommand must have 2 arguments!");
			String filename = commandWords[1].toLowerCase();
			return new LoadCommand(filename);
		} else
			return null;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		if (!MyStringUtils.isValidFilename(this.filename))
			throw new CommandExecuteException("That's not a valid filename!");
		else if (!MyStringUtils.fileExists(this.filename))
			throw new CommandExecuteException("The file doesn't exist!");
		else if (!MyStringUtils.isReadable(this.filename))
			throw new CommandExecuteException("The file is not readable!");

		try (BufferedReader inStream = new BufferedReader(new FileReader(this.filename))) {

			String read = inStream.readLine();
			if (!read.toLowerCase().trim().equals("plants vs zombies v3.0"))
				throw new CommandExecuteException("That file has nothing to do with Plants Vs Zombies");
			read = inStream.readLine();
			game.load(inStream);

		} catch (IOException e) {
				throw new CommandExecuteException("Invalid File");
		} catch (FileContentsException e) {
			throw new CommandExecuteException(e.getMessage());
		}
		System.out.println("Game successfully loaded from file: " + this.filename + System.lineSeparator());

		return true;
	}

}