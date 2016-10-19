package model;

public class Player {
	
	char prevDirection, newDirection;
	int prevX, prevY, currX, currY, sq;
	boolean stop;
	int pixelInc = 4;
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
		if (this.board.getBarrier(x/this.sq, y/this.sq)){
			return true;
		}
		return false;
		
	}
	
	public void move(){
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

	public void checkDot(){
		int currDot = this.board.getDot(this.currX / 20, this.currY / 20);
		if(currDot == 1){
			this.board.updateDot(this.currX/20,this.currY/20,0);
			this.board.accessScore().addScore(100);
		}else{
			//DO NOTHING
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
