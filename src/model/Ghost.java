/**
* @file Ghost.java
* @title Ghost
* @author VPB Game Studio
* @date 13/11/2016
* @brief This class represents a Ghost in the game.
* @details This class represents all the functionalities and attributes of a Ghost in the game.	
* @extends Character
*/
package model;

import java.util.Random;

public class Ghost extends Character {

	 /** 
	 * @brief Constructor for Ghost
	 * @details Constructor accepts one parameter for the game's Board model.
     * @param board - Board object that is passed in to add character (in this case, enemy) functionality.
     */
	public Ghost(Board board){
		super(board);
		this.board.setGhost(this);
		this.prevX = 10*this.sq;
		this.prevY = 7*this.sq;
		this.currX = 10*this.sq;
		this.currY = 7*this.sq;
	}
	
	/**
	* @brief Method returns whether or not Ghost can keep moving in the same direction
	* @details This method determines whether the ghost can keep moving in the current direction it is headed.
	* @param direction - Char value representing the Ghost's current direction.
	* @return Boolean value (true - direction remains valid, or false - direction invalid).
	*/
	public boolean keepMoving(char direction){
		switch(direction){
		case 'L':
			return (!this.isBarrier(this.currX - this.pixelInc, this.currY));
			
		case 'R':
			return (!this.isBarrier(this.currX + this.sq, this.currY));
			
		case 'U':
			return (!this.isBarrier(this.currX, this.currY - this.pixelInc));
			
		case 'D':
			return (!this.isBarrier(this.currX, this.currY + this.sq));
		default:
			return true;
		}
	}

	/**
	* @brief Determines a valid path for the Ghost to move
	* @details This method is the Ghost's AI. It is used to change the direction of the ghost's movement when the ghost
	* hits a barrier. The new direction is chosen randomly.
	*/
	public void ghostMove(){
		if (keepMoving(this.prevDirection)){
			this.newDirection = this.prevDirection;
		}else{
			Random randomGenerator = new Random();
			int randomDirection = randomGenerator.nextInt(4) + 1;
			switch(randomDirection){
				case 1:
					this.newDirection = 'L';
					break;
				case 2:
					this.newDirection = 'R';
					break;
				case 3:
					this.newDirection = 'U';
					break;
				case 4:
					this.newDirection = 'D';
					break;
			}
		}
		this.prevX = this.currX;
		this.prevY = this.currY;
		if (this.currX % 20 == 0 && this.currY % 20 == 0 || 
				(this.newDirection == 'L' && this.prevDirection == 'R') ||
				(this.newDirection == 'U' && this.prevDirection == 'D') ||
				(this.newDirection == 'R' && this.prevDirection == 'L') ||
				(this.newDirection == 'D' && this.prevDirection == 'U')){
			
			switch(this.newDirection){
			case 'L':
				if (!this.isBarrier(this.currX - this.pixelInc, this.currY)){
					this.currX -= this.pixelInc;
				}
				break;
			case 'R':
				if (!this.isBarrier(this.currX + this.sq, this.currY)){
					this.currX += this.pixelInc;
				}
				break;
			case 'U':
				if (!this.isBarrier(this.currX, this.currY - this.pixelInc)){
					this.currY -= this.pixelInc;
				}
				break;
			case 'D':
				if (!this.isBarrier(this.currX, this.currY + this.sq)){
					this.currY += this.pixelInc;
				}
				break;
			}
			
		}
		
		if (this.prevX == this.currX && this.prevY == this.currY){
			switch(this.prevDirection){
				case 'L':
					if (!this.isBarrier(this.currX - this.pixelInc, this.currY)){
						this.currX -= this.pixelInc;
					}
					break;
				case 'R':
					if (!this.isBarrier(this.currX + this.sq, this.currY)){
						this.currX += this.pixelInc;
					}
					break;
				case 'U':
					if (!this.isBarrier(this.currX, this.currY - this.pixelInc)){
						this.currY -= this.pixelInc;
					}
					break;
				case 'D':
					if (!this.isBarrier(this.currX, this.currY + this.sq)){
						this.currY += this.pixelInc;
					}
					break;
			}
		}else{
			this.prevDirection = this.newDirection;
		}
	}
}
