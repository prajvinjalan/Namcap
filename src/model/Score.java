/**
* @file Score.java
* @title Score
* @author VPB Game Studio
* @date 13/11/2016
* @brief This class represents the scoring in the game.
* @details This class represents all the functionalities and attributes of the scoring system in the game.	
* @todo Add test cases for the functions.
*/
package model;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Score {

	private int value;
	private int highScore;

	/**
	* @brief constructor for score
	*/
	public Score(){
		this.value = 0;
		this.readHighScore();
	}

	/**
	* @brief returns the current score
	* @return score value
	*/
	public int getScore(){
		return this.value;
	}

	public int getHighScore(){
		return this.highScore;
	}

	/**
	* @brief  increments the score
	* @param an integer value to add to the current score
	*/
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