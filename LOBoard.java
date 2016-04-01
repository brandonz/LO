import java.util.Stack;

public class LOBoard {

	private final int n;			//total size of 1d array
	private final int size = 5;
	private boolean[] board;			//false for lights off, true for on
	private boolean state = true; 	//true for standard, false for one changes

	//creates a size-by-size board
	public LOBoard () {
		n =  5*5;
		board = new boolean[n];
	} 

	//true if in grid, false otherwise
	private boolean check (int i) {
		return !(i < 0 || i >= n);
	}

	//switches values at specified square;
	private void change (int square) {
		board[square] = !board[square]; 
	}

	//returns the squares that need to change colors
	public Iterable<Integer> move (int square) {
		Stack<Integer> iter = new Stack<Integer>();

		if (state) {
			
			if (check(square)) {
				change(square);
				iter.push(square);
			} 
			if (check(square-1) && ((square-1) % 5) !=4) {
				change(square-1);
				iter.push(square-1);
			}
			if (check(square+1) && ((square+1) % 5) !=0) {
				change(square+1);
				iter.push(square+1);
			}
			if (check(square-size)) {
				change(square-size);
				iter.push(square-size);
			}
			if (check(square+size)) {
				change(square+size);
				iter.push(square+size);
			}
			return iter;
		}

		else {
			change(square);
			iter.push(square);
			return iter;
		}
	}

	//checks if board is won (all lights are off)
	public boolean isWon () {
		for (int i = 0; i < n; i++) {
			if (board[i]) return false;
		}
		return true;
	}

	//resets the board
	public void reset () {
		for (int i = 0; i < n; i++) {
			board[i] = false;
		}
	}

	public boolean state() {
		return state;
	}

	public void changeState() {
		state = !state;
	}

}