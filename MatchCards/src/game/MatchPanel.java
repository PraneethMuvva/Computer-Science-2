package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MatchPanel extends JPanel implements MouseListener {
	
	private final int WIDTH = 700;
	private final int HEIGHT = 700;
	private Dimension SIZE = new Dimension(WIDTH, HEIGHT);

	private int indx1, indx2, turn = 0; 
	private int matches = 0;
	private Random rand = new Random();
	private Timer myTimer;
	private ImageIcon grey = new ImageIcon("src/images/grey.png");
	private Color color;
	
	private ImageIcon [] icons;
	private JLabel [] imageLBLs;
	private Border border;
	
	
	/**
	 * Create the panel.
	 */
	
	public MatchPanel() {
		this.setPreferredSize(SIZE);
		this.setLayout(new GridLayout(6, 6, 5, 2));
		
		border = BorderFactory.createLineBorder(Color.ORANGE, 5);
		myTimer = new Timer(1000, new TimeListener());
		createArray();
	}
	
	private void createArray() {
		icons = new ImageIcon[36];
		imageLBLs = new JLabel[36];
		ImageIcon temp = new ImageIcon();
		
		for(int i = 0; i < 18; i++) {
			temp = new ImageIcon("src/images/" + i + ".png");
			icons[i] = temp;
			icons[i + 18] = temp;
			
			imageLBLs[i] = new JLabel(grey);
			imageLBLs[i].addMouseListener(this);
			this.add(imageLBLs[i]);
			
			imageLBLs[i + 18] = new JLabel(grey);
			imageLBLs[i + 18].addMouseListener(this);
			this.add(imageLBLs[i + 18]);
		}
		
		shuffleIcons();
		
	}

	private void shuffleIcons() {
		ImageIcon temp = new ImageIcon();
		for(int i = 0; i < icons.length; i++) {
			int x = rand.nextInt(icons.length);
			temp = icons[i];
			icons[i] = icons[x];
			icons[x] = temp;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {	
		if(myTimer.isRunning()) {
			return;
		}
		
		Object src = e.getSource();
		for(int i = 0; i < imageLBLs.length; i++) {
			if(src == imageLBLs[i]) {
				imageLBLs[i].setIcon(icons[i]);
				imageLBLs[i].setBorder(null);
				imageLBLs[i].removeMouseListener(this);
				if(turn % 2 == 0) {
					indx2 = i;	
				} else {
					indx1 = i;
				}
			}
		}
		
		turn++;
		
		if(turn % 2 == 0) {
			if(!(icons[indx1].equals(icons[indx2]))) {
				imageLBLs[indx1].addMouseListener(this);
				imageLBLs[indx2].addMouseListener(this);
				myTimer.start();
			} else {
				matches++;
				if(matches == 18) {
					gameOver();
				}
			}
		}
	}

	private void gameOver() {
		int answer = JOptionPane.showConfirmDialog(null, "Play Again?", "Game Over", 
				JOptionPane.YES_NO_OPTION);
		if(answer == 0) {
			startOver();
		} else {
			System.exit(0);
		}
	}

	private void startOver() {
		matches = 0;
		turn = 0;
		this.removeAll();
		
		for(int i = 0; i < imageLBLs.length; i++) {
			imageLBLs[i].setIcon(grey);
			imageLBLs[i].addMouseListener(this);
			this.add(imageLBLs[i]);
		}
		
		shuffleIcons();
		validate();
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(myTimer.isRunning()) {
			return;
		}
		
		color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		border = BorderFactory.createLineBorder(color, 5);
		
		Object src = e.getSource(); 
		for(int i = 0; i < imageLBLs.length; i ++) {
			if(src == imageLBLs[i]) {
				imageLBLs[i].setBorder(border);
				break;
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(myTimer.isRunning()) {
			return;
		}
		Object src = e.getSource();
		for(int i = 0; i < imageLBLs.length; i ++) {
			if(src == imageLBLs[i]) {
				imageLBLs[i].setBorder(null);
				break;
			}
		}
	}
	
	private class TimeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			imageLBLs[indx1].setIcon(grey);
			imageLBLs[indx2].setIcon(grey);
			myTimer.stop();
			
		}
	}
}
