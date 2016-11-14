/**
* @file Namcap.java
* @title Namcap
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class instantiates the game's main menu
*/
package model;

import java.awt.Dimension;

import javax.swing.JFrame;

import view.MainMenuView;

public class Namcap {

	/**
	* @brief Creates the inital frame and the main menu
	* @param String[] args command line arguments.
	*/
	public static void main(String[] args){
		JFrame frame = new JFrame("Namcap");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainMenu menu = new MainMenu();
		MainMenuView menuGUI = new MainMenuView(menu, frame);
		frame.setContentPane(menuGUI);
		frame.setPreferredSize(new Dimension(450,450));
		frame.pack();
		frame.setVisible(true);
	}
}
