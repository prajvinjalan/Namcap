package model;

public class Character {

	char prevDirection, newDirection;
	int prevX, prevY, currX, currY, sq;
	boolean stop;
	int pixelInc = 4;
	Board board;
	
	public Character(Board board){
		this.board = board;
		this.sq=20;
		this.stop = false;
		this.prevDirection = 'R';
		this.newDirection = 'R';
	}
	
	public boolean isBarrier(int x, int y){
		if (this.board.getBarrier(x/this.sq, y/this.sq)){
			return true;
		}
		return false;
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
