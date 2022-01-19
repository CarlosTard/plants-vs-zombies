package tp.pr3.commands;

import tp.pr3.factories.ZombieFactory;
import tp.pr3.gameLogic.Game;
import tp.pr3.gameObjects.Zombie;

import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.exceptions.CommandParseException;

public class AddZombieCommand extends Command {
	String zombie;
	int x;
	int y;

	public AddZombieCommand() {
		super("addzombie", "addzombie", "addZombie> <x> <y>: Adds a zombie in position x, y");
	}
	public AddZombieCommand(String zombie,int x,int y) {
		this();
		this.zombie = zombie;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		if (!game.insideBoard(this.x, this.y))
			throw new CommandExecuteException("La casilla no está en el tablero.");
		else if (!game.isEmptyCell(x, y))
			throw new CommandExecuteException("Ya hay alguien en esa casilla.");
		else {
			Zombie zomb = ZombieFactory.getZombie(zombie);
			if (zomb == null) {
				throw new CommandExecuteException("Ese zombie no existe.");
			} else {
				game.addZombie(zomb, x, y);
			}
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords[0].toLowerCase().equals(this.commandName)) {
			if (commandWords.length != 4)
				throw new CommandParseException("Invalid number of arguments");
			String zombie = commandWords[1].toLowerCase();
			try {
				int x = Integer.parseInt(commandWords[2]);
				int y = Integer.parseInt(commandWords[3]);
				return new AddZombieCommand(zombie,x,y);
			} catch (NumberFormatException e) {
				throw new CommandParseException("x and y must be numbers!");
			}
			
		} else
			return null;
	}

}
