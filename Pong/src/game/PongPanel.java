package game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class PongPanel extends JPanel implements Runnable{
	private final int WIDTH = 1000, HEIGHT = (int)(WIDTH * (5.0/9.0));
	private final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
	private final int paddleIndent = 3;
	private static final int BALLDIAMETER = 20;
	private static final int PWIDTH = 16;
	private static final int PHEIGHT = 100;
	private int fps = 60;
	private int bounceWait;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	PongPaddle player1;
	PongPaddle player2;
	PongBall ball;
	PongScore score;
	
	
	public PongPanel() {
		this.setPreferredSize(SIZE);
		this.setLayout(null);
		this.setFocusable(true);
		newPaddles();
		newBall();
		score = new PongScore(WIDTH, HEIGHT);
		addKeyListener(new AL());
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	private void newPaddles() {
		player1 = new PongPaddle(paddleIndent, HEIGHT/2 - PHEIGHT/2, PWIDTH, PHEIGHT, 1);
		player2 = new PongPaddle(WIDTH - (paddleIndent + PWIDTH), HEIGHT/2 - PHEIGHT/2, PWIDTH, PHEIGHT, 2);
	}
	
	private void newBall() {
		ball = new PongBall(WIDTH/2 - BALLDIAMETER/2, HEIGHT/2 - BALLDIAMETER/2, BALLDIAMETER);
	}
	
	public void paint(Graphics g) {
		 image = createImage(getWidth(), getHeight());
		 graphics = image.getGraphics();
		 draw(graphics);
		 g.drawImage(image, 0, 0, this);
	}
	
	public void draw(Graphics g) {
		player1.draw(g);
		player2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	private void move() {
		player1.move();
		player2.move();
		ball.move();
	}
	
	private void checkCollision() {
		if(player1.y <= 0) {
			player1.y = 0;
		} else if(player1.y >= HEIGHT - PHEIGHT) {
			player1.y = HEIGHT - PHEIGHT;
		}
		
		if(player2.y <= 0) {
			player2.y = 0;
		} else if(player2.y >= HEIGHT - PHEIGHT) {
			player2.y = HEIGHT - PHEIGHT;
		}
		
		if(ball.y <= 0 || ball.y > HEIGHT - BALLDIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		} 
		
		if((ball.intersects(player1) || ball.intersects(player2)) && bounceWait > fps) {
			bounceWait = 0;
			ball.setXDirection(-ball.xVelocity);
		} 
		
		if(ball.x <= 0) {
			score.score2++;
			newBall();
		} else if(ball.x >= WIDTH) {
			score.score1++;
			newBall();
		}
		bounceWait++;
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();

		double ns = 1000000000/fps;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}

	private class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			player1.keyPressed(e);
			player2.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			player1.keyReleased(e);
			player2.keyReleased(e);
		}
	}

}
