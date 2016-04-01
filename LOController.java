
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;

public class LOController implements ActionListener {

	private LOGui gui;
	private LOBoard board;

	public LOController (LOGui gui, LOBoard board) {
		this.gui = gui;
		this.board = board;
	}

	public void reset () {
		board.reset();
		gui.clearBoard();
		randomize();
	}

	public void randomize() {
		if(!board.state()) {
			board.changeState();
			gui.changeState();
		}
		for (int i = 0; i < 10; i++) {;
			for (Integer j : board.move((int) (Math.random()*25))) {
				gui.change(j);
			}
		}
	}

	/**
	 * Called when one of the buttons in the user interface is clicked.
	 */
	public void actionPerformed (ActionEvent e) {

		// Get the button that was clicked
		JButton button = (JButton)e.getSource();

		// It was the start new game button
		if (button.getText().equals("Start New Game")) {
			reset();
		}

		else if (button.getText().equals("Enter Manual Setup") || button.getText().equals("Exit Manual Setup")) {
			board.changeState();
			gui.changeState();
		}

		// It was one of the twenty five board buttons
		else {

			// Get the name of the button that were clicked
			int square = Integer.parseInt(button.getName());

			// Make the move.
			Iterable<Integer> iter = board.move(square);

			// Change block colors
			for (Integer i : iter) {
				gui.change(i);
			}

			if (board.isWon()) {
				JOptionPane.showMessageDialog(null, "You Win!");
			}
		}

	}

}