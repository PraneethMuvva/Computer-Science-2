package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class PongBall extends Rectangle {
	Random rand = new Random();
	int xVelocity;
	int yVelocity;
	int speed = 8;
	
	public PongBall(int x, int y, int diameter) {
		super(x, y, diameter, diameter);
		int x1 = rand.nextInt(2);
		if(x1 == 0) {
			setXDirection(-speed);
		} else {
			setXDirection(speed);
		}
		
		int y1 = rand.nextInt(2);
		if(y1 == 0) {
			setYDirection(-speed);
		} else {
			setYDirection(speed);
		}
	}
	

	public void setYDirection(int i) {
		yVelocity = i;
	}
	
	public void setXDirection(int i) {
		xVelocity = i;
	}
	
	public void move() {
		y += yVelocity;
		x += xVelocity;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x, y, width, height);
	}
}
