/**
* @file MainMenu.java
* @title MainMenu
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class is the model of the main menu in MVC architecture
*/
package model;

import java.awt.Dimension;

import javax.swing.JFrame;

import view.BoardView;
import view.MainMenuView;

public class MainMenu {

	private MainMenuView menuView;
	
	/**
	* @brief Constructor for the Main Menu
	*/
	public MainMenu(){
		super();
	}
	
	/**
	* @brief Connects this model to a view (display)
	* @param menuView The MainMenuView object that this model connects to for display.
	*/
	public void setView(MainMenuView menuView){
		this.menuView = menuView;
	}
	

	/**
	* @brief Starts the game
	* @details Creates the frame that will contain the board and connects a display to it; also instantiates the appropriate player and ghost characters on the board.
	*/
	public void startGame(){
		this.menuView.setFrameInvis();
		JFrame frame = new JFrame("Namcap");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Board board = new Board();
		BoardView boardGUI = new BoardView(board, frame);
		Player player = new Player(board);
		Ghost ghost = new Ghost(board);
		frame.setContentPane(boardGUI);
		frame.setPreferredSize(new Dimension(437,450));
		frame.pack();
		frame.setVisible(true);
	}
}
