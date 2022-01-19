package tp.pr3.printers;


import tp.pr3.gameLogic.Game;
import tp.pr3.util.MyStringUtils;

public abstract class BoardPrinter {
	protected int dimX; 
	protected int dimY;
	protected String[][] board;
	private final String space = " ";
	protected int cellSize ;
	
	public BoardPrinter(int x,int y,int cellsize) {
		this.dimX = x;
		this.dimY = y;
		this.cellSize = cellsize;
	}

	protected abstract void encodeGame(Game game);

	public String boardToString() {
		int marginSize = 1;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
        str.append(lineDelimiter);
		
		for(int i=0; i<dimX; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<dimY; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(margin).append(lineDelimiter);
		}

		return str.toString();
	}
}
