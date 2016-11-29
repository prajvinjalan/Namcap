/**
* @file Board.java
* @title Board
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class is the model of the game board following MVC architecture
*/
package model;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import view.BoardView;

public class Board {

	/**
	* Corresponding view object for the Board model component (following MVC).
	*/
	BoardView view;
	/**
	* Array containing grid barrier status (whether a grid section/point is a barrier).
	*/
	boolean[][] barriers;
	/**
	* Array containing grid dot status (defines the dot type of a given grid section/point).
	* 0 - no dot, 1 - regular (default) dot, 2 - big dot (in-game power up).
	*/
	//array for grid which defines the type of dot a square has (if any)
	int[][] dots; 
	/**
	* Represents corresponding player object for the Board model (instance).
	*/
	Player player;
	/**
	* Represents the corresponding score object for the Board model (instance).
	*/
	Score score;
	/**
	* Represents the corresponding enemy objects (usually multiple in-game) for the Board model.
	*/
	ArrayList<Enemy> enemies;
	/**
	* Time that the game started (used to determine estimated time the game has been running)
	*/
	long startTime;
	/**
	* Time that the game has been running
	*/
	long estimatedTimeElapsed;
	/**
	* Time that the game pauses at
	*/
	long startPauseTime;
	/**
	* Time that the game has been paused for
	*/
	long estimatedPauseTimeElapsed;
	/**
	* Time that the big dot gets eat
	*/
	long startBigDotTime;
	/**
	* Time that the big dot has been on for
	*/
	long estimatedBigDotTimeElapsed;
	/**
	* Whether or not the game is paused currently
	*/
	boolean pause;
	/**
	* Whether or not the big dot is currently on
	*/
	boolean bigDotOn;
	
	/**
	* @brief Constructor for Board
	* @details Initializes all the barriers and dots for the Board grid with appropriate values for the initial game state (before the game starts); also creates a score object and initializes the game start timer.
	*/
	public Board(){
		this.barriers = new boolean[21][21];
		this.dots = new int[21][21];
		this.score = new Score();
		this.enemies = new ArrayList<Enemy>();
		
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				this.barriers[i][j] = false;
				this.dots[i][j] = 1;
			}
		}
		//manually updates the big dots
		this.updateDot(1, 2, 2);
		this.updateDot(19, 2, 2);
		this.updateDot(1, 15, 2);
		this.updateDot(19, 15, 2);

		this.bigDotOn = false;

		this.startTime = System.nanoTime(); //starts a timer
	}
	
	/**
	* @brief Connects model to a corresponding enemy character
	* @param enemy - Enemy(s) object for this game Board model.
	*/
	public void setEnemy(Enemy enemy){
		this.enemies.add(enemy);
	}
	
	/**
	* @brief Connects the model to a corresponding player character
	* @param player - Player object for this game Board model.
	*/
	public void setPlayer(Player player){
		this.player = player;
	}
	
	/**
	* @brief Connects the model to a corresponding view (display)
	* @param view - BoardView object that this model connects to for display (GUI).
	*/
	public void setView(BoardView view){
		this.view = view;
	}

	/**
	* @brief Accessor method for this board's display
	* @return The BoardView object that this board uses for display
	*/
	public BoardView getView(){
		return this.view;
	}
	
	/**
	* @brief Updates a singular grid point for barrier existence
	* @param x - The x-coordinate for the location of the barrier on the Board's grid.
	* @param y - The y-coordinate for the location of the barrier on the Board's grid.
	*/
	public void updateBarrier(int x, int y){
		this.barriers[x][y] = true;
	}
	
	/**
	* @brief Updates multiple grid points for barrier existence
	* @param points - ArrayList of 2D Integer arrays that contains the (x,y) tuple locations of barriers on the map.
	*/
	public void updateBarrier(ArrayList<int[][]> points){
		for (int[][] list : points){
			for (int[] location : list){
				this.barriers[location[0]][location[1]] = true;
			}
		}
	}
	
	/**
	* @brief Accessor method for barrier existence at a grid point
	* @param x - The x-coordinate for the location of the potential barrier on the Board's grid.
	* @param y - The y-coordinate for the location of the potential barrier on the Board's grid.
	* @return A boolean value for whether a barrier exists at the specified point.
	*/
	public boolean getBarrier(int x, int y){
		return this.barriers[x][y];
	}
	
	/**
	* @brief Updates a singular grid point for dot existence
	* @param x - The x-coordinate for the location of the dot on the Board's grid.
	* @param y - The y-coordinate for the location of the dot on the Board's grid.
	* @param type - The type of dot for this specified location (0 - no dot, 1 - regular (default) dot, 2 - big dot).
	*/
	public void updateDot(int x, int y, int type){
		this.dots[x][y] = type;
	}
	
	/**
	* @brief Updates mutliple grid points for dot existence
	* @param points - ArrayList of 2D Integer arrays that contains the (x,y) tuple locations of dots on the map.
	*/
	public void updateDot(ArrayList<int[][]> points){
		for (int[][] list : points){
			for (int[] location : list){
				this.dots[location[0]][location[1]] = 0;
			}
		}
	}
	
	/**
	 * @brief Checks if there are any dots left on the board
	 * @return boolean value that is true if all the dots on boards have been collected,
	 * else, returns false
	 */
	public boolean noDots(){
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				if (this.dots[i][j] != 0){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	* @brief Accessor method for dot existence at a grid point
	* @param x - The x-coordinate for the location of the potential dot on the Board's grid.
	* @param y - The y-coordinate for the location of the potential dot on the Board's grid.
	* @return An integer value for the type of dot that exists at the specified point.
	*/
	public int getDot(int x, int y){
		return this.dots[x][y];
	}
	
	/**
	* @brief Accessor method for the Board's player object
	* @return The player object corresponding to the Board instance.
	*/
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	* @brief Accessor method for the Board's enemy objects
	* @return The enemy objects (in an ArrayList) corresponding to the Board instance.
	*/
	public ArrayList<Enemy> getEnemy(){
		return this.enemies;
	}

	/**
	* @brief Accessor method for the Board's score object
	* @return The score on the Board (player's current score state).
	*/
	public Score accessScore(){
		return this.score;
	}

	/**
	* @brief Manipulates the player's direction on the Board
	* @param dir - New direction that the player is facing on the Board.
	*/
	public void changePlayerDirection(char dir){
		this.getPlayer().setNewDirection(dir);
	}
	
	/**
	* @brief Updates the display (view) of this Board instance
	* @details Calls the repaint method on the Board's view to redraw the paint components of the view (most of the Board is manually painted).
	*/
	public void updateView(){
		this.view.repaint();
	}

	/**
	* @brief Directs the display (view) that the user wishes to quit the game
	*/
	public void quitGame(){
		this.view.quitGame();
	}

	/**
	* @brief Directs the display (view) to prompt the user to take a break if sufficient time has elapsed
	*/
	public void checkTimeRunning(){
		long breakTime = 7200L; //2 hours
		this.estimatedTimeElapsed = System.nanoTime() - this.startTime;

		if (TimeUnit.NANOSECONDS.toSeconds(this.estimatedTimeElapsed) == breakTime){
			this.view.promptBreak();
		}
	}

	/**
	* @brief Restarts the timer for the break prompt
	*/
	public void restartTimer(){
		this.startTime = System.nanoTime();
	}

	/**
	* @brief Starts the timer for game pause (when player loses a life)
	*/
	public void startPause() {
		this.startPauseTime = System.nanoTime();
		this.pause = true;
	}

	/**
	* @brief Accessor for if the game is paused
	* @return The boolean value for whether the game is paused or not
	*/
	public boolean getPause() {
		return this.pause;
	}
	
	/**
	* @brief Checks how long the game has been paused for (after life loss)
	* @details Depending on the time elapsed for the pause, the view will be directed to update the continue text that displays for the user (every second) and display nothing once the time has passed
	*/
	public void checkPauseTime(){
		this.estimatedPauseTimeElapsed = System.nanoTime() - this.startPauseTime;

		if (TimeUnit.NANOSECONDS.toSeconds(this.estimatedPauseTimeElapsed) < 1L){
			this.view.setMessage("CONTINUING IN 3");
		}else if (TimeUnit.NANOSECONDS.toSeconds(this.estimatedPauseTimeElapsed) < 2L){
			this.view.setMessage("CONTINUING IN 2");
		}else if (TimeUnit.NANOSECONDS.toSeconds(this.estimatedPauseTimeElapsed) < 3L){
			this.view.setMessage("CONTINUING IN 1");
		}else{
			this.view.setMessage("");
			this.pause = false;
		}
		this.updateView();
	}

	/**
	* @brief Stops enemies from being edible after big dot time has passed
	*/
	public void checkBigDotTimer(){
		long bigDotTime = 10L; //10 seconds
		this.estimatedBigDotTimeElapsed = System.nanoTime() - this.startBigDotTime;

		if (TimeUnit.NANOSECONDS.toSeconds(this.estimatedBigDotTimeElapsed) == bigDotTime){
			this.stopBigDot();
		}
	}

	/**
	* @brief Resets the positions of all enemies to their initial location
	*/
	public void resetEnemies() {
		for (Enemy e : this.enemies){
			e.bigDotEaten = false;
			e.resetPosition();
		}
	}
	
	/**
	 * @brief This method is called when the player eats the big dot
	 * @details The enemy's directions will change
	 */
	public void bigDotEaten(){
		this.bigDotOn = true;
		this.startBigDotTime = System.nanoTime();
		for (Enemy e : this.enemies){
			e.bigDotEaten = true;
			e.reverseDirection();
			e.changeSpeed(4);
		}
	}

	/**
	* @brief Accessor for if big dot is on
	* @return The boolean value for whether big dot is on or not
	*/
	public boolean getBigDotOn() {
		return this.bigDotOn;
	}

	/**
	 * @brief Resets enemies after big dot time has completed
	 */
	private void stopBigDot(){
		this.bigDotOn = false;
		for (Enemy e : this.enemies){
			e.bigDotEaten = false;
			e.reverseDirection();
			e.changeSpeed(4);
		}
	}
}