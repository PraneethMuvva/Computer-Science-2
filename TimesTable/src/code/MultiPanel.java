package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MultiPanel extends JPanel implements ActionListener {
	//set up size of panel
	private int WIDTH = 500;
	private int HEIGHT = 500;
	private Dimension SIZE = new Dimension(WIDTH, HEIGHT);
	
	//set up variables for table
	private int tableSize = 11;
	private int input;

	//set up swing components
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
	private JLabel[] labels;
	private JLabel getSizeLBL;
	private JTextField textField;
	private JPanel top = new JPanel();
	private JPanel table = new JPanel();
	
	//create panel
	public MultiPanel() {
		setPreferredSize(SIZE);
		setDisplay();
		setupGrid();
	}

	
	//set layout of main panel, add a prompt and TextField box 
	private void setDisplay() {
		setLayout(new BorderLayout());
		
		getSizeLBL = new JLabel("What size for the table(1-20): ");
		top.add(getSizeLBL);
		
		textField = new JTextField("");
		textField.setPreferredSize(new Dimension(20, 20));
		textField.addActionListener(this);
		top.add(textField);
		
		top.setBackground(Color.ORANGE);
		add(top, BorderLayout.NORTH);
		
		table.setBackground(Color.YELLOW);
		add(table, BorderLayout.CENTER);
	}
	
	private void setupGrid() {
		//remove all labels from JPanel table
		table.removeAll();
		
		//make labels a new array of length tableSize squared
		labels = new JLabel[tableSize * tableSize];
		
		//make table a gridLayout with a tableSize amount of columns and rows
		table.setLayout(new GridLayout(tableSize, tableSize));
		//set the first JLabel to a blank value
		labels[0] = new JLabel("");
		labels[0].setBorder(border);
		table.add(labels[0]);
		
		//set every other JLabel to their index
		for(int i = 1; i < labels.length; i++) {
			labels[i] = new JLabel(Integer.toString(i));
			labels[i].setBorder(border);
			table.add(labels[i]);
		}
		
		//go through every JLabel in labels past the first row
		for(int i = 1; i < tableSize; i++) {
			for(int j = 0; j < tableSize; j++) {
				//set the text to the row number if in the first column
				if(j == 0) {
					labels[i * tableSize + j].setText(Integer.toString(i));
				} 
				//set text to the product of the row and column otherwise
				else {
					labels[i * tableSize + j].setText(Integer.toString(i * j));
				}
			}
		}
		
		validate();
		repaint();
	
	}
	
	//take string input from textField, change tableSize if valid input and call setupGrid
	@Override
	public void actionPerformed(ActionEvent e) {
		input = Integer.valueOf(textField.getText());
		textField.setText("");
		
		if(input >= 1 && input <= 20) {
			tableSize = input + 1;
			setupGrid();
		}
	}
}
