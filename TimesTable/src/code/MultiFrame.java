package code;

import java.awt.EventQueue;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class MultiFrame extends JFrame {

	private MultiPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiFrame frame = new MultiFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MultiFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocation(100, 100);
		contentPane = new MultiPanel();
		setContentPane(contentPane);
		
		pack();
	}

}
