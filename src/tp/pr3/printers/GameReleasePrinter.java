package tp.pr3.printers;

import tp.pr3.gameLogic.Game;

public class GameReleasePrinter extends BoardPrinter implements GamePrinter {

	public GameReleasePrinter(int x, int y) {
		super(x, y, 7);
	}

	protected void encodeGame(Game game) {
		this.board = new String[dimX][dimY];
		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {
				board[i][j] =  game.cellToString(i, j);
			}
		}
	}

	@Override
	public String printGame(Game game) {
		this.encodeGame(game);
		return game.getInfo() + this.boardToString(); 
		
	}

}
