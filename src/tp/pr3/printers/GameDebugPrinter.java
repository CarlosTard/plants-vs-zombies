package tp.pr3.printers;

import tp.pr3.gameLogic.Game;

public class GameDebugPrinter extends BoardPrinter implements GamePrinter {
	
	public GameDebugPrinter(int x, int y) {
		super(1, y, 19);
	}

	protected void encodeGame(Game game) {
		board = new String[1][dimY];
		for(int k = 0; k < this.dimY;++k) {
			board[0][k] = game.cellToDebugString(k);
		}
	}

	@Override
	public String printGame(Game game) {
		this.dimY = game.getNumberLiving();
		this.encodeGame(game);
		return game.getDebugInfo()
				+ this.boardToString();
	}
}
