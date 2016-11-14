/**
* @file Character.java
* @title Character
* @author VPB Game Studio
* @date 13/11/2016
* @brief This class represents a Character in the game (Enemy or Player)
* @details This class represents all the shared functionalities and attributes of a Character in the game.	
*/
package model;

public class Character {

	/**
	* Chars representing the previous and new (current) directions of the Character.
	* L - Left, R - Right, U - Up, D - Down.
	*/
	char prevDirection, newDirection;
	/**
	* Integer values representing the previous and current Character x- and y-coordinate locations on
	* the grid, with corresponding grid square size parameter 'sq' (pixels).
	*/
	int prevX, prevY, currX, currY, sq;
	/**
	* Boolean value representing Character movement stopped (barrier met/game ended).
	*/
	boolean stop;
	/**
	* Represents the pixel increment for Character movement in level per frame. 
	*/
	int pixelInc = 4;
	/**
	* Corresponding Board model for the given Character object.
	*/
	Board board;
	
 	/** 
	 * @brief Constructor for Character
	 * @details Constructor accepts one parameter for the game Board model.
     * @param board - Board object is passed in to which to add character functionality.
     */
	public Character(Board board){
		this.board = board;
		this.sq=20;
		this.stop = false;
		this.prevDirection = 'R';
		this.newDirection = 'R';
	}

	/**
	* @brief Method returns wheteher the character has come in contact with a barrier
	* @param x - Character's current x-coordinate location.
	* @param y - Character's current y-coordinate location.
	* @return Boolean value (true - Character has met barrier, false - otherwise).
	*/
	public boolean isBarrier(int x, int y){
		if (this.board.getBarrier(x/this.sq, y/this.sq)){
			return true;
		}
		return false;
	}

	/**
	* @brief Method returns character's current x-coordinate location
	* @return Character's current x-coordinate location.
	*/
	public int getCurrX(){
		return this.currX;
	}

	/**
	* @brief Method returns character's current y-coordinate location
	* @return Character's current y-coordinate location.
	*/
	public int getCurrY(){
		return this.currY;
	}

	/**
	* @brief Returns the character's previous direction
	* @return Character's previous direction (corresponding char value).
	*/
	public char getCurrDirection(){
		return this.prevDirection;
	}

	/**
	* @brief Sets the character's new direction
	* @param dir - Char value to represent the character's new direction (char correspondence represented above).
	*/
	public void setNewDirection(char dir){
		this.newDirection = dir;
	}	
}
