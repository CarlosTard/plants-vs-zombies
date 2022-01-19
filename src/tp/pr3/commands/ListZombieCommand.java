package tp.pr3.commands;

import tp.pr3.factories.ZombieFactory;
import tp.pr3.gameLogic.Game;

public class ListZombieCommand extends NoParamsCommand {

	public ListZombieCommand() {
		super("zombielist","zombielist","zombieList: Prints the list of available zombies.");
	}

	@Override
	public boolean execute(Game game) {
		System.out.print(ZombieFactory.listOfAvailableZombies());
		return false;
	}

}
