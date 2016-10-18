package model;

public class Score {

	private int value;

	public Score(){
		this.value = 0;
	}

	public int getScore(){
		return this.value;
	}

	public void addScore(int pts){
		this.value += pts;
	}

}