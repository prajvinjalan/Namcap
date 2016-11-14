package model;

import java.util.ArrayList;
import view.BoardView;

public class Board {

	BoardView view;
	boolean[][] barriers; //array for grid which defines whether a square is a barrier
	
	//array for grid which defines the type of dot a square has (if any)
	int[][] dots; //0 - no dot, 1 - regular dot, other numbers for future functionality
	
	Player player;
	Score score;
	Ghost ghost;
	
	public Board(){
		this.barriers = new boolean[21][21];
		this.dots = new int[21][21];
		this.score = new Score();
		
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				this.barriers[i][j] = false;
				this.dots[i][j] = 1;
			}
		}
	}
	
	public void setGhost(Ghost ghost){
		this.ghost = ghost;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public void setView(BoardView view){
		this.view = view;
	}
	
	public void updateBarrier(int x, int y){
		this.barriers[x][y] = true;
	}
	
	public void updateBarrier(ArrayList<int[][]> points){
		for (int[][] list : points){
			for (int[] location : list){
				this.barriers[location[0]][location[1]] = true;
			}
		}
	}
	
	
	public boolean getBarrier(int x, int y){
		return this.barriers[x][y];
	}
	
	public void updateDot(int x, int y, int type){
		this.dots[x][y] = type;
	}
	
	public void updateDot(ArrayList<int[][]> points){
		for (int[][] list : points){
			for (int[] location : list){
				this.dots[location[0]][location[1]] = 0;
			}
		}
	}
	
	public int getDot(int x, int y){
		return this.dots[x][y];
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Ghost getGhost(){
		return this.ghost;
	}

	public Score accessScore(){
		return this.score;
	}

	public void changePlayerDirection(char dir){
		this.getPlayer().setNewDirection(dir);
	}
	
	public void updateView(){
		this.view.repaint();
	}
}