package tp.pr3.commands;

import tp.pr3.exceptions.CommandParseException;
import tp.pr3.gameLogic.Game;
import tp.pr3.util.MyStringUtils;
import tp.pr3.exceptions.CommandExecuteException;
import java.io.*;

public class SaveCommand extends Command{
	private String filename;
	
	public SaveCommand(){
		super("save", "save", "Save <namefile>: Save current state in namefile.dat");
	}
	public SaveCommand(String filename){
		this();
		this.filename = filename;
	}
	
	@Override
	public Command parse(String[] commandWords)
	throws CommandParseException {
		if(commandWords[0].toLowerCase().equals(this.commandName) ||
				   commandWords[0].toLowerCase().equals(this.idStr)){
			if(commandWords.length != 2)
				throw new CommandParseException("Command SaveCommand must have 2 arguments!");
			String filename = commandWords[1].toLowerCase();
			return new SaveCommand(filename);
		}
		else return null;
	}
	
	@Override
	public boolean execute(Game game) 
	throws CommandExecuteException {
		if(!MyStringUtils.isValidFilename(filename + ".dat")) {
			System.out.println("Invalid filename: the filename contains invalid characters");
			return false;
		}
		try (BufferedWriter outStream = new BufferedWriter(new FileWriter(
				this.filename + ".dat"))){
			outStream.write("Plants Vs Zombies v3.0");
			outStream.newLine();
			outStream.newLine();
			outStream.write(game.externalise());
		}
		catch (IOException exc) {
			throw new CommandExecuteException("Invalid File");
		}
		System.out.println("Game successfully saved in file " + filename  +
				".dat. Use the load command to reload it");
		return false;
	}
	
	
	
	
}