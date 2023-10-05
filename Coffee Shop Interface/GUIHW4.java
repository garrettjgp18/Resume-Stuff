package Homework4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class GUIHW4 extends Coffee {
	
	static double total = 0;
	
	public GUIHW4() {

	}

	public void start() {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();

					frame.setSize(1500, 1500);
					frame.setTitle("Coffee system");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						;
					} catch (Exception e) {
						e.printStackTrace();
					}
					frame.add(createMainPanel(), BorderLayout.CENTER);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	} 

	
	
	public Component createMainPanel() {
		
		// MAIN PANEL
		JPanel mainPanel = new JPanel(); 
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// MAIN MENU PANEL (WHAT WORKER SEES)
		final JPanel mainMenu = new JPanel();
		JButton order = new JButton("Order");
		JButton view = new JButton("View orders");
		JButton exit = new JButton("Exit");
		ImageIcon gif = new ImageIcon("catCoffeeGif.jpeg"); //added for some creativity points since I felt like I am missing a good bit of quality
		JLabel image = new JLabel (gif);
		image.setSize(100, 100);
		mainMenu.setLayout(new GridLayout(4, 1));
		mainMenu.add(order);
		mainMenu.add(view);
		mainMenu.add(exit);
		mainMenu.add(image);
		// ------------

		// ORDER PANEL
		final JPanel orderPanel = new JPanel();
		JButton back = new JButton("back");
		orderPanel.setLayout(new GridLayout(3, 1));
		orderPanel.add(back);
		orderPanel.setVisible(false);

		final JPanel selectType = new JPanel();
		selectType.setLayout(new GridLayout(1, 3));

		final JPanel Coffee = new JPanel();
		final JPanel Espresso = new JPanel();
		final JPanel Mocha = new JPanel();

		// ------------------------------------

		// CUSTOMER INFO (ORDER MENU)

		final JPanel customerInfo = new JPanel();
		final JTextField name = new JTextField(12);
		final JTextField email = new JTextField(12);
		final JTextField address = new JTextField(12);
		customerInfo.setLayout(new GridLayout(3, 1));
		name.setText("Enter last name");
		email.setText("Enter email");
		address.setText("Enter address");

		customerInfo.add(name);

		customerInfo.add(email);

		customerInfo.add(address);
		
		// ORDER QUE - used later in the PrintWrtiers
		
		final ArrayList<String> orderQue = new ArrayList<String>();
		
		// COFFEE LAYOUT & BUTTONS

		Coffee.setLayout(new BoxLayout(Coffee, BoxLayout.Y_AXIS));
		Coffee.setBackground(new Color(255, 5, 5));
		JLabel milkTypeHeader = new JLabel("  Select milk:");
		JRadioButton none = new JRadioButton("None");
		final JRadioButton milk = new JRadioButton("2% milk");			//RadioButtons allow only one option to avoid confusion
		final JRadioButton oatMilk = new JRadioButton("Oat milk");
		final JRadioButton wholeMilk = new JRadioButton("Whole milk");

		JLabel sizes = new JLabel("   Select size:");
		final JRadioButton small = new JRadioButton("Small: $3.00");
		final JRadioButton medium = new JRadioButton("Medium: $5.00");
		final JRadioButton large = new JRadioButton("Large: $7.00");
		final ButtonGroup size = new ButtonGroup();
		size.add(small);
		size.add(medium);
		size.add(large);

		final ButtonGroup milks = new ButtonGroup();
		milks.add(milk);
		milks.add(wholeMilk);
		milks.add(oatMilk);
		milks.add(none);

		JButton add2bag = new JButton("Add to order");
		final Coffee newCoffee = new Coffee();					//Stores setters
		final JButton resetCoffee = new JButton("Reset");

		resetCoffee.addActionListener(new ActionListener() {	//Resets all the radio groups to default
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == resetCoffee) {
					size.clearSelection();
					milks.clearSelection();
				}
			}
		});

		none.addActionListener(new ActionListener() {		//I'm sure there is an easier way to do this than making a hundred buttons
			public void actionPerformed(ActionEvent e) {
				newCoffee.setBase("No milk");
			}
		});
		milk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == milk) {
					newCoffee.setBase("2% milk");
				}
			}
		});

		wholeMilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == wholeMilk) {
					newCoffee.setBase("Whole milk");
				}
			}
		});

		oatMilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == oatMilk) {
					newCoffee.setBase("Oat milk");
				}
			}
		});

		small.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == small) {
					newCoffee.setSize("Small");
					newCoffee.setPrice(3.0);	
					total += newCoffee.getPrice();
				}

				
			}
		});
		
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == medium) {
					newCoffee.setSize("Medium");
					newCoffee.setPrice(5.0);
				}				
			}
		});
		


		large.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == large) {
					newCoffee.setSize("Large");
					newCoffee.setPrice(7.0);
					//totalCoffee += newCoffee.getPrice();
				}
			}
		});
		add2bag.addActionListener(new ActionListener() { 	
			public void actionPerformed(ActionEvent e) {
				Coffee orderCoffee = new Coffee();				//fixed scope problems I was having - kinda juggles variables
				orderCoffee.setSize(newCoffee.getSize());
				String size = orderCoffee.getSize();
				orderCoffee.setBase(newCoffee.getBase());
				String base = orderCoffee.getBase();
				orderCoffee.setPrice(newCoffee.getPrice());
				double price = orderCoffee.getPrice();
				orderCoffee.setName(name.getText());
				orderCoffee.setEmail(email.getText());
				orderCoffee.setAddress(address.getText());

				Coffee coffeeOrder = new Coffee(size, base, price); //creates and sets the object per the constructor of the type of coffee
				coffeeOrder.setName(orderCoffee.getName());
				coffeeOrder.setEmail(orderCoffee.getEmail());
				coffeeOrder.setAddress(orderCoffee.getAddress());
				orderQue.add(coffeeOrder.toString()); 				//Formats the order in a readable way and adds to arrayList for later use
				
				

			}
		});
		// ---------------------------------------------------------
		final Espresso newEspresso = new Espresso();

		Espresso.setLayout(new BoxLayout(Espresso, BoxLayout.Y_AXIS));
		Espresso.setBackground(new Color(155, 125, 60));
		JLabel shots = new JLabel("   Number of shots:");
		JRadioButton oneShot = new JRadioButton("One: $1.00");
		JRadioButton twoShot = new JRadioButton("Two: $1.75");
		JRadioButton threeShot = new JRadioButton("Three: $2.50");
		final ButtonGroup numberOfShots = new ButtonGroup();
		numberOfShots.add(oneShot);
		numberOfShots.add(twoShot);
		numberOfShots.add(threeShot);

		JLabel blend = new JLabel("   Type of base:");
		JRadioButton cream = new JRadioButton("Cream");
		JRadioButton water = new JRadioButton("Water");
		JRadioButton milk1 = new JRadioButton("Milk");
		JRadioButton plain = new JRadioButton("Plain");
		final ButtonGroup espressoMix = new ButtonGroup();
		espressoMix.add(cream);
		espressoMix.add(plain);
		espressoMix.add(milk1);
		espressoMix.add(water);
		JButton addEspreso = new JButton("Add to order");

		JButton resetEspresso = new JButton("Reset");
		
		resetEspresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numberOfShots.clearSelection();
				espressoMix.clearSelection();
			}
		});
		
		oneShot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEspresso.setShots(1);
				newEspresso.setPrice(1);
			}
		});
 
		twoShot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEspresso.setShots(2);
				newEspresso.setPrice(1.75);
			}
		});

		threeShot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEspresso.setShots(3);
				newEspresso.setPrice(2.5);
			}
		});

		cream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEspresso.setBase("Cream");

			}
		});

		water.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEspresso.setBase("Water");

			}
		});

		milk1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEspresso.setBase("Milk");

			}
		});

		plain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEspresso.setBase("Plain");

			}
		});

		addEspreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Espresso orderEspresso = new Espresso();
				orderEspresso.setShots(newEspresso.getShots());
				int shots = orderEspresso.getShots();

				orderEspresso.setBase(newEspresso.getBase());
				String base = orderEspresso.getBase();

				orderEspresso.setPrice(newEspresso.getPrice());
				double price = orderEspresso.getPrice();

				orderEspresso.setName(name.getText());
				orderEspresso.setEmail(email.getText());
				orderEspresso.setAddress(address.getText());

				Espresso espressoOrder = new Espresso(shots, base, price);
				espressoOrder.setName(orderEspresso.getName());
				espressoOrder.setEmail(orderEspresso.getEmail());
				espressoOrder.setAddress(orderEspresso.getAddress());
				orderQue.add(espressoOrder.toString());

			}
		});

		// ------------------------------------
		// -----------------------

		final Mocha newMocha = new Mocha();
		Mocha.setLayout(new BoxLayout(Mocha, BoxLayout.Y_AXIS));
		Mocha.setBackground(new Color(250, 250, 0));

		JLabel mochaLabel = new JLabel("   Mocha shots:");
		JRadioButton Oneshot = new JRadioButton("One shot: $2.50");
		JRadioButton Twoshot = new JRadioButton("Two shots: $3.75");
		JRadioButton Threeshot = new JRadioButton("Three shots: $4.50");
		final ButtonGroup mochaShots = new ButtonGroup();
		mochaShots.add(Oneshot);
		mochaShots.add(Twoshot);
		mochaShots.add(Threeshot);

		JLabel mochaSyrup = new JLabel("   Syrup:");
		JRadioButton choc = new JRadioButton("Chocolate syrup");
		JRadioButton whitechoc = new JRadioButton("White chocolate syrup");
		final ButtonGroup syrup = new ButtonGroup();
		syrup.add(choc);
		syrup.add(whitechoc);

		JLabel baseType = new JLabel("   Foam:");
		JRadioButton steamMilk = new JRadioButton("Steamed milk");
		JRadioButton noBase = new JRadioButton("None");
		final ButtonGroup milkStuff = new ButtonGroup();

		JButton addMocha = new JButton("Add to order");
		milkStuff.add(steamMilk);
		milkStuff.add(noBase);
		
		JButton resetMocha = new JButton("Reset");

		resetMocha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mochaShots.clearSelection();
				syrup.clearSelection();
				milkStuff.clearSelection();
			}
		});
		
		Oneshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMocha.setShots(1);
				newMocha.setPrice(2.50);

			}
		});

		Twoshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMocha.setShots(2);
				newMocha.setPrice(3.75);

			}
		});

		Threeshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMocha.setShots(3);
				newMocha.setPrice(4.50);

			}
		});

		choc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMocha.setSyrup("Chocolate");
			}
		});

		whitechoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMocha.setSyrup("White chocolate");
			}
		});

		steamMilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMocha.setBase("Steamed milk");
			}
		});

		noBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMocha.setBase("No foam");
			}
		});

		addMocha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mocha orderMocha = new Mocha();

				orderMocha.setShots(newMocha.getShots());
				int shots = orderMocha.getShots();

				orderMocha.setSyrup(newMocha.getSyrup());
				String syrup = orderMocha.getSyrup();

				orderMocha.setBase(newMocha.getBase());
				String base = orderMocha.getBase();

				orderMocha.setPrice(newMocha.getPrice());
				double price = orderMocha.getPrice();

				orderMocha.setName(name.getText());
				orderMocha.setEmail(email.getText());
				orderMocha.setAddress(address.getText());

				Mocha mochaOrder = new Mocha(shots, syrup, base, price);
				mochaOrder.setName(name.getText());
				mochaOrder.setEmail(email.getText());
				mochaOrder.setAddress(address.getText());
				orderQue.add(mochaOrder.toString());

			}
		});
		
		// -----------------------------------------------------------
		// TYPES OF COFFEE (ORDER MENU)
		JCheckBox coffee = new JCheckBox("Regular coffee"); 
		JCheckBox espresso = new JCheckBox("Espresso");
		JCheckBox mocha = new JCheckBox("Mocha");

		Coffee.add(coffee);			//All buttons, labels, and textAreas are added to their respective JPanels here
		Coffee.add(milkTypeHeader);
		Coffee.add(none);
		Coffee.add(milk1);
		Coffee.add(wholeMilk);
		Coffee.add(oatMilk);
		Coffee.add(sizes);
		Coffee.add(small);
		Coffee.add(medium);
		Coffee.add(large);
		Coffee.add(add2bag);
		Coffee.add(resetCoffee);

		Espresso.add(espresso);
		Espresso.add(shots);
		Espresso.add(oneShot);
		Espresso.add(twoShot);
		Espresso.add(threeShot);
		Espresso.add(blend);
		Espresso.add(plain);
		Espresso.add(milk1);
		Espresso.add(water);
		Espresso.add(cream);
		Espresso.add(addEspreso);
		Espresso.add(resetEspresso);

		Mocha.add(mocha);
		Mocha.add(mochaLabel);
		Mocha.add(Oneshot);
		Mocha.add(Twoshot);
		Mocha.add(Threeshot);
		Mocha.add(mochaSyrup);
		Mocha.add(choc);
		Mocha.add(whitechoc);
		Mocha.add(baseType);
		Mocha.add(steamMilk);
		Mocha.add(noBase);
		Mocha.add(addMocha);
		Mocha.add(resetMocha);

		selectType.setVisible(false);

		// ----------------------------------
		
		// MAIN MENU BUTTONS
		order.addActionListener(new ActionListener() {	//Many buttons utilize the setVisible() command, allows for smoother experience
			public void actionPerformed(ActionEvent e) {	
				mainMenu.setVisible(false);
				orderPanel.setVisible(true);
				selectType.setVisible(true);

			}
		});

		exit.addActionListener(new ActionListener() {		//Completely closes the program
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.setVisible(true);
				orderPanel.setVisible(false);
				selectType.setVisible(false);

			}
		});

		// ------------------------------------

		final JPanel continueOrder = new JPanel();
		continueOrder.setLayout(new BoxLayout(continueOrder, BoxLayout.Y_AXIS));
		JButton goBack = new JButton("Back to order menu");
		continueOrder.add(goBack);
		continueOrder.setVisible(false);

		final JTextArea summary = new JTextArea(50, 50);
		JLabel summaryField = new JLabel("Order Summary");
		summary.setEditable(false);

		// summary.setText();
		continueOrder.add(summaryField);
		continueOrder.add(summary);

		
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectType.setVisible(true);
				orderPanel.setVisible(true);
				continueOrder.setVisible(false);
			}
		});

		JButton finish = new JButton("Check order");

		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectType.setVisible(false);
				orderPanel.setVisible(false);
				continueOrder.setVisible(true);

				StringBuilder stringbuild = new StringBuilder(); //For loop that iterates through the string array orderQue, and uses a string builder to format 
				for (String summaryQue : orderQue) {			 //the strings in a way that would make each order start on a new line. Sets JTextArea summary
					stringbuild.append(summaryQue).append("\n"); //to it's output.
				}
				summary.setText(stringbuild.toString());
			}
		});

		
		

		final JButton completeOrder = new JButton("Order coffee"); 
		continueOrder.add(completeOrder);

		completeOrder.addActionListener(new ActionListener() {	//Creates a printWrtier object and gets current text within "coffeeShop.txt" file, and appends
			public void actionPerformed(ActionEvent e) {		//text from the JTextArea summary field to the file in the same way text exist within it.
				if (e.getSource() == completeOrder) {
					try (PrintWriter pw = new PrintWriter(new FileWriter("coffeeShop.txt", true))) { 
						pw.println('\n' + summary.getText());
						pw.close();
					} catch (Exception d) {
						d.printStackTrace();
					}
				}
			}
		});

		final JPanel viewOrders = new JPanel();
		final JTextArea pastOrders = new JTextArea(100, 80);

		viewOrders.setVisible(false);
		viewOrders.add(pastOrders);

		JButton goBackMenu = new JButton("Go back to main menu");
		viewOrders.add(goBackMenu);

		goBackMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.setVisible(true);
				viewOrders.setVisible(false);
			}
		});
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.setVisible(false);
				viewOrders.setVisible(true);
				try (Scanner fileScanner = new Scanner(Paths.get("coffeeShop.txt"))) {	//Uses a scanner that reads each line of the "coffeeShop.txt" file, and
					StringBuilder osb = new StringBuilder();							//uses a stringBuilder that copies the text of the file into a textArea.
					while (fileScanner.hasNextLine()) {
						osb.append(fileScanner.nextLine());
						osb.append('\n');
					}
					pastOrders.setText(osb.toString());
					fileScanner.close();
				} catch (Exception d) {
					d.printStackTrace();
				}

			}
		});
		
		

		mainPanel.add(mainMenu);
		mainPanel.add(orderPanel);
		mainPanel.add(viewOrders);

		orderPanel.add(customerInfo);
		orderPanel.add(selectType);

		selectType.add(Coffee);
		selectType.add(Box.createHorizontalGlue());		//Creates space to make more readable
		selectType.add(Espresso);
		selectType.add(Box.createHorizontalGlue());
		selectType.add(Mocha);
		selectType.add(Box.createHorizontalGlue());
		selectType.add(finish);
		mainPanel.add(continueOrder);

		
		// -----------------

		return mainPanel;
	}
}
