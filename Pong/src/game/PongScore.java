package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PongScore {
	int width, height;
	int score1, score2;
	
	public PongScore(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.GRAY);
		g2.setFont(new Font("Consolas", Font.PLAIN, 60));
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(width/2, 0, width/2, height);
		g2.drawString(String.valueOf(score1/10) + String.valueOf(score1%10), width/2 - 85, 50);
		g2.drawString(String.valueOf(score2/10) + String.valueOf(score2%10), width/2 + 20, 50);
	}
}
