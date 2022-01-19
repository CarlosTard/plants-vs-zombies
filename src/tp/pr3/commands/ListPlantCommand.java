package tp.pr3.commands;

import tp.pr3.factories.PlantFactory;
import tp.pr3.gameLogic.Game;

public class ListPlantCommand extends NoParamsCommand {

	public ListPlantCommand(){
		super("list","l","[L]ist: Prints the list of available plants.");
	}

	@Override
	public boolean execute(Game game) {
		System.out.print(PlantFactory.listOfAvailablePlants());
		return false;
	}
}
