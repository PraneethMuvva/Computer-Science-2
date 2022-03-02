package game;

import java.awt.EventQueue;

import javax.swing.JFrame;


public class SnakeFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnakeFrame frame = new SnakeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SnakeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 50);
		setTitle("Snake");
		setResizable(false);
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("src/files/snake.png"));
		setContentPane(new SnakePanel());
		
		pack();
	}

}
