package model;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Score {

	private int value;
	private int highScore;

	public Score(){
		this.value = 0;
		this.readHighScore();
	}

	public int getScore(){
		return this.value;
	}

	public int getHighScore(){
		return this.highScore;
	}

	public void addScore(int pts){
		this.value += pts;
	}

	public void updateHighScore(int pts){
		if(this.value > this.highScore){
			try{
				FileWriter scoreFile = new FileWriter(new File("model/high_score"));
				scoreFile.append(this.value + "");
				scoreFile.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void readHighScore(){
		try{
			Scanner scoreFile = new Scanner(new File("model/high_score"));
			this.highScore = Integer.parseInt(scoreFile.next());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}