package model;

import java.util.Random;

public class Ghost {
	
	char prevDirection, newDirection;
	int prevX, prevY, currX, currY, sq;
	boolean stop;
	int pixelInc = 4;
	Board board;
	
	public Ghost(Board board){
		this.board = board;
		this.board.setGhost(this);
		this.sq = 20;
		this.stop = false;
		this.prevX = 10*this.sq;
		this.prevY = 7*this.sq;
		this.currX = 10*this.sq;
		this.currY = 7*this.sq;
		this.prevDirection = 'R';
		this.newDirection = 'R';
	}
	
	public boolean isBarrier(int x, int y){
		if (this.board.getBarrier(x/this.sq, y/this.sq)){
			return true;
		}
		return false;
		
	}
	
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
	
	public int getCurrX(){
		return this.currX;
	}
	
	public int getCurrY(){
		return this.currY;
	}
	
	public char getCurrDirection(){
		return this.prevDirection;
	}
	
	public void setNewDirection(char dir){
		this.newDirection = dir;
	}

}
