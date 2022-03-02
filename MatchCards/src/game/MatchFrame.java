package game;

import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MatchFrame extends JFrame {

	private MatchPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchFrame frame = new MatchFrame();
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
	public MatchFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Match Cards");
		setLocation(100, 0);
		setResizable(false);
		setLayout(null);
		contentPane = new MatchPanel();
		setContentPane(contentPane);
		pack();
	}

}
