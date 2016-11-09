package model;

public class Player extends Character {
	

	public Player(Board board){
		super(board);
		this.board.setPlayer(this);
		this.prevX = 10*this.sq;
		this.prevY = 15*this.sq;
		this.currX = 10*this.sq;
		this.currY = 15*this.sq;
	}
	
	
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
	
	public void checkCollision(){
		if ((this.board.getGhost().getCurrX()>=this.currX-this.sq)&&(this.board.getGhost().getCurrX()<=this.currX+this.sq)&&((this.board.getGhost().getCurrY()>=this.currY-this.sq))&&(this.board.getGhost().getCurrY()<=this.currY+this.sq)){
			this.endGame();
		}
		
	}
	
	public void endGame(){
		this.board.view.endGame();
	}
}
