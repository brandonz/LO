
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LOGui extends JFrame {

	public static void main (String[] args) {
		LOGui lo = new LOGui();
		lo.setVisible(true);
		lo.setResizable(true);
	}

	private BoardPanel boardPanel; // The main part of the GUI
	private JButton state;

	public LOGui () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Lights Out");

		// Represents the state of the game
		LOBoard board = new LOBoard();
		
		// Controls the interaction between the GUI and the board
		LOController controller = new LOController(this, board);	
		
		// Top-level panel within the window
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		// Lay out the main panel
		boardPanel = new BoardPanel(controller);
		mainPanel.add(boardPanel, "Center");
		JButton reset = new JButton("Start New Game");
		state = new JButton("Enter Manual Setup");
		state.addActionListener(controller);
		reset.addActionListener(controller);
		mainPanel.add(state, "North");
		mainPanel.add(reset, "South");
		
		// Compose the top-level window and get going.
		setContentPane(mainPanel);
		setSize(800,800);
		controller.reset();
	}
	
	
	/**
	 * Clears the board
	 */
	public void clearBoard () {
		boardPanel.clear();
	}

	public void change(int square) {
		boardPanel.changeLights(square);
	}

	public void changeState() {
		if (state.getText().equals("Enter Manual Setup")) state.setText("Exit Manual Setup");
		else state.setText("Enter Manual Setup");
	}

}


/**
 * Represents the playing area of a LOGui.
 */
class BoardPanel extends JPanel {	

	/**
	 * Creates a BoardPanel given the controller and the board state.
	 */
	public BoardPanel (ActionListener listener) {
		setLayout(new GridLayout(5,5));
		for (int i = 0; i < 25; i++) {
			JButton b = new JButton("");
			b.setName("" + i);
			b.setBackground(Color.BLACK);
			add(b);
			b.addActionListener(listener);
		}
	}
	
	
	public void clear () {
		for (int i = 0; i < 25; i++) {
			JButton b = (JButton) getComponent(i);
			b.setBackground(Color.BLACK);
		}
	}

	public void changeLights(int i) {
		JButton b = (JButton) getComponent(i);
		if (b.getBackground() == Color.BLACK) b.setBackground(Color.WHITE);
		else b.setBackground(Color.BLACK);
	}

}


