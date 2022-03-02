package game;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class PongFrame extends JFrame {

	private PongPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PongFrame frame = new PongFrame();
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
	public PongFrame() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200, 50);
		this.setResizable(false);
		this.setTitle("Pong");
		contentPane = new PongPanel();
		this.setContentPane(contentPane);
		
		pack();
	}

}
