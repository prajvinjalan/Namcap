//This code follows the Google Java Standards referenced at https://google.github.io/styleguide/javaguide.html
/**
* @file ButtonController.java
* @title ButtonController
* @author VPB Game Studio
* @data 13/11/2016
* @brief This class handles any user input to buttons.
* @details This class handles any user input to the buttons of the application (primarily the main menu). The input pertains only to the implemented 
* buttons of the program.
*/
package controller;

import java.awt.event.*;
import javax.swing.*;

import model.MainMenu;

/**
* Button Controller class
* 
* This class handles any user input to the buttons of the application. The input pertains only to the implemented
* butons of the program.
*/
public class ButtonController implements ActionListener{

	/**
	* Corresponding main menu for this instance of the program.
	*/
	private MainMenu menu;
	/**
	* Corresponding JButton implemented on the main menu.
	*/
	private JButton button;
	
	/**
	* @brief Constructor for a Button Controller
	* @details Constructor accepts two parameters for both the corresponding main menu and Jbutton objects.
	* @param menu - MainMenu corresponding to the implemented button.
	* @param button - JButton corresponding to the implemented menu (frame).
	*/
	public ButtonController(MainMenu menu, JButton button){
		this.menu = menu;
		this.button = button;
	}
	
	/**
	* @brief Handles button action occurrence
	* @param e - ActionEvent representing a button press.
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.button.getText() == "Start Game"){
			this.menu.startGame();
		}
	}

}
