/**
* @file Score.java
* @title Score
* @author VPB Game Studio
* @date 13/11/2016
* @brief This class represents the scoring in the game.
* @details This class represents all the functionalities and attributes of the scoring system in the game.	
*/
package model;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Score {

	/**
	* Represents (holds) current Score value of the game.
	*/
	private int value;
	/**
	* Represents current high score value displayed on GUI.
	*/
	private int highScore;

	/**
	* @brief Constructor for the Score state object
	*/
	public Score(){
		this.value = 0;
		this.readHighScore();
	}

	/**
	* @brief Method returns the current Score
	* @return Current Score value for the game instance.
	*/
	public int getScore(){
		return this.value;
	}

	/**
	* @brief Method returns the current high score value
	* @details The method returns the currently stored high score value (read from file) for this game instance.
	* @return Current high score value for the game instance.
	*/
	public int getHighScore(){
		return this.highScore;
	}

	/**
	* @brief Increments the score
	* @param pts - Integer points value to add to the current score value.
	*/
	public void addScore(int pts){
		this.value += pts;
	}

	/**
	* @brief Updates the high score log file
	* @details Appends to the high score log file ('high_score') in the case that the achieved score is greater than the 
	* previously recorded high score.
	* @param pts - Achieved Score for the current instance of the game (Score value).
	*/
	public void updateHighScore(int pts){
		if(this.value > this.highScore){
			try{
				File hsFile = new File("high_score");
				FileWriter scoreFile = new FileWriter(hsFile);
				scoreFile.append(this.value + "");
				scoreFile.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	* @brief Reads high score value from log file
	* @details Reads the high score value from the high score log file ('high_score') and
	* updates the current Score states high score value consequently.
	*/
	public void readHighScore(){
		try{
			File hsFile = new File("high_score");
			if(!hsFile.exists()){
				hsFile.createNewFile();
				FileWriter scoreFile = new FileWriter(hsFile);
				scoreFile.append(0 + "");
				scoreFile.close();
			}
			Scanner scoreFile = new Scanner(hsFile);
			this.highScore = Integer.parseInt(scoreFile.next());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}