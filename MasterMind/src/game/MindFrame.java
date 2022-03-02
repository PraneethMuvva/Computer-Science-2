package game;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MindFrame extends JFrame {

	private MindPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MindFrame frame = new MindFrame();
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
	public MindFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(50, 50);
		setResizable(false);
		contentPane = new MindPanel();
		setTitle("MasterMind");
		setContentPane(contentPane);
		
		
		pack();
	}

}
