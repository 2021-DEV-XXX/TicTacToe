
public class Cell {
	private Players cellValue;
	private boolean isEmpty = true;
	
	public boolean isEmpty() {
		return this.isEmpty;
	}
	
	public Players getValue() {
		return this.cellValue;
	}
	
	public String display() {
		if (this.isEmpty) return " ";
		else if (cellValue == Players.ONE) return "X";
		else if (cellValue == Players.TWO) return "O";
		
		return null;
	}
	
	public boolean play(Players player) {
		if (!isEmpty)
			return false;
		
		cellValue = player;
		isEmpty = false;
		return true;
	}
}
