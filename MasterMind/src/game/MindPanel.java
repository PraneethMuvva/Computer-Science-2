package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MindPanel extends JPanel implements ActionListener {
	
	private int WIDTH = 600;
	private int HEIGHT = 600;
	private Dimension SIZE = new Dimension(WIDTH, HEIGHT);
	private String [] options = {"SELECT", "RED", "GREEN", "BLUE", "YELLOW"};
	private String [] inputs = {"", "", "", ""};
	private String [] answer = new String[4];
	private String [] tempAnswer;
	private JPanel top = new JPanel();
	private JPanel center = new JPanel();
	private JPanel left = new JPanel();
	private int guesses = 0;
	private int idx = 0;
	private Random rand = new Random();
	private Color bg = new Color(255, 232, 131);

	
	private JComboBox[] dropdowns = new JComboBox[4];
	private JButton enter;
	private JLabel[][] labels;
	private JLabel[][] feedback;
	
	private ImageIcon black = new ImageIcon("src/files/black.png");
	private ImageIcon white = new ImageIcon("src/files/white.png");
	private ImageIcon red = new ImageIcon("src/files/red.png");
	private ImageIcon blue = new ImageIcon("src/files/blue.png");
	private ImageIcon yellow = new ImageIcon("src/files/yellow.png");
	private ImageIcon green = new ImageIcon("src/files/green.png");
	
	/**
	 * Create the panel.
	 */
	public MindPanel() {
		setPreferredSize(SIZE);
		giveInstructions();
		getAnswer();
		setDisplay();
	}
	
	
	private void getAnswer() {
		for(int i = 0; i < 4; i++) {
			answer[i] = options[rand.nextInt(4) + 1];
		}
	}
	
	private void giveInstructions() {
		JOptionPane.showMessageDialog(null,
				"The object of Mastermind is to guess a secret code consisting of a series of 4 colors. Each guess " + "\n"
				+ "results in feedback narrowing down the possibilites of the code. A black circle represents a color " + "\n"
				+ "in the right position and a white circle represents a color in the wrong position. Guessing the " + "\n"
				+ "complete code wins the game. You have 8 guesses. Good Luck!",
				"Instructions", 1);
	}
	
	private void setDisplay() {
		top.setBackground(bg);
		center.setBackground(bg);
		left.setBackground(bg);
		
		this.setLayout(new BorderLayout());
		top.setLayout(new FlowLayout(FlowLayout.RIGHT, 25, 10));
		center.setLayout(new GridLayout(8, 5));
		left.setLayout(new GridLayout(8, 4));
		
		left.setPreferredSize(new Dimension(150, HEIGHT));

		enter = new JButton("Enter");
		enter.setMargin(new Insets(0, 15, 0, 15));
		enter.addActionListener(this);
		top.add(enter);
		
		for(int i = 0; i < 4; i++) {
			dropdowns[i] = new JComboBox(options);
			top.add(dropdowns[i]);
		}
		
		this.add(top, BorderLayout.NORTH);
		
		labels = new JLabel[8][5];
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 5; j++) {
				if(j == 0) {
					labels[i][j] = new JLabel("" + (8 - i));
					labels[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
				} else {
					labels[i][j] = new JLabel();
				}
				center.add(labels[i][j]);
			}
		}
		this.add(center, BorderLayout.CENTER);
	
		feedback = new JLabel[8][4];
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 4; j++) {
				feedback[i][j] = new JLabel();
				left.add(feedback[i][j]);
			}
		}
		this.add(left, BorderLayout.WEST);
	}
	
	private void checkWinner() {
		for(int i = 0; i < 4; i++) {
			if(!(feedback[7-guesses][i].getIcon() == black)) {
				return;
			}
		}
		int answer = JOptionPane.showConfirmDialog(null, "Play Again?", "Game over, you won :)", 
				JOptionPane.YES_NO_OPTION);
		if(answer == 0) {
			restart();
		} else {
			System.exit(0);
		}
	}
	
	private void gameOver() {	
		int answer = JOptionPane.showConfirmDialog(null, "Play Again?", "Game over, you lost :(", 
				JOptionPane.YES_NO_OPTION);
		if(answer == 0) {
			restart();
		} else {
			System.exit(0);
		}
	}

	private void restart() {
		guesses = -1;
		getAnswer();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 4; j++) {
				labels[i][j+1].setIcon(null);
				feedback[i][j].setIcon(null);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 4; i++) {
			if(dropdowns[i].getSelectedIndex() == 0) {
				return;
			}
		}
		
		tempAnswer = Arrays.copyOf(answer, answer.length);
		idx = 0;
		
		for(int i = 0; i < 4; i++) {			
			inputs[i] = options[dropdowns[i].getSelectedIndex()];
			dropdowns[i].setSelectedIndex(0);
			
			switch(inputs[i]) {
				case "RED":
					labels[7-guesses][i+1].setIcon(red);
					break;
				case "GREEN":
					labels[7-guesses][i+1].setIcon(green);
					break;
				case "BLUE":
					labels[7-guesses][i+1].setIcon(blue);
					break;
				case "YELLOW":
					labels[7-guesses][i+1].setIcon(yellow);
					break;
			}
			
			if(inputs[i] == tempAnswer[i]) {
				feedback[7-guesses][idx].setIcon(black);

				tempAnswer[i] = "null1";
				inputs[i] = "null2";
				idx++;
			} 
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(tempAnswer[i]==inputs[j]) {
					feedback[7-guesses][idx].setText("");
					feedback[7-guesses][idx].setIcon(white);
					tempAnswer[i] = "null1";
					inputs[i] = "null2";
					idx++;
				}
			}
		}

		checkWinner();
		if (guesses >= 7) {
			gameOver();
		}
		guesses++;
		
		
	}

}