/**
* @file MainMenu.java
* @title MainMenu
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class is the model of the Main Menu in MVC architecture
*/
package model;

import java.awt.Dimension;
import java.util.ArrayList;

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
	* @brief Connects this model to a corresponding Main Menu View (GUI)
	* @param menuView - MainMenuView object that this model initializes to for display.
	*/
	public void setView(MainMenuView menuView){
		this.menuView = menuView;
	}
	

	/**
	* @brief Starts the game
	* @details Creates the frame that will contain the Board and connects a display to it; also instantiates the appropriate Player and Ghost characters on the Board.
	*/
	public void startGame(){
		this.menuView.setFrameInvis();
		JFrame frame = new JFrame("Namcap");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
		Board board = new Board();
		BoardView boardGUI = new BoardView(board, frame);
		Player player = new Player(board);
		Ghost ghost1 = new Ghost(board);
		Ghost ghost2 = new Ghost(board);
		Ghost ghost3 = new Ghost(board);
		Ghost ghost4 = new Ghost(board);
		frame.setContentPane(boardGUI);
		frame.setPreferredSize(new Dimension(450,450));
		frame.pack();
		frame.setVisible(true);
	}
}
