package model;

public class Player {
	
	char prevDirection, newDirection;
	int prevX, prevY, currX, currY, sq;
	boolean stop;
	int pixelInc = 2;
	Board board;
	
	public Player(Board board){
		this.board = board;
		this.board.setPlayer(this);
		this.sq = 20;
		this.stop = false;
		this.prevX = 10*this.sq;
		this.prevY = 15*this.sq;
		this.currX = 10*this.sq;
		this.currY = 15*this.sq;
		this.prevDirection = 'R';
		this.newDirection = 'R';
		
	}
	
	public boolean isBarrier(int x, int y){
		if (x<=380 && y<=380 && x>=20 && y>=20 /*!this.board.getBarrier(x/20-1, y/20-1)*/){
			return true;
		}
		return false;
		
	}
	
	public void move(){
		this.prevX = this.currX;
		this.prevY = this.currY;
		if (this.currX % 20 == 0 && this.currY % 20 == 0 || 
				(this.newDirection == 'L' && this.prevDirection == 'R') ||
				(this.newDirection == 'U' && this.prevDirection == 'D') ||
				(this.newDirection == 'R' && this.prevDirection == 'L') ||
				(this.newDirection == 'D' && this.prevDirection == 'U')){
			
			switch(this.newDirection){
			case 'L':
				if (this.isBarrier(this.currX - pixelInc, this.currY)){
					this.currX -= this.pixelInc;
				}
				break;
			case 'R':
				if (this.isBarrier(this.currX + pixelInc, this.currY)){
					this.currX += this.pixelInc;
				}
				break;
			case 'U':
				if (this.isBarrier(this.currX, this.currY - this.pixelInc)){
					this.currY -= this.pixelInc;
				}
				break;
			case 'D':
				if (this.isBarrier(this.currX, this.currY + this.pixelInc)){
					this.currY += this.pixelInc;
				}
				break;
		}
			
		}
		
		if (this.prevX == this.currX && this.prevY == this.currY){
			switch(this.prevDirection){
				case 'L':
					if (this.isBarrier(this.currX - pixelInc, this.currY)){
						this.currX -= this.pixelInc;
					}
					break;
				case 'R':
					if (this.isBarrier(this.currX + pixelInc, this.currY)){
						this.currX += this.pixelInc;
					}
					break;
				case 'U':
					if (this.isBarrier(this.currX, this.currY - this.pixelInc)){
						this.currY -= this.pixelInc;
					}
					break;
				case 'D':
					if (this.isBarrier(this.currX, this.currY + this.pixelInc)){
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
