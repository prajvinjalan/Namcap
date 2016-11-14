/**
* @file Board.java
* @title Board
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class is the model of the game board following MVC architecture
*/
package model;

import java.util.ArrayList;
import view.BoardView;

public class Board {

	/**
	* @todo Comment class variables
	*/
	BoardView view;
	boolean[][] barriers; //array for grid which defines whether a square is a barrier
	
	//array for grid which defines the type of dot a square has (if any)
	int[][] dots; //0 - no dot, 1 - regular dot, other numbers for future functionality
	
	Player player;
	Score score;
	Ghost ghost;
	
	/**
	* @brief Constructor for Board
	* @details Initializes all the barriers and dots for the board grid with appropriate values for the initial game state (before the game starts); also creates a score object.
	*/
	public Board(){
		this.barriers = new boolean[21][21];
		this.dots = new int[21][21];
		this.score = new Score();
		
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				this.barriers[i][j] = false;
				this.dots[i][j] = 1;
			}
		}
	}
	
	/**
	* @brief Connects model to a corresponding ghost character
	* @param ghost The Ghost(s) object for this game board.
	*/
	public void setGhost(Ghost ghost){
		this.ghost = ghost;
	}
	
	/**
	* @brief Connects the model to a corresponding player character
	* @param player The Player object for this game board.
	*/
	public void setPlayer(Player player){
		this.player = player;
	}
	
	/**
	* @brief Connects the model to a corresponding view (display)
	* @param view The BoardView object that this model connects to for display (GUI).
	*/
	public void setView(BoardView view){
		this.view = view;
	}
	
	/**
	* @brief Updates a singular grid point for barrier existence
	* @param x The x coordinate for the location of the barrier on the board's grid.
	* @param y The y coordinate for the location of the barrier on the board's grid.
	*/
	public void updateBarrier(int x, int y){
		this.barriers[x][y] = true;
	}
	
	/**
	* @brief Updates multiple grid points for barrier existence
	* @param points The ArrayList of 2D Integer arrays that contains the (x,y) tuple locations of barriers on the map.
	*/
	public void updateBarrier(ArrayList<int[][]> points){
		for (int[][] list : points){
			for (int[] location : list){
				this.barriers[location[0]][location[1]] = true;
			}
		}
	}
	
	/**
	* @brief Accessor method for barrier existence at a grid point
	* @param x The x coordinate for the location of the potential barrier on the board's grid.
	* @param y The y coordinate for the location of the potential barrier on the board's grid.
	* @return A boolean value for whether a barrier exists at this point.
	*/
	public boolean getBarrier(int x, int y){
		return this.barriers[x][y];
	}
	
	/**
	* @brief Updates a singular grid point for dot existence
	* @param x The x coordinate for the location of the dot on the board's grid.
	* @param y The y coordinate for the location of the dot on the board's grid.
	* @param type The type of dot for this location (0 - no dot, 1 - regular dot, 2 - big dot).
	*/
	public void updateDot(int x, int y, int type){
		this.dots[x][y] = type;
	}
	
	/**
	* @brief Updates mutliple grid points for dot existence
	* @param points The ArrayList of 2D Integer arrays that contains the (x,y) tuple locations of dots on the map.
	*/
	public void updateDot(ArrayList<int[][]> points){
		for (int[][] list : points){
			for (int[] location : list){
				this.dots[location[0]][location[1]] = 0;
			}
		}
	}
	
	/**
	* @brief Accessor method for dot existence at a grid point
	* @param x The x coordinate for the location of the potential dot on the board's grid.
	* @param y The y coordinate for the location of the potential dot on the board's grid.
	* @return An integer value for the type of dot that exists at this point.
	*/
	public int getDot(int x, int y){
		return this.dots[x][y];
	}
	
	/**
	* @brief Accessor method for the board's player object
	* @return The player object for this board.
	*/
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	* @brief Accessor method for the board's ghost object
	* @return The ghost object for this board.
	*/
	public Ghost getGhost(){
		return this.ghost;
	}

	/**
	* @brief Accessor method for the board's score object
	* @return The score on the board (player's current score state).
	*/
	public Score accessScore(){
		return this.score;
	}

	/**
	* @brief Manipulates the player's direction on the board
	* @param dir The new direction that the player is facing on the board.
	*/
	public void changePlayerDirection(char dir){
		this.getPlayer().setNewDirection(dir);
	}
	
	/**
	* @brief Updates the display (view) of this board
	* @details Calls the repaint method on the board's view to redraw the paint components of the view (most of the board is manually painted).
	*/
	public void updateView(){
		this.view.repaint();
	}
}