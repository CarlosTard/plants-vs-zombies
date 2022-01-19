package tp.pr3;
import java.util.Scanner;
import tp.pr3.commands.Command;
import tp.pr3.commands.CommandParser;
import tp.pr3.gameLogic.Game;
import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.exceptions.CommandParseException;

public class Controller {
	private Game game;
	private Scanner in;
	private String errorMessage;
	
	public Controller(Game game){
		this.errorMessage = "Invalid Command\n";
		this.game = game;
		this.in = new Scanner(System.in);
	}

	public void run() {
		printGame();
		while (!game.isFinished()) {
			System.out.print("Command > ");
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
			try {
				Command command = CommandParser.parseCommand(words);
				if(command !=null) {
					if(command.execute(game))printGame();
				}else System.out.println(errorMessage);
			}
			catch (CommandParseException | CommandExecuteException ex){
				System.out.format(ex.getMessage() + " %n %n");
			}
		}
		if(game.isExit()) {
			System.out.println("Game over");
		} else if(this.game.userWins())
				System.out.println("User Wins");
		else System.out.println("Zombies Win");
		
	}
	public void printGame() {
			System.out.println(game);
	}
	
}
