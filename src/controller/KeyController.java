//This code follows the Google Java Standards referenced at https://google.github.io/styleguide/javaguide.html
/**
* @file KeyController.java
* @title KeyController
* @author VPB Game Studio
* @data 13/11/2016
* @brief This class handles any user key inputs.
* @details This class handles all user key inputs (primarily used for the movement of the player). Maps the corresponding keys to player movement.
*/
package controller;

import java.awt.event.*;
import javax.swing.*;

import model.Board;

/**
* Key Controller Class
*
* This class handles all user key inputs (primarily used for the movement of the player). Maps the corresponding keys to player movement.
*/
public class KeyController implements KeyListener{

	/**
	* Corresponding game board object for the this instance of the program.
	*/
	private Board board;
	
	/**
	* @brief Constructor for a Key Controller
	* @details Constructor accepts one parameter for the corresponding board object for the controller.
	* @param board - Board corresponding to the instance key controller.
	*/
	public KeyController(Board board){
		this.board = board;
	}

	/**
	* @brief Handles key press events
	* @details Handles key press events and maps to the correct player movement direction or informs the board that user wants to quit.
	* @param e - KeyEvent representing the current key press.
	*/
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			this.board.changePlayerDirection('L');
			break;
		case KeyEvent.VK_RIGHT:
			this.board.changePlayerDirection('R');
			break;
		case KeyEvent.VK_UP:
			this.board.changePlayerDirection('U');
			break;
		case KeyEvent.VK_DOWN:
			this.board.changePlayerDirection('D');
			break;
		case KeyEvent.VK_ESCAPE:
			this.board.quitGame();
		}
		this.board.updateView();
	}

	/**
	* @brief Method required to be included for Key Events
	*/
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	* @brief Method required to be included for Key Events
	*/
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
