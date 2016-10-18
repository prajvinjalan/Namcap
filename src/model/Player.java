package model;

public class Player {
	
	char prevDirection, newDirection;
	int prevX, prevY, currX, currY, sq;
	boolean stop;
	int pixelInc = 3;
	Board board;
	
	public Player(Board board){
		this.board = board;
		this.board.setPlayer(this);
		this.sq = 20;
		this.stop = false;
		this.prevX = 10*this.sq;
		this.prevY = 15*this.sq;
		this.currX = 10*this.sq;
		this.currY = 15*this.sq+4;
		this.prevDirection = 'R';
		this.newDirection = 'R';
		
	}
	
	public void move(){
		this.prevX = this.currX;
		this.prevY = this.currY;
		
		if (this.currX % 20 == 0 && this.currY % 20 == 0)
		
		switch(this.newDirection){
			case 'L':
				this.currX -= this.pixelInc;
				break;
			case 'R':
				this.currX += this.pixelInc;
				break;
			case 'U':
				this.currY -= this.pixelInc;
				break;
			case 'D':
				this.currY += this.pixelInc;
				break;
		}
		
		if (this.prevX == this.currX && this.prevY == this.currY){
			switch(this.prevDirection){
				case 'L':
					this.currX -= this.pixelInc;
					break;
				case 'R':
					this.currX += this.pixelInc;
					break;
				case 'U':
					this.currY -= this.pixelInc;
					break;
				case 'D':
					this.currY += this.pixelInc;
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
