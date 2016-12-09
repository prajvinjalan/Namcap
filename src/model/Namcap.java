//This code follows the Google Java Standards referenced at https://google.github.io/styleguide/javaguide.html
/**
* @file Namcap.java
* @title Namcap
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class instantiates the game's Main Menu
*/
package model;

import java.awt.Dimension;
import javax.swing.JFrame;
import view.MainMenuView;

public class Namcap {

	/**
	* @brief Instantiates the initial frame and the Main Menu
	* @param String[] args command line arguments.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame("Namcap");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainMenu menu = new MainMenu();
		MainMenuView menuGUI = new MainMenuView(menu, frame);
		frame.setContentPane(menuGUI);
		frame.setPreferredSize(new Dimension(450,620));
		frame.pack();
		frame.setVisible(true);
	}
}
