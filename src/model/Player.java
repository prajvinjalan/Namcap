/**
* @file Player.java
* @title Player
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class is the Player for the game Board
* @extends Character
*/
package model;

public class Player extends Character {
	
	/**
	* Represents Player lives count (initialized to 3 lives).
	*/
	private int lives;
	
	/**
	* @brief Constructor for Player
	* @details Initializes the Player's initial coordinates on the board, and connects this Player object to the Board model.
	* @param board - Board model that this player is initialized to.
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
	
	/**
	* @brief Accessor method for Player lives count
	* @return Integer representing current count of Player lives.
	*/
	public int getLives(){
		return this.lives;
	}
	
	/**
	* @brief Mutator method for Player lives count
	* @param int - representing the number of lives to set to
	*/
	public void setLives(int lives){
		this.lives = lives;
	}

	/**
	* @brief Controls player movement
	* @details Checks for collisions with Enemy(s) or dots, sets up the Player's direction, and increments pixels to move the Player.
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
	* @brief Checks Board for dots
	* @details Checks if Player collided with dots and directs the Board to update the Score and dots accordingly (if Score is max, endGame is called).
	* Calls the bigDotEaten() method if Player collects the big dot.
	*/
	public void checkDot(){
		int currDot = this.board.getDot(this.currX / 20, this.currY / 20);
		if(currDot == 1){
			this.board.updateDot(this.currX/20,this.currY/20,0);
			this.board.accessScore().addScore(100);
		}
		if(currDot == 2){
			//BIG DOT FUNCTIONALITY
			this.board.updateDot(this.currX/20,this.currY/20,0);
			this.board.accessScore().addScore(200);
			this.board.bigDotEaten();
		}
		if (this.board.noDots()){ //if all dots are gone
			this.endGame();
		}
	}

	/**
	* @brief Checks for collision with Enemy
	* @details Checks if Player collided with a Enemy and calls the endGame method if lives count == 0. Otherwise,
	* Player lives are decremented by 1 and Player location is reset to initial position. If the bog dot was eaten, the enemy is moved
	* to it's start position and points are incremented by 500.
	*/
	public void checkCollision(){
		for (Enemy en : this.board.getEnemy()){
			if (((en.getCurrX()>=this.currX-this.sq+5)&&(en.getCurrX()<=this.currX+this.sq-5)&&((en.getCurrY()>=this.currY-this.sq+5))&&(en.getCurrY()<=this.currY+this.sq-5)) && !en.bigDotEaten){
				if(this.lives == 0){
					this.endGame();
				}else{
					this.lives--;
					this.prevX = 10*this.sq;
					this.prevY = 15*this.sq;
					this.currX = 10*this.sq;
					this.currY = 15*this.sq;
					this.newDirection = 'L';
					this.board.resetEnemies();
					this.board.startPause();
				}
			}else{
				if (((en.getCurrX()>=this.currX-this.sq+5)&&(en.getCurrX()<=this.currX+this.sq-5)&&((en.getCurrY()>=this.currY-this.sq+5))&&(en.getCurrY()<=this.currY+this.sq-5)) && en.bigDotEaten){
					this.board.accessScore().addScore(500);
					en.resetPosition();
					this.board.startPause();
					en.bigDotEaten = false;
					en.changeSpeed(4);
				}
			}
		}
	}

	/**
	* @brief Directs the Board display to end the game
	* @details Updates the high score value for this game instance (if applicable) and calls view end game procedure.
	*/
	public void endGame(){
		this.board.accessScore().updateHighScore(this.board.accessScore().getScore());
		this.board.view.endGame();
	}
	
}
