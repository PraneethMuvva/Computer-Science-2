package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TTTPanel extends JPanel implements MouseListener {
	
	private final int GAME_WIDTH = 500;
	private final int GAME_HEIGHT = 610;
	private final Dimension SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	private int turn = 1;
	
	private ImageIcon board = new ImageIcon("src/images/board.png");
	private ImageIcon X = new ImageIcon("src/images/x.png");
	private ImageIcon O = new ImageIcon("src/images/o.png");
	private ImageIcon x = new ImageIcon("src/images/small_x.png");
	private ImageIcon o = new ImageIcon("src/images/small_o.png");
	
	private JLabel northLBL = new JLabel("Tic Tac Toe");
	private JLabel southLBL = new JLabel("Whose turn is it:");
	private JLabel boardLBL = new JLabel("");
	private JLabel [] labels;
	
	/**
	 * Create the panel.
	 */
	public TTTPanel() {
		setPreferredSize(SIZE);
		setLayout(null);
		setLabels();
	}
	
	private void setLabels() {	
		northLBL.setBackground(new Color(255, 75, 0));
		northLBL.setHorizontalAlignment(SwingConstants.CENTER);
		northLBL.setFont(new Font("Baskerville Old Face", Font.BOLD, 42));
		northLBL.setBounds(0, 0, 500, 50);
		add(northLBL);
		
		southLBL.setBackground(new Color(255, 75, 0));
		southLBL.setHorizontalAlignment(SwingConstants.CENTER);
		southLBL.setHorizontalTextPosition(JLabel.LEFT);
		southLBL.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		southLBL.setBounds(0, 560, 500, 50);
		southLBL.setIcon(x);
		add(southLBL);
		
		boardLBL.setBounds(0, 50, 500, 510);
		boardLBL.setIcon(board);
		add(boardLBL);
		
		labels = new JLabel[9];
		for(int i = 0; i < 9; i++) {
			labels[i] = new JLabel();
			labels[i].setBounds(28 + (i % 3) * 166, 80 + (i / 3) * 170, 110, 110);
			labels[i].addMouseListener(this);
			add(labels[i]);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object src = e.getSource();
		int idx = -1;
		for(int i = 0; i < 9; i++) {
			if(src == labels[i]) {
				idx = i;
			}
		}
		
		if(idx >= 0) {
			if(turn % 2 == 0) {
				labels[idx].setIcon(O);
				southLBL.setIcon(x);
			} else {
				labels[idx].setIcon(X);
				southLBL.setIcon(o);
			}
			
			turn++;
			labels[idx].removeMouseListener(this);
			
			if(checkWinner()||checkTie()) {
				int answer = JOptionPane.showConfirmDialog(null, "Play Again?", "Game Over", 
						JOptionPane.YES_NO_OPTION);
				if(answer == 0) {
					startOver();
				} else {
					System.exit(0);
				}
			}
		} 
	}
	
	private void startOver() {
		for(int i = 0; i < 9; i++) {
			labels[i].setIcon(null);
			labels[i].addMouseListener(this);
			turn = 1;
		}
	}
	
	private boolean checkWinner() {
		if(labels[0].getIcon() != null &&
				labels[0].getIcon().equals(labels[1].getIcon()) &&
				labels[0].getIcon().equals(labels[2].getIcon())) {
			return true;
		}
		
		if(labels[3].getIcon() != null &&
				labels[3].getIcon().equals(labels[4].getIcon()) &&
				labels[3].getIcon().equals(labels[5].getIcon())) {
			return true;
		}	
		
		if(labels[6].getIcon() != null &&
				labels[6].getIcon().equals(labels[7].getIcon()) &&
				labels[6].getIcon().equals(labels[8].getIcon())) {
			return true;
		}

		if(labels[0].getIcon() != null &&
				labels[0].getIcon().equals(labels[3].getIcon()) &&
				labels[0].getIcon().equals(labels[6].getIcon())) {
			return true;
		}
		
		if(labels[1].getIcon() != null &&
				labels[1].getIcon().equals(labels[4].getIcon()) &&
				labels[1].getIcon().equals(labels[7].getIcon())) {
			return true;
		}
		
		if(labels[2].getIcon() != null &&
				labels[2].getIcon().equals(labels[5].getIcon()) &&
				labels[2].getIcon().equals(labels[8].getIcon())) {
			return true;
		}

		if(labels[0].getIcon() != null &&
				labels[0].getIcon().equals(labels[4].getIcon()) &&
				labels[0].getIcon().equals(labels[8].getIcon())) {
			return true;
		}
		
		if(labels[2].getIcon() != null &&
				labels[2].getIcon().equals(labels[4].getIcon()) &&
				labels[2].getIcon().equals(labels[6].getIcon())) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkTie() {
		for(int i = 0; i < 9; i++) {
			if(labels[i].getIcon() == null) {
				return false;
			}
		}
		return true;
	}
	

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
