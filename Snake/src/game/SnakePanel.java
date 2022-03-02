package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements ActionListener {
	private final int WIDTH = 600;
	private final int HEIGHT = 600;
	private Dimension size = new Dimension(WIDTH, HEIGHT);
	
	private int UNIT_SIZE = 25;
	private int GAME_UNITS = (WIDTH * HEIGHT)/UNIT_SIZE;
	private int delay = 175;
	private int[]x = new int[GAME_UNITS];
	private int[]y = new int[GAME_UNITS];
	private int bodyParts = 3;
	private int fruitEaten = 0;
	private int fruitX, fruitY;
	private char direction = 'R';
	private boolean running = false;
	private Timer timer;
	private Random rand = new Random();
	private String highScore;
	private int highScoreNum;
	private String ini;
	private String msg;
	
	private String fileName = "src/files/highScore.txt";
	FileWrite writing;
	FileRead reading;
	
	public SnakePanel() {
		this.setPreferredSize(size);
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		writing = new FileWrite(fileName);
		reading = new FileRead(fileName);
		getInitials();
		startGame();
	}

	private void getInitials() {
		ini = JOptionPane.showInputDialog(null, "Enter Player Initials: ");
		ini = ini.substring(0, 3);
	}

	private void startGame() {
		msg = "";
		newFruit();
		getHighScore();
		running = true;
		timer = new Timer(delay, this);
		timer.start();
	}
	
	private void getHighScore() {
		reading.readFile();
		highScore = reading.in.get(0);
		highScoreNum = Integer.parseInt(reading.in.get(1));
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Comic Sans", Font.BOLD, 20));
		g.drawString("High Score: " + highScore + " " + highScoreNum, 0, g.getFont().getSize());
		g.drawString("Score: " + fruitEaten, 0, g.getFont().getSize() * 2);
		
		if(running) {
			g.setColor(Color.RED);
			g.fillOval(fruitX, fruitY, UNIT_SIZE, UNIT_SIZE);
			
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					g.setColor(Color.YELLOW);
				} else {
					g.setColor(Color.GREEN);
				}
				
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
		} else {
			gameOver(g);
		}
		
	}
	
	private void move() {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
			case 'R': 
				x[0]+=UNIT_SIZE;
				break;
			case 'L': 
				x[0]-=UNIT_SIZE;
				break;
			case 'U': 
				y[0]-=UNIT_SIZE;
				break;
			case 'D': 
				y[0]+=UNIT_SIZE;
				break;
		}
	}
	
	private void newFruit() {
		fruitX = rand.nextInt(WIDTH/UNIT_SIZE) * UNIT_SIZE;
		fruitY = rand.nextInt(WIDTH/UNIT_SIZE) * UNIT_SIZE;
	}
	
	private void checkCollisions() {
		for(int i = bodyParts; i > 0; i--) {
			if(x[0]==x[i]&&y[0]==y[i]) {
				running = false;
			}
		}
		
		if(x[0] > WIDTH || x[0] < 0) {
			running = false;
		} 
		
		if(y[0] > HEIGHT || y[0] < 0) {
			running = false;
		}
		
		if(x[0] == fruitX && y[0] == fruitY) {
			bodyParts++;
			fruitEaten++;
			newFruit();
		}
		
		if(!running) {
			timer.stop();
		}
		
	}

	private void gameOver(Graphics g) {
		if(fruitEaten > highScoreNum) {
			highScoreNum = fruitEaten;
			msg = "You beat the high score! ";
			writing.setData(ini + "\n");
			writing.setData(""+highScoreNum);
			writing.writeOver();
		}
		
		int restart = JOptionPane.showConfirmDialog(null, msg + "Would you like to play again?: ", "Game Over", 
				JOptionPane.YES_NO_OPTION);
		if(restart == 0) {
			gameRestart();
		} else {
			System.exit(0);
		}
	}
	
	private void gameRestart() {
		for(int i = 0; i < bodyParts; i++) {
			x[i]=0;
			y[i]=0;
		}
		bodyParts = 3;
		fruitEaten = 0;
		direction = 'R';
		startGame();
	}
	
	

	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_UP:
					if(direction != 'D') {
						direction = 'U';
					}
					break;
				case KeyEvent.VK_DOWN:
					if(direction != 'U') {
						direction = 'D';
					}
					break;
				case KeyEvent.VK_LEFT:
					if(direction != 'R') {
						direction = 'L';
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(direction != 'L') {
						direction = 'R';
					}
					break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkCollisions();
		}
		repaint();
	}
}
