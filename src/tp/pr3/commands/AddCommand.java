package tp.pr3.commands;

import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.exceptions.CommandParseException;
import tp.pr3.factories.PlantFactory;
import tp.pr3.gameLogic.Game;
import tp.pr3.gameObjects.Plant;

public class AddCommand extends Command {
	String plant;
	int x;
	int y;

	public AddCommand() {
		super("add", "a", "[A]dd <plant> <x> <y>: Adds a plant in position x, y");
	}
	public AddCommand(String plant,int x,int y) {
		this();
		this.plant = plant;
		this.x = x;
		this.y = y;
	}
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		if (!game.plantInsideBoard(x, y)) {
			throw new CommandExecuteException("Esa casilla no está en el tablero.");
		} else if (!game.isEmptyCell(x, y)) {
			throw new CommandExecuteException("Ya hay alguien en esa casilla.");
		} else {
			Plant plnt = PlantFactory.getPlant(plant);
			if (plnt == null) {
				throw new CommandExecuteException("Esa planta no existe: " + plant);
			} else if (game.add(plnt, x, y)) {
				game.update();
			} else {
				throw new CommandExecuteException("No tienes suficientes suncoins");
			}
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords)throws CommandParseException {
		if(commandWords[0].toLowerCase().equals(this.commandName) ||
		   commandWords[0].toLowerCase().equals(this.idStr)){
			if(commandWords.length != 4) throw new CommandParseException("Invalid number of arguments"); 
			String plant = commandWords[1].toLowerCase();
			try {
				int x = Integer.parseInt(commandWords[2]);
				int y = Integer.parseInt(commandWords[3]);
				return new AddCommand(plant,x,y);
			}catch(NumberFormatException e) {
				throw new CommandParseException("x and y must be integers!");
			}
		} else return null;
	}

}