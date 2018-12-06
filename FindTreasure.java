package findtreasure;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FindTreasure extends JFrame {

	// field

	int rowCount = 0;
	int colCount = 0;
	JLabel[][] labelArray = new JLabel[rowCount][colCount];
	JLabel counterLabel = new JLabel();
	int count;
	java.net.URL catURL = getClass().getResource("/resources/dabbingcat.gif");
	ImageIcon cat = new ImageIcon(catURL);
	java.net.URL dogURL = getClass().getResource("/resources/dabbing dog.gif");
	ImageIcon dog = new ImageIcon(dogURL);
	java.net.URL horseURL = getClass().getResource("/resources/gable.jpg");
	ImageIcon horse = new ImageIcon(horseURL);
	ImageIcon winTile = cat;
	ImageIcon loseTile = horse;
	ImageIcon defTile = dog;
	JButton newButton = new JButton();
	int[][] catLocation;
	Random myRandom = new Random();
	int winX;
	int winY;
	int loseX;
	int loseY;
	String failed = "no";
	String clickedTile;
	JMenuBar mainMenuBar = new JMenuBar();
	JMenu winMenu = new JMenu("Winning Tile");
	JMenu loseMenu = new JMenu("Losing Tile");
	JMenu defaultMenu = new JMenu("Default Tile");
	JMenu colorMenu = new JMenu("Background Color");
	JMenuItem winCat = new JMenuItem("Cat");
	JMenuItem winDog = new JMenuItem("Dog");
	JMenuItem winHorse = new JMenuItem("Horse");
	JMenuItem loseCat = new JMenuItem("Cat");
	JMenuItem loseDog = new JMenuItem("Dog");
	JMenuItem loseHorse = new JMenuItem("Horse");
	JMenuItem defCat = new JMenuItem("Cat");
	JMenuItem defDog = new JMenuItem("Dog");
	JMenuItem defHorse = new JMenuItem("Horse");
	JMenuItem whiteBG = new JMenuItem("White");
	JMenuItem grayBG = new JMenuItem("Gray");
	JMenuItem yellowBG = new JMenuItem("Yellow");
	
	JLabel currentWin = new JLabel();
	JLabel currentLose = new JLabel();
	JLabel currentDef = new JLabel();
	
	String winString = "Cat";
	String loseString = "Horse";
	String defString = "Dog";
	

	// constructor

	public FindTreasure() {

		setVisible(true);
		// setSize(800, 1000); // temp for dev, might replace with pack()
		setTitle("Find the Cat by Coleby K");
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitForm(evt);
			}
		});
		
		setJMenuBar(mainMenuBar);
		
		mainMenuBar.setBackground(new Color(204,245,255));


		mainMenuBar.add(winMenu);
		mainMenuBar.add(loseMenu);
		mainMenuBar.add(defaultMenu);
		mainMenuBar.add(colorMenu);
		winMenu.setMnemonic('W');
		loseMenu.setMnemonic('L');
		defaultMenu.setMnemonic('D');
		colorMenu.setMnemonic('B');
		winMenu.add(winCat);
		winMenu.addSeparator();
		winMenu.add(winDog);
		winMenu.addSeparator();
		winMenu.add(winHorse);
		loseMenu.add(loseCat);
		loseMenu.addSeparator();
		loseMenu.add(loseDog);
		loseMenu.addSeparator();
		loseMenu.add(loseHorse);
		defaultMenu.add(defCat);
		defaultMenu.addSeparator();
		defaultMenu.add(defDog);
		defaultMenu.addSeparator();
		defaultMenu.add(defHorse);
		colorMenu.add(whiteBG);
		colorMenu.addSeparator();
		colorMenu.add(grayBG);
		colorMenu.addSeparator();
		colorMenu.add(yellowBG);
		
		
		winCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				winCatActionPerformed(e);
			}
		});
		
		winDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				winDogActionPerformed(e);
			}
		});
		
		winHorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				winHorseActionPerformed(e);
			}
		});
		
		loseCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loseCatActionPerformed(e);
			}


		});
		
		loseDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loseDogActionPerformed(e);
			}
		});
		
		loseHorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loseHorseActionPerformed(e);
			}
		});
		
		defCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defCatActionPerformed(e);
			}
		});
		
		defDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defDogActionPerformed(e);
			}
		});
		
		defHorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defHorseActionPerformed(e);
			}
		});
		
		whiteBG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whiteBGActionPerformed(e);
			}
		});
		
		grayBG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grayBGActionPerformed(e);
			}
		});
		
		yellowBG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yellowBGActionPerformed(e);
			}
		});
		
		while (rowCount > 5 || rowCount < 1 || colCount > 5 || colCount < 2) {
			try {
				rowCount = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of rows (1-5): "));
				colCount = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of columns (2-5): "));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter valid numbers");
			}
		}
		labelArray = new JLabel[rowCount][colCount];
		

		// required: set a layout manager, or use null
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridConstraints;
		for (int i = 0; i < rowCount; i++) {
			for (int f = 0; f < colCount; f++) {
				labelArray[i][f] = new JLabel();
			}
		}

		for (int i = 0; i < rowCount; i++) {
			for (int f = 0; f < colCount; f++) {
				gridConstraints = new GridBagConstraints();
				labelArray[i][f].setPreferredSize(new Dimension(cat.getIconWidth(), cat.getIconHeight()));
				labelArray[i][f].setOpaque(true);
				labelArray[i][f].setBackground(new Color(111, 60, 137));
				labelArray[i][f].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				gridConstraints.gridx = f;
				gridConstraints.gridy = i;
				gridConstraints.insets = new Insets(10, 10, 10, 10);
				getContentPane().add(labelArray[i][f], gridConstraints);
				labelArray[i][f].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						labelMouseClicked(e);
					}
				}); // end of addMouseListener
			}

		} // end of for loop
		
		/*
		newButton.setText("Play Again");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = rowCount;
		gridConstraints.insets = new Insets(10, 10, 10, 10);
		getContentPane().add(newButton, gridConstraints);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newButtonActionPerformed(e);
			}
		});
		*/

		counterLabel.setText("Clicks: " + String.valueOf(count));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = rowCount;
		getContentPane().add(counterLabel, gridConstraints);
		
		currentWin.setText("Winning tile: Cat");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = rowCount + 1;
		getContentPane().add(currentWin, gridConstraints);
		
		currentLose.setText("Losing tile: Horse");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = rowCount + 1;
		getContentPane().add(currentLose, gridConstraints);
		
		currentDef.setText("Default tile: Dog");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 2;
		gridConstraints.gridy = rowCount + 1;
		getContentPane().add(currentDef, gridConstraints);

		getContentPane().setBackground(Color.GRAY);
		newButtonActionPerformed();
		pack();
	} // end of constructor

	// methods
	
	private void whiteBGActionPerformed(ActionEvent e) {
		getContentPane().setBackground(Color.WHITE);
		
	}
	
	private void grayBGActionPerformed(ActionEvent e) {
		getContentPane().setBackground(Color.GRAY);
		
	}
	
	private void yellowBGActionPerformed(ActionEvent e) {
		getContentPane().setBackground(Color.YELLOW);
		
	}
	
	
	// win menu item methods
	private void winCatActionPerformed(ActionEvent e) {
		if (winTile == cat) 
			return;
		
		newButtonActionPerformed();
		if (defTile == cat) {
			defTile = loseTile;
			defString = loseString;
		}
		loseTile = winTile;
		loseString = winString;
		winTile = cat;
		winString = "Cat";
		currentWin.setText("Winning tile: Cat");
		currentLose.setText("Losing tile: " + loseString);
		currentDef.setText("Default tile: " + defString);
		
	}
	
	private void winDogActionPerformed(ActionEvent e) {
		if (winTile == dog) 
			return;
		newButtonActionPerformed();
		if (defTile == dog) {
			defTile = loseTile;
			defString = loseString;
		}
		loseTile = winTile;
		loseString = winString;
		winTile = dog;
		winString = "Dog";
		currentWin.setText("Winning tile: Dog");
		currentLose.setText("Losing tile: " + loseString);
		currentDef.setText("Default tile: " + defString);
		
	}
	
	private void winHorseActionPerformed(ActionEvent e) {
		if (winTile == horse) 
			return;
		newButtonActionPerformed();
		if (defTile == horse) {
			defTile = loseTile;
			defString = loseString;
		}
		loseTile = winTile;
		loseString = winString;
		winTile = horse;
		winString = "Horse";
		currentWin.setText("Winning tile: Horse");
		currentLose.setText("Losing tile: " + loseString);
		currentDef.setText("Default tile: " + defString);
		
	}
	
	// lose menu item methods
	
	private void loseCatActionPerformed(ActionEvent e) {
		if (loseTile == cat) 
			return;
		newButtonActionPerformed();
		
		if (defTile == cat) {
			defTile = winTile;
			defString = winString;
		}
		winTile = loseTile;
		winString = loseString;
		loseTile = cat;
		loseString = "Cat";
		currentWin.setText("Winning tile: " + winString);
		currentLose.setText("Losing tile: Cat");
		currentDef.setText("Default tile: " + defString);
		
	}
	
	private void loseDogActionPerformed(ActionEvent e) {
		if (loseTile == dog) 
			return;
		newButtonActionPerformed();
		
		if (defTile == dog) {
			defTile = winTile;
			defString = winString;
		}
		winTile = loseTile;
		winString = loseString;
		loseTile = dog;
		loseString = "Dog";
		currentWin.setText("Winning tile: " + winString);
		currentLose.setText("Losing tile: Dog");
		currentDef.setText("Default tile: " + defString);
		
	}
	
	private void loseHorseActionPerformed(ActionEvent e) {
		if (loseTile == horse) 
			return;
		newButtonActionPerformed();
		
		if (defTile == horse) {
			defTile = winTile;
			defString = winString;
		}
		winTile = loseTile;
		winString = loseString;
		loseTile = horse;
		loseString = "Horse";
		currentWin.setText("Winning tile: " + winString);
		currentLose.setText("Losing tile: Horse");
		currentDef.setText("Default tile: " + defString);
		
	}
	
	// default menu item methods
	
	private void defCatActionPerformed(ActionEvent e) {
		if (defTile == cat) 
			return;
		newButtonActionPerformed();
		
		if (loseTile == cat) {
			loseTile = defTile;
			loseString = defString;
		}else {
			winTile = loseTile;
			winString = loseString;
			loseTile = defTile;
			loseString = defString;
		}
		defTile = cat;
		defString = "Cat";
		currentWin.setText("Winning tile: " + winString);
		currentLose.setText("Losing tile: " + loseString);
		currentDef.setText("Default tile: Cat");
		
	}
	
	private void defDogActionPerformed(ActionEvent e) {
		if (defTile == dog) 
			return;
		newButtonActionPerformed();
		
		if (loseTile == dog) {
			loseTile = defTile;
			loseString = defString;
		}else {
			winTile = loseTile;
			winString = loseString;
			loseTile = defTile;
			loseString = defString;
		}
		defTile = dog;
		defString = "Dog";
		currentWin.setText("Winning tile: " + winString);
		currentLose.setText("Losing tile: " + loseString);
		currentDef.setText("Default tile: Dog");
		
	}
	
	private void defHorseActionPerformed(ActionEvent e) {
		if (defTile == horse) 
			return;
		newButtonActionPerformed();
		
		if (loseTile == horse) {
			loseTile = defTile;
			loseString = defString;
		}else {
			winTile = loseTile;
			winString = loseString;
			loseTile = defTile;
			loseString = defString;
		}
		defTile = horse;
		defString = "Horse";
		currentWin.setText("Winning tile: " + winString);
		currentLose.setText("Losing tile: " + loseString);
		currentDef.setText("Default tile: Horse");
		
	}

	private void exitForm(WindowEvent evt) {
		System.exit(0);
	}

	private void newButtonActionPerformed() {
		// clear boxes and hide cat
		for (int i = 0; i < rowCount; i++) {
			for (int f = 0; f < colCount; f++) {
				labelArray[i][f].setIcon(null);
				labelArray[i][f].setBackground(new Color(111, 60, 137));
			}
		}
		winX = myRandom.nextInt(rowCount);
		winY = myRandom.nextInt(colCount);
		// catX = 3;
		// catY = 3;
		loseX = myRandom.nextInt(rowCount);
		loseY = myRandom.nextInt(colCount);
		while (loseX == winX && loseY == winY) {
			loseX = myRandom.nextInt(rowCount);
			loseY = myRandom.nextInt(colCount);
		}
		if (failed.equals("no")) {
			count = 0;
		}
		counterLabel.setText("Clicks: " + String.valueOf(count));
		newButton.setEnabled(false);
		failed = "no";

	}

	private void labelMouseClicked(MouseEvent e) {
		Component clickedComponent = e.getComponent();

		int f;
		int i;
		for (i = 0; i < rowCount; i++) {
			if (clickedComponent == labelArray[winX][winY]) {
				labelArray[winX][winY].setIcon(winTile);
				labelArray[winX][winY].setBackground(Color.WHITE);
				count = count + 1;
				counterLabel.setText("Clicks: " + String.valueOf(count));
				newButton.setEnabled(true);
				clickedTile = "win";
				winLose();
				break;
			}
			if (clickedComponent == labelArray[loseX][loseY]) {
				labelArray[loseX][loseY].setIcon(loseTile);
				labelArray[loseX][loseY].setBackground(Color.WHITE);
				count = count + 1;
				counterLabel.setText("Clicks: " + String.valueOf(count));
				clickedTile = "lose";
				winLose();
				break;
			}
			for (f = 0; f < colCount; f++)
				if (clickedComponent == labelArray[i][f]) {
					labelArray[i][f].setBackground(Color.WHITE);
					labelArray[i][f].setIcon(defTile);
					count = count + 1;
					counterLabel.setText("Clicks: " + String.valueOf(count));
					break;
				}

		}

	} // end of mouseClicked

	private void winLose() {
		if (clickedTile.equals("win")) {
			int response;
			response = JOptionPane.showConfirmDialog(null, "You win! Do you want to play again?", "",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				failed = "no";
				newButtonActionPerformed();
			} else {
				exitForm(null);

			}
		} else {
			int response;
			response = JOptionPane.showConfirmDialog(null, "You lost. Do you want to play again?",
					"", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				failed = "yes";
				newButtonActionPerformed();
			} else {
				exitForm(null);

			}
		}
	}

}// end of FindTreasure class