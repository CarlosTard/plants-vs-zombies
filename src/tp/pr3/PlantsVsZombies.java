package tp.pr3;

import java.util.Random;

import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.exceptions.FileContentsException;
import tp.pr3.gameLogic.Game;
import tp.pr3.gameLogic.Level;
import java.io.IOException;
import java.lang.Exception;
import java.lang.RuntimeException;

public class PlantsVsZombies {
	public static void main(String[] args) {
		String message= "Usage: plantsVsZombies <" + Level.all("|") + "> [seed]";
		if (args.length != 2 && args.length != 1)
			System.out.println(message);
		else {
			Level level;
			level = Level.parse(args[0].toLowerCase());
			long seed = 0;
			try {
				seed = (args.length > 1) ? Long.parseLong(args[1]) : new Random().nextInt(1000);
				if (level != null) {
					Game game = new Game(seed, level);
					Controller controller = new Controller(game);
					game.setPrinter("r");
					controller.run();
				}
				else System.out.println(message + ": level must be one of: " + Level.all(","));
			}
			catch(NumberFormatException e) {
				System.out.println(message + ": The seed must be a number!");
			}
		}
	}
}
