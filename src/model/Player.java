package model;

public class Player {

	private int lives;
	private Board game;
	private int[][] location;

	public Player(Board currentBoard){
		this.lives = 3;
		this.game = currentBoard;
		this.location = [1][1];
	}

	public updateLives(int update){
		this.lives += update;
	}

	public updateLocation(int x, int y){
		this.location = [x][y];
	}

	
	
}