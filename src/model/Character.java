/**
* @file Character.java
* @title Character
* @author VPB Game Studio
* @date 13/11/2016
* @brief This class represents a character in the game (Enemy or Player)
* @details This class represents all the shared functionalities and attributes of a character in the game.	
*/
package model;

public class Character {

	char prevDirection, newDirection;
	int prevX, prevY, currX, currY, sq;
	boolean stop;
	int pixelInc = 4;
	Board board;
	
 	/** 
	 * @brief Constructor for Character
	 * @details Constructor accepts one parameter for the game's board.
     * @param board Object is passed in to add character functionality to it.
     */
	public Character(Board board){
		this.board = board;
		this.sq=20;
		this.stop = false;
		this.prevDirection = 'R';
		this.newDirection = 'R';
	}
	/**
	* @brief returns wheteher the character has come in contact with a barrier
	* @param character's x-coordinate.
	* @param character's y-coordinate.
	* @return true or false.
	*/
	public boolean isBarrier(int x, int y){
		if (this.board.getBarrier(x/this.sq, y/this.sq)){
			return true;
		}
		return false;
	}
	/**
	* @brief returns character's current x-coordinate
	* @param current x-coordinate.
	*/
	public int getCurrX(){
		return this.currX;
	}
	/**
	* @brief returns character's current y-coordinate
	* @return current y-coordinate.
	*/
	public int getCurrY(){
		return this.currY;
	}
	/**
	* @brief returns the character's previous direction
	* @return previous direction.
	*/
	public char getCurrDirection(){
		return this.prevDirection;
	}
	/**
	* @brief sets the character's new direction
	* @param a character value to represent the character's new direction.
	*/
	public void setNewDirection(char dir){
		this.newDirection = dir;
	}

	
}
