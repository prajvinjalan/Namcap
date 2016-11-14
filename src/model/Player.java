/**
* @file Player.java
* @title Player
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class is the player for the game board
*/
package model;

public class Player extends Character {
	
	private int lives;
/**
	* @brief Constructor for Player
	* @details Initializes the player's initial coordinates on the board, and connects this player object to the board
	* @param board The board that this player is connected to
	*/
	public Player(Board board){
		super(board);
		this.board.setPlayer(this);
		this.prevX = 10*this.sq;
		this.prevY = 15*this.sq;
		this.currX = 10*this.sq;
		this.currY = 15*this.sq;
		this.lives = 3;
	}
	
	public int getLives(){
		return this.lives;
	}
	/**
	* @brief Controls player movement
	* @details Checks for collisions with ghost or dots, sets up the players direction, and increments pixels to move the player
	*/
	public void move(){
		this.checkCollision();
		this.checkDot();
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
/**
	* @brief Checks board for dots
	* @details Checks if player collided with dots and directs the board to update the score and dots accordingly (if score is max, endGame is called)
	*/
	public void checkDot(){
		int currDot = this.board.getDot(this.currX / 20, this.currY / 20);
		if(currDot == 1){
			this.board.updateDot(this.currX/20,this.currY/20,0);
			this.board.accessScore().addScore(100);
		}else{
			//DO NOTHING
		}
		if (this.board.accessScore().getScore() == 16500){
			this.endGame();
		}
	}
	/**
	* @brief Checks for ghost collision
	* @details Checks if player collided with a ghost and calls the endGame method if that is the case
	*/
	
	public void checkCollision(){
		if ((this.board.getGhost().getCurrX()>=this.currX-this.sq)&&(this.board.getGhost().getCurrX()<=this.currX+this.sq)&&((this.board.getGhost().getCurrY()>=this.currY-this.sq))&&(this.board.getGhost().getCurrY()<=this.currY+this.sq)){
			if(this.lives == 0){
				this.endGame();
			}else{
				this.lives--;
				this.prevX = 10*this.sq;
				this.prevY = 15*this.sq;
				this.currX = 10*this.sq;
				this.currY = 15*this.sq;
			}
		}
	}
	/**
	* @brief Directs the board display to end the game
	*/
	public void endGame(){
		this.board.accessScore().updateHighScore(this.board.accessScore().getScore());
		this.board.view.endGame();
	}
}
