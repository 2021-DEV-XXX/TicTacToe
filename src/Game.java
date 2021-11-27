import java.io.Console;
import java.util.Scanner;

public class Game {
	private final Scanner scanner = new Scanner(System.in);
	
	private Players currentPlayer = Players.ONE;
	private boolean playerHasWon = false;
	
	private Board board = new Board();
	
	private boolean gameOver = false;
	
	public void display() {
		System.out.print(this.board.display());
	};
	
	private void alert(String msg) {
		System.out.flush();
		System.out.println("ERROR: " + msg);
	//	this.board.display();
	}
	
	private int askForInt(String question) {
		System.out.print(question);
		int nb = 0;
		
		boolean isInputValid = false;
		while (!isInputValid) {
			String input = scanner.next();
//			String input = console.readLine();
			try {
				nb = Integer.parseInt(input);
				if (nb > 0 & nb <=3)
					isInputValid = true;
				else
					this.alert("Value must be a number between 1 and 3");
			}
			catch (NumberFormatException e) {
				this.alert("Not a number");
			}
		}
		
		return nb;
	}
	
	private boolean pickOneValidCell() {
		boolean hasPlayed = false;
		boolean hasWon = false;
		while (!hasPlayed) {
			int line = askForInt("Line? ");
			int column = askForInt("Column? ");
			
			if (!board.play(line, column, currentPlayer))
				this.alert("This cell is not empty. Please pick an empty one");
			else {
				hasPlayed = true;
				hasWon = board.isWinningCell(line, column);
			}
		}
		
		return hasWon;
	}
	
	private void switchPlayer() {
		switch (this.currentPlayer) {
		case ONE:
			this.currentPlayer = Players.TWO;
			break;
		case TWO:
			this.currentPlayer = Players.ONE;
			break;
		}
	}
	
	public boolean start() {
		for (int turns = 0; turns <=9 && !playerHasWon; turns++) {
			System.out.println(board.display());
			System.out.println("Player " + this.currentPlayer.toString() + ": ");
			
			playerHasWon = pickOneValidCell();

			if (playerHasWon) {
				System.out.println(board.display());
				System.out.println("Player " + currentPlayer.toString() + " has won!!!!");
			}
			else
				switchPlayer();
		}
	
		
		System.out.println("Play again? (Y or N)");
		String playAgain = scanner.next();
		boolean toto = playAgain.equals("Y");
		return toto;
	}
}
