
public class TicTacToe {
	public static void main(String[] str) {
		boolean keepPlaying = true;
		while (keepPlaying) {
			Game g = new Game();
			keepPlaying = g.start();
		}
	}
}
