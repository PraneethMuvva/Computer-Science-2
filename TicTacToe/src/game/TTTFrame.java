package game;

import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TTTFrame extends JFrame {

	private TTTPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TTTFrame frame = new TTTFrame();
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
	public TTTFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new TTTPanel();
		this.setTitle("Tic-Tac-Toe");
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocation(150, 150);
		
		pack();
		
	}

}
