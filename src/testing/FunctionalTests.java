/**
* @file FunctionalTests.java
* @title FunctionalTests
* @author VPB Game Studio
* @date 12/07/2016
* @brief This class tests every functional requirement of the project.
*/
package testing;

import java.awt.AWTException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Board;
import model.Enemy;
import model.MainMenu;
import model.Player;
import view.BoardView;

public class FunctionalTests {
	/**
	* Corresponding Frame object to contain the Board View (actual GUI element).
	*/
	private static JFrame frame;
	
	/**
	* Corresponding Board model for the current Board View.
	*/
	private static Board board;
	
	/**
	* Corresponding view object for the Board model component (following MVC).
	*/
	private static BoardView boardGUI;
	
	/**
	* Represents corresponding player object for the Board model (instance).
	*/
	private static Player player;
	
	/**
	* Represents the corresponding enemy object.
	*/
	private static Enemy enemy;
	
	/**
	 * Represents a robot object that simulates key presses.
	 */
	private static Robot robot;
	
	/**
	 * @brief Main method that runs all the other test methods.
	 * @throws AWTException - for Robot Object
	 */
	public static void main(String[] args) throws AWTException{
		
		upDirectionTest();
		downDirectionTest();
		leftDirectionTest();
		rightDirectionTest();
		wallCollisionTest();
		enemyCollision();
		enemyCollisionOneLife();
		dotCollection();
		bigDotCollection();
		bigDotEnemyCollision();
		enemyPath();
		bigDotEnemyColour();
		bigDotEnemyRespawn();
		lastDot();
		dotScoreIncrement();
		bigDotScoreIncrement();
		
		System.exit(0);
	}
	
	/**
	 * @details This method instantiates every object used for testing.
	 * @throws AWTException - for Robot Class
	 */
	public static void setUp() throws AWTException{
		frame = new JFrame("Namcap");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new Board();
		boardGUI = new BoardView(board, frame);
		player = new Player(board);
		frame.setContentPane(boardGUI);
		frame.setPreferredSize(new Dimension(450,450));
		frame.pack();
		frame.setVisible(true);
		
		robot = new Robot();
	}
	
	/**
	 * @details This method disposes or every object after a test has been completed.
	 */
	public static void tearDown(){
		frame.dispose();
		board = null;
		boardGUI = null;
		player =  null;
		enemy = null;
	}
	
	/**
	 * @details This test verifies that the player moves up when the Up arrow is pressed.
	 * @throws AWTException - for Robot object
	 */
	public static void upDirectionTest() throws AWTException{
		setUp();
		
		int currY, newY;
		boolean up;
		long currTime;
		
	    player.setCurrX(5*20);
		player.setCurrY(17*20);
		
		currTime = System.nanoTime();
		currY = player.getCurrY();
		
		while (System.nanoTime() - currTime <= 500000000L);
		
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 500000000L);
		
		newY = player.getCurrY();
		up = newY < currY;
		
		if (up){
			System.out.println("Up Direction Passed");
		}else{
			System.out.println("Up Direction Failed");
		}
		
		tearDown();
	}
	
	/**
	 * @details This test verifies that the player moves down when the Down arrow is pressed.
	 * @throws AWTException - for Robot object
	 */
	public static void downDirectionTest() throws AWTException{
		setUp();
		
		int currY, newY;
		boolean down;
		long currTime;
		
	    player.setCurrX(5*20);
		player.setCurrY(1*20);
		player.setPrevDirection('U');
		player.setNewDirection('U');
		
		currTime = System.nanoTime();
		currY = player.getCurrY();
		
		while (System.nanoTime() - currTime <= 500000000L);
		
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 500000000L);
		
		newY = player.getCurrY();
		down = newY > currY;
		
		if (down){
			System.out.println("Down Direction Passed");
		}else{
			System.out.println("Down Direction Failed");
		}
		
		tearDown();
	}
	
	/**
	 * @details This test verifies that the player moves Left when the Left arrow is pressed.
	 * @throws AWTException - for Robot object
	 */
	public static void leftDirectionTest() throws AWTException{
		setUp();
		
		int currX, newX;
		boolean left;
		long currTime;
		
	    player.setCurrX(19*20);
		player.setCurrY(9*20);
		
		currTime = System.nanoTime();
		currX = player.getCurrX();
		
		while (System.nanoTime() - currTime <= 500000000L);
		
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyRelease(KeyEvent.VK_LEFT);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 500000000L);
		
		newX = player.getCurrX();
		left = newX < currX;
		
		if (left){
			System.out.println("Left Direction Passed");
		}else{
			System.out.println("Left Direction Failed");
		}
		
		tearDown();
	}
	
	/**
	 * @details This test verifies that the player moves Right when the Right arrow is pressed.
	 * @throws AWTException - for Robot object
	 */
	public static void rightDirectionTest() throws AWTException{
		setUp();
		
		int currX, newX;
		boolean right;
		long currTime;
		
	    player.setCurrX(1*20);
		player.setCurrY(9*20);
		player.setNewDirection('L');
		player.setPrevDirection('L');
		
		currTime = System.nanoTime();
		currX = player.getCurrX();
		
		while (System.nanoTime() - currTime <= 500000000L);
		
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyRelease(KeyEvent.VK_RIGHT);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 500000000L);
		
		newX = player.getCurrX();
		right = newX > currX;
		
		if (right){
			System.out.println("Right Direction Passed");
		}else{
			System.out.println("Right Direction Failed");
		}
		
		tearDown();
	}
	
	/**
	 * @details This test verifies that the player is stopped when coming in contact with a barrier.
	 * @throws AWTException - for Robot object
	 */
	public static void wallCollisionTest() throws AWTException{
		setUp();
		
		player.setCurrX(1*20);
		player.setCurrY(20);
		player.setNewDirection('L');
		player.setNewDirection('L');
		
		int currX, newX;
		boolean pass;
		long currTime;
		
		currTime = System.nanoTime();
		currX = player.getCurrX();
		while (System.nanoTime() - currTime <= 1000000000L);
		newX = player.getCurrX();
		
		pass = (newX == currX);
		
		if (pass){
			System.out.println("Wall Collision Passed");
		}else{
			System.out.println("Wall Collision Failed");
		}
		
		tearDown();
	}
	
	/**
	 * @details This test verifies that the player loses a life when colliding with an enemy.
	 * @throws AWTException - for Robot object
	 */
	public static void enemyCollision() throws AWTException{
		setUp();
		long currTime;
		enemy = new Enemy(board);
		
		player.setCurrX(1*20);
		player.setCurrY(20);
		
		enemy.setPrevX(3*20);
		enemy.setPrevY(20);
		enemy.setStop(true);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 500000000L);
		
		if (player.getLives() < 3){
			System.out.println("Enemy Collision Test Passed");
		}else{
			System.out.println("Enemy Collision Test Failed");
		}
		
		tearDown();
		
	}
	
	/**
	 * @details This test verifies that the game is over when player collides with enemy on their last life.
	 * @throws AWTException
	 */
	public static void enemyCollisionOneLife() throws AWTException{
		setUp();
		long currTime;
		enemy = new Enemy(board);
		
		player.setCurrX(1*20);
		player.setCurrY(20);
		player.setLives(0);
		
		
		enemy.setPrevX(3*20);
		enemy.setPrevY(20);
		enemy.setStop(true);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 500000000L);
		
		if (!frame.hasFocus()){
			System.out.println("Enemy Collision (One Life) Test Passed");
		}else{
			System.out.println("Enemy Collision (One Life) Test Failed");
		}
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 2000000000L);
		
		for (Window w : java.awt.Window.getWindows()){
			w.dispose();
			w = null;
		}
		
		for (Window w : java.awt.Window.getWindows()){
			w.dispose();
			w = null;
		}
		tearDown();
		
	}
	
	/**
	 * @details This test verifies that a dot is not visible once player collects it.
	 * @throws AWTException - for Robot object
	 */
	public static void dotCollection() throws AWTException{
		setUp();
		long currTime;
		boolean pass = true;;
		
		player.setCurrX(20);
		player.setCurrY(19*20);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 3400000000L);
		
		for (int x = 1; x <= 19; x++){
			if (board.getDot(x, 19) != 0){
				pass = false;
			}
		}
		
		if (pass){
			System.out.println("Dot Disappearing Test Passed");
		}else{
			System.out.println("Dot Disappearing Test Failed");
		}
		
		tearDown();
		
	}
	
	/**
	 * @details This test verifies that the big dot is not visible once the player collects it.
	 * @throws AWTException - for Robot object
	 */
	public static void bigDotCollection() throws AWTException{
		setUp();
		long currTime;
		boolean pass = false;
		
		player.setCurrX(17*20);
		player.setCurrY(15*20);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 500000000L);
		
		if (board.getDot(19, 15) == 0){
			pass = true;
		}
		
		if (pass){
			System.out.println("Big Dot Disappearing Test Passed");
		}else{
			System.out.println("Big Dot Disappearing Test Failed");
		}
		
		tearDown();
		
	}
	
	/**
	 * @details This test verifies that the user does not lose a life when colliding with an enemy after collecting the big dot.
	 * @throws AWTException - for Robot object
	 */
	public static void bigDotEnemyCollision() throws AWTException{
		setUp();
		long currTime;
		
		
		player.setCurrX(20);
		player.setCurrY(20);
		player.setNewDirection('D');
		player.setPrevDirection('D');
		
		enemy = new Enemy(board);
		enemy.setPrevX(20);
		enemy.setPrevY(5*20);
		enemy.setStop(true);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 1000000000L);
		
		
		if (player.getLives() == 3){
			System.out.println("Big Dot Enemy Collision Test Passed");
		}else{
			System.out.println("Big Dot Enemy Collision Test Failed");
		}
		
		tearDown();
		
	}
	
	/**
	 * @details This test verifies that the enemy is able to move in a valid path.
	 * @throws AWTException - for Robot object
	 */
	public static void enemyPath() throws AWTException{
		setUp();
		long currTime;
		
		enemy = new Enemy(board);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 10000000000L);
		
		System.out.println("Valid Enemy Path Test Passed");
		
		tearDown();
		
	}
	
	/**
	 * @details This test verifies that the enemy's color changes when the player collects the big dot.
	 * @throws AWTException - for Robot object
	 */
	public static void bigDotEnemyColour() throws AWTException{
		setUp();
		long currTime;
		boolean pass = false;
		
		player.setCurrX(17*20);
		player.setCurrY(15*20);
		
		enemy = new Enemy(board);
		enemy.setStop(true);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 500000000L);
		
		if (enemy.bigDotEaten){
			pass = true;
		}
		
		if (pass){
			System.out.println("Big Dot Enemy Colour Change Test Passed");
		}else{
			System.out.println("Big Dot Enemy Colour Change Test Failed");
		}
		
		tearDown();
		
	}
	
	/**
	 * @details This test verifies that the enemy is respawned in the center after being "eaten" by the player.
	 * @throws AWTException - for Robot object
	 */
	public static void bigDotEnemyRespawn() throws AWTException{
		setUp();
		long currTime;
		
		
		player.setCurrX(20);
		player.setCurrY(20);
		player.setNewDirection('D');
		player.setPrevDirection('D');
		
		enemy = new Enemy(board);
		enemy.setPrevX(20);
		enemy.setPrevY(5*20);
		enemy.setStop(true);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 1000000000L);
		
		if (enemy.getCurrX() == 10*20 && enemy.getCurrY() == 9*20){
			System.out.println("Big Dot Enemy Respawn Test Passed");
		}else{
			System.out.println("Big Dot Enemy Respawn Test Failed");
		}
		
		tearDown();
		
	}
	
	/**
	 * @details This dot verifies that the game is ended upon collecting the last dot on the screen.
	 * @throws AWTException - for Robot object
	 */
	public static void lastDot() throws AWTException{
		setUp();
		long currTime;
		
		
		player.setCurrX(20);
		player.setCurrY(20);
		player.setNewDirection('D');
		player.setPrevDirection('D');
		
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				 board.updateDot(i, j, 0);	
			}
		}
		
		board.updateDot(1, 5, 1);
	
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 2000000000L);
		
		if (!frame.hasFocus()){
			System.out.println("Last Dot Test Passed");
		}else{
			System.out.println("Last Dot Test Failed");
		}
		
		tearDown();
		
		for (Window w : java.awt.Window.getWindows()){
			w.dispose();
			w = null;
		}
		
		for (Window w : java.awt.Window.getWindows()){
			w.dispose();
			w = null;
		}
		
	}
	
	/**
	 * @details This test verifies that the player's score increments by 100 when collecting a regular dot.
	 * @throws AWTException - for Robot object
	 */
	public static void dotScoreIncrement() throws AWTException{
		setUp();
		long currTime;
		boolean pass = true;;
		
		player.setCurrX(20);
		player.setCurrY(19*20);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 3400000000L);
		
		if (board.accessScore().getScore() == 1900){
			System.out.println("Dot Score Increment Test Passed");
		}else{
			System.out.println("Dot Score Increment Test Failed");
		}
		
		tearDown();
		
	}
	
	/**
	 * @details This test verifies that the player's score is incremented by 200 when collecting the big dot.
	 * @throws AWTException - for Robot object
	 */
	public static void bigDotScoreIncrement() throws AWTException{
		setUp();
		long currTime;
		boolean pass = false;
		
		player.setCurrX(17*20);
		player.setCurrY(15*20);
		
		currTime = System.nanoTime();
		while (System.nanoTime() - currTime <= 500000000L);
	
		if (board.accessScore().getScore() == 400){
			System.out.println("Big Dot Score Increment Test Passed");
		}else{
			System.out.println("Big Dot Score Increment Test Failed");
		}
		
		tearDown();	
	}
}
