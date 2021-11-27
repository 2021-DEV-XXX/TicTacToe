import java.util.ArrayList;

public class Board extends ArrayList<Cell> {
	private static final int NB_OF_LINES = 3;
	private static final int NB_OF_COLUMNS = 3;
	private static final int NB_OF_CELLS = NB_OF_LINES * NB_OF_COLUMNS;
	
	public boolean play(int i, int j, Players currentPlayer) {
		Cell c = getCell(i, j);
		return c.play(currentPlayer);
	}
	private Cell getCell(int i, int j) {
		return this.get((NB_OF_COLUMNS*(i-1)) +(j-1));
	}
	
	private String displayCell(int i, int j) {
		Cell c = getCell(i, j);
		return c.display();
	}
	
	private boolean checkTriad(Cell[] cells) {
		for (Cell cell : cells) {
			if (cell.isEmpty())
				return false;
		}
		return ((cells[0].getValue() == cells[1].getValue())
				&& (cells[0].getValue() == cells[2].getValue()));
	}
	
	private boolean checkDiagon(int line, int column) {
		//first diagon
		if (line == column) {
			Cell[] cells = new Cell[3];
			
			cells[0] = getCell(1,1);
			cells[1] = getCell(2,2);
			cells[2] = getCell(3,3);
		
			if (checkTriad(cells))
				return true;
		}
		
		//second diagon
		if (line + column == 4) { // because the second diagon is has a director coef of -1 and is defined as y=3-x
			Cell[] cells = new Cell[3];
			
			cells[0] = getCell(3,1);
			cells[1] = getCell(2,2);
			cells[2] = getCell(1,3);
		
			if (checkTriad(cells))
				return true;
		}
		return false;
	}
	
	private boolean checkLine(int line) {
		Cell[] cells = new Cell[3];
		
		cells[0] = getCell(line,1);
		cells[1] = getCell(line,2);
		cells[2] = getCell(line,3);
		
		return checkTriad(cells);
	}
	
	private boolean checkColumn(int column) {
		Cell[] cells = new Cell[3];
		
		cells[0] = getCell(1,column);
		cells[1] = getCell(2,column);
		cells[2] = getCell(3,column);
		
		return checkTriad(cells);
	}
	
	public boolean isWinningCell(int i, int j) {
		if (checkLine(i)) return true;
		if (checkColumn(j)) return true;
		if (checkDiagon(i, j)) return true;
		return false;
	}
	
	public String display() {
		String[] lines = new String[NB_OF_LINES +2];
		lines[0] = "_______";
		lines[4] = "_______";
		for (int i=0; i<NB_OF_LINES; i++) {
			StringBuffer sb = new StringBuffer();
			
			sb.append("|");
			for(int j=0;j<NB_OF_COLUMNS; j++) {
				sb.append(this.displayCell(1+i, 1+j) + " ");
			}
			sb.setLength(sb.length() - 1); //removing last char which is just a space)
			sb.append("|");
			
			lines[i+1] = sb.toString();
		}
		return String.join("\n", lines);
	}
	
	public Board() {
		for (int i=0; i<Board.NB_OF_CELLS; i++)
			this.add(new Cell());
	}
}
