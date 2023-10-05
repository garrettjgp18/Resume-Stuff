package Lab10;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;

public class GUI {

	// add variables if needed

	public GUI() {
		// you can leave this constructor empty
	}

	/**
	 * start() is a method starts the GUI by creating the frame and panels needed to
	 * show this Java App. Feel free to configure the settings to match your
	 * intended output!
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();

					// configure your frame's size, title, and close operation
					frame.setSize(800, 500);
					frame.setTitle("Calculator V1");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (Exception e) {
						e.printStackTrace();
					}

					// add the main JPanel into your JFrame
					// call createMainPanel and add it to your JFrame
					frame.add(createMainPanel(), BorderLayout.CENTER);

					// uncomment if you want to pack all components closely together
					// frame.pack();

					// for some reason, a JFrame defaults to false for visibility
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * createMainPanel() creates all of the necessary components needed to be added
	 * into a main JPanel object. You will need to add more panels and components to
	 * make complete your Java App!
	 * 
	 * @return a main JPanel object to be added into the JFrame
	 */
	public Component createMainPanel() {

		// this is our main panel to house other panels or components
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel topPanel = new JPanel();
		final JTextField output = new JTextField(12);

		output.setMaximumSize(new Dimension(750, 200));
		output.setFont(new Font(Font.SERIF, Font.PLAIN, 84));
		output.setHorizontalAlignment(JTextField.RIGHT);
		output.setEditable(false);

		topPanel.setMaximumSize(new Dimension(800, 200));
		topPanel.add(output);

		JPanel bottomPanel = new JPanel();
		JButton clear = new JButton("CLEAR");
		JButton delete = new JButton("DEL");
		ArrayList<JButton> buttons = new ArrayList<JButton>(); //Ignore this, tried something but didn't work like I thought
		JButton equals = new JButton("=");
		buttons.add(equals);
		JButton add = new JButton("+");
		buttons.add(add);
		JButton subtract = new JButton("-");
		buttons.add(subtract);
		JButton multiply = new JButton("*");
		buttons.add(multiply);
		JButton divide = new JButton("/");
		buttons.add(divide);
		JButton one = new JButton("1");
		buttons.add(one);
		JButton two = new JButton("2");
		buttons.add(two);
		JButton three = new JButton("3");
		buttons.add(three);
		JButton four = new JButton("4");
		buttons.add(four);
		JButton five = new JButton("5");
		buttons.add(five);
		JButton six = new JButton("6");
		buttons.add(six);
		JButton seven = new JButton("7");
		buttons.add(seven);
		JButton eight = new JButton("8");
		buttons.add(eight);
		JButton nine = new JButton("9");
		buttons.add(nine);
		JButton zero = new JButton("0");
		buttons.add(zero);
		JButton decimal = new JButton(".");
		buttons.add(decimal);

		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText("");
			}
		});

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String del = output.getText();
				output.setText(del.substring(0, del.length() - 1));
			}
		});

		decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + ".");
			}
		});

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "+");
			}
		});

		subtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "-");
			}
		});

		multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "*");
			}
		});

		divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "/");
			}
		});

		
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "1");
				System.out.println(output.getText());
			}
		});

		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "2");
			}
		});

		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "3");
			}
		});

		four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "4");
			}
		});

		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "5");
			}
		});

		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "6");
			}
		});

		seven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "7");
			}
		});

		eight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "8");
			}
		});

		nine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "9");
			}
		});

		zero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(output.getText() + "0");
			}
		});

		equals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) throws ArithmeticException {
				String[] calculate = output.getText().split("[\\+\\-\\*\\/\\√]");
				String numberOne = calculate[0];
				String numberTwo = calculate[1];
				double num1 = Double.parseDouble(numberOne);
				double num2 = Double.parseDouble(numberTwo);

				if (output.getText().contains("+")) {
					output.setText(Double.toString(num1 + num2));
				} else if (output.getText().contains("-")) {
					output.setText(Double.toString(num1 - num2));
				} else if (output.getText().contains("*")) {
					output.setText(Double.toString(num1 * num2));
				} else if (output.getText().contains("/")) {
					if (num2 == 0) {
						try {
							Desktop.getDesktop().browse(new URL("https://www.yout-ube.com/watch?v=dQw4w9WgXcQ").toURI());
						} catch (Exception z) {
  
						}
						output.setText("Can't divide by zero");
						throw new ArithmeticException("Cannot divide by zero");
					}
					output.setText(Double.toString(num1 / num2));
				} 

			}

		});

		bottomPanel.setLayout(new GridLayout(0, 3));
		bottomPanel.add(clear);
		bottomPanel.add(delete);
		bottomPanel.add(one);
		bottomPanel.add(two);
		bottomPanel.add(three);
		bottomPanel.add(four);
		bottomPanel.add(five);
		bottomPanel.add(six);
		bottomPanel.add(seven);
		bottomPanel.add(eight);
		bottomPanel.add(nine);
		bottomPanel.add(zero);
		bottomPanel.add(decimal);
		bottomPanel.add(add);
		bottomPanel.add(subtract);
		bottomPanel.add(divide);
		bottomPanel.add(multiply);
		bottomPanel.add(equals);

		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);

		return mainPanel;
	}
}
