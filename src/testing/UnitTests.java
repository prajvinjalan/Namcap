/**
* @file UnitTests.java
* @title UnitTests
* @author VPB Game Studio
* @date 12/07/2016
* @brief This class runs JUnit test cases for Namcap's Unit Tests.
*/
package testing;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.*;
import view.*;

public class UnitTests {

	/**
	* JFrame objects (menu and game windows) that are tested for and used in other tests.
	*/
	private static JFrame mainMenuFrame, boardFrame;
	/**
	* Main Menu's model whose functionality gets tested. 
	*/
	private static MainMenu menu;
	/**
	* Main Menu's view whose functionality gets tested.
	*/
	private static MainMenuView menuGUI;
	/**
	* Board's model whose functionality gets tested (this model is also used for multiple other tests). 
	*/
	private static Board board;
	/**
	* View of the board whose functionality gets tested (also the game map that is set up for multiple other tests). 
	*/
	private static BoardView boardGUI;
	/**
	* ArrayList that holds all the points of the barriers on the map
	*/
	private static ArrayList<int[][]> polyPoints;
	/**
	* Message containing test results that is written to a file (initialized with a heading) 
	*/
	private static String message = "UNIT TEST RESULTS\n\n";
	
	/**
	* @brief Occurs after each test case
	* @details Nullifies all fields in this class (essentially reseting the game environment) and disposes of open frames. A new line is also appended to "message" for output file formatting.
	*/
	@After
	public void tearDown() throws Exception {
		message += "\n";
		for (Field f : this.getClass().getFields()){
			f = null;
		}
		
		for (Frame f : Frame.getFrames()){
			f.dispose();
		}
	}
	
	/**
	* @brief Occurs after all test cases have run
	* @details After the completion of every unit test, the final message is written to an output file. 
	*/
	@AfterClass
	public static void finalMessage() {
		writeToFile(message);
	}
	
	/**
	* @brief File writing method
	* @details Final message is written to a file called "unit_test_results"
	* @param s - Message being written to file
	*/
	public static void writeToFile(String s){
		try{
			File f = new File("unit_test_results");
			FileWriter writer = new FileWriter(f);
			writer.append(s);
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	* @brief Creates the main menu objects to be used for testing. 
	*/
	public static void createMenu() {
		mainMenuFrame = new JFrame("Namcap");
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menu = new MainMenu();
		menuGUI = new MainMenuView(menu, mainMenuFrame);
		mainMenuFrame.setContentPane(menuGUI);
		mainMenuFrame.setPreferredSize(new Dimension(450,620));
		mainMenuFrame.pack();
		mainMenuFrame.setVisible(true);
	}
	
	/**
	* @brief Unit Test 1 to test Game Start from main menu
	* @details Menu is created and startGame method is run - test checks whether a new frame was opened. 
	*/
	@Test
	public void testGameStart() {
		createMenu();
		menu.startGame();
		for (Frame f : Frame.getFrames()){
			if (f.isVisible()){
				JFrame fr = (JFrame)f;
				try{
					assertEquals(4, fr.getContentPane().getComponentCount());
					message += "Test:\t UT1 - Game Start\nResult:\t Test succeeded.";
				} catch (AssertionError e){
					message += "Test:\t UT1 - Game Start\nResult:\t Test failed.\nError:\t " + e.toString();
				}
			}
		}
	}

	/**
	* @brief Unit Test 2 to test Player's starting X position
	* @details Board is created and initialized with a player - test checks whether player's starting X coordinate is at 200.
	*/
	@Test
	public void testPlayerStartX() {
		createBoard();
		Player p = new Player(board);
		
		try{
			assertEquals(200, board.getPlayer().getCurrX());
			message += "Test:\t UT2 - Player X\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT2 - Player X\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	/**
	* @brief Unit Test 3 to test Player's starting Y position
	* @details Board is created and initialized with a player - test checks whether player's starting Y coordinate is at 300. 
	*/
	@Test
	public void testPlayerStartY() {
		createBoard();
		Player p = new Player(board);
		
		try{
			assertEquals(300, board.getPlayer().getCurrY());
			message += "Test:\t UT3 - Player Y\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT3 - Player Y\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	/**
	* @brief Unit Test 4 to test Player's starting direction
	* @details Board is created and initialized with a player - test checks whether the player starts facing right. 
	*/
	@Test
	public void testPlayerStartDirection() {
		createBoard();
		Player p = new Player(board);
		
		try{
			assertEquals('R', board.getPlayer().getCurrDirection());
			message += "Test:\t UT4 - Player Start Direction\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT4 - Player Start Direction\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	/**
	* @brief Unit Test 5 to test Player's collision with an enemy
	* @details Board is created and initialized with a player and enemy - test places them next to each other and checks whether the checkCollision method can detect the collision. 
	*/
	@Test
	public void testPlayerEnemyCollision() {
		createBoard();
		Player p = new Player(board);
		Enemy en = new Enemy(board);
		
		p.setCurrX(200);
		p.setCurrY(300);
		en.setCurrX(185);
		en.setCurrY(300);
		board.getPlayer().checkCollision();
		
		try{
			assertEquals(2, board.getPlayer().getLives());
			message += "Test:\t UT5 - Player Enemy Collision\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT5 - Player Enemy Collision\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	/**
	* @brief Unit Test 6 to test Player's collision with a dot
	* @details Board is created and initialized with a player - test places player next to a dot and checks whether the checkDot method can detect the collision.  
	*/
	@Test
	public void testPlayerDotCollision() {
		createBoard();
		Player p = new Player(board);
		
		p.setCurrX(180);
		p.setCurrY(300);
		board.getPlayer().checkDot();
		
		try{
			assertEquals(100, board.accessScore().getScore());
			assertEquals(0, board.getDot(9, 15));
			message += "Test:\t UT6 - Player Dot Collision\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT6 - Player Dot Collision\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	@Test
	public void testEndGame() {
		createBoard();
		Player p = new Player(board);
		
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				board.updateDot(i, j, 0);
			}
		}
		board.updateDot(9,15,1);
		p.setCurrX(180);
		p.setCurrY(300);
		//board.getPlayer().checkDot();
	}
	
	/**
	* @brief Unit Test 8 to test Player's collision with a barrier
	* @details Board is created and initialized with a player - test checks every position next to the player for barriers. 
	*/
	@Test
	public void testPlayerBarrierCollision() {
		createBoard();
		Player p = new Player(board);
		Player testP = board.getPlayer();
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				System.out.println(i);
			}
		} 
		
		//p.setCurrX(200);
		//p.setCurrY(300);
		
		try{
			assertEquals(false, testP.isBarrier(testP.getCurrX() - 4, testP.getCurrY())); //L
			assertEquals(false, testP.isBarrier(testP.getCurrX() + 20, testP.getCurrY())); //R
			assertEquals(true, testP.isBarrier(testP.getCurrX(), testP.getCurrY() - 4)); //U
			assertEquals(true, testP.isBarrier(testP.getCurrX(), testP.getCurrY() + 20)); //D
			message += "Test:\t UT8 - Player Barrier Collision\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT8 - Player Barrier Collision\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	/**
	* @brief Unit Test 9 to test all barriers on the map
	* @details Board is created and initialized with barriers - test checks every position manually for appropriate barrier locations. 
	*/
	@Test
	public void testAllBarriers() {
		createBoard();
		setupBarriers();
		
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				System.out.println(i);
			}
		}
		
		try{
			for (int[][] x : polyPoints){
				for (int i = 0; i < x.length; i++){
					assertEquals(true, board.getBarrier(x[i][0], x[i][1]));
				}
			}
			message += "Test:\t UT9 - All Map Barriers\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT9 - All Map Barriers\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	/**
	* @brief Unit Test 10 to test an individual barrier update on the map
	* @details Board is created - test checks a position for a barrier, updates it, then checks whether it was updated. 
	*/
	@Test
	public void testIndividualBarrier() {
		createBoard();
		
		try{
			assertEquals(false, board.getBarrier(10, 15));
			board.updateBarrier(10, 15);
			assertEquals(true, board.getBarrier(10, 15));
			message += "Test:\t UT10 - Individual Map Barriers\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT10 - Individual Map Barriers\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	/**
	* @brief Unit Test 11 to test all dots on the map
	* @details Board is created and initialized with barriers and dots - test checks every position manually for appropriate dot locations 
	*/
	@Test
	public void testAllDots(){
		createBoard();
		setupBarriers();
		
		ArrayList<int[]> allDots = new ArrayList<int[]>();
		
		for (int[][] x : polyPoints){
			for (int i = 0; i < x.length; i++){
				if (board.getBarrier(x[i][0], x[i][1]) == true){
					allDots.add(new int[]{x[i][0], x[i][1]});
				}
			}
		}
		
		int[][] noDots = {{9,6},{11,6},{7,7},{8,7},{9,7},
				{10,7},{11,7},{12,7},{13,7},{7,8},{10,8},{13,8},
				{6,9},{7,9},{9,9},{10,9},{11,9},{13,9},{14,9},{7,10},
				{13,10},{7,11},{8,11},{9,11},{10,11},{11,11},{12,11},
				{13,11},{7,12},{13,12},{1,9},{2,9},{3,9},{4,9},
				{16,9},{17,9},{18,9},{19,9}};
		
		int index = 0;
		for (int[] x : allDots){
			for (int i = 0; i < noDots.length; i++){
				if (x[0] == noDots[i][0] && x[1] == noDots[i][1]){
					allDots.remove(index);
				}
			}
			index++;
		}
		
		try{
			for (int[] x : allDots){
				assertNotEquals(0, board.getDot(x[0], x[1]));
			}
			message += "Test:\t UT11 - All Map Dots\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT11 - All Map Dots\nResult:\t Test failed.\nError:\t " + e.toString();
		}
		
	}
	
	/**
	* @brief Unit Test 12 to test an individual dot update on the map
	* @details Board is created - test checks a position for a dot, updates it, then checks whether it was updated. 
	*/
	@Test
	public void testIndividualDot() {
		createBoard();
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				System.out.println(i);
			}
		}
		
		try{
			assertEquals(0, board.getDot(0, 0));
			board.updateDot(0, 0, 1);
			assertEquals(1, board.getDot(0, 0));
			message += "Test:\t UT12 - Individual Map Dots\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT12 - Individual Map Dots\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	/**
	* @brief Unit Test 13 to test a score update for the game
	* @details Board is created - test checks the current score, increments it, then checks whether score increased. 
	*/
	@Test
	public void testAddScore() {
		createBoard();
		
		try{
			assertEquals(0, board.accessScore().getScore());
			board.accessScore().addScore(1000);
			assertEquals(1000, board.accessScore().getScore());
			message += "Test:\t UT13 - Score Addition\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT13 - Score Addition\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	/**
	* @brief Unit Test 14 to test high score update for the game
	* @details Board is created - test updates the high score file, increases the current score for the board, and checks whether the high score remained as last updated (high score was successfully read from the file)
	*/
	@Test
	public void testHighScore() {
		createBoard();
		
		try{
			board.accessScore().addScore(16000);
			board.accessScore().updateHighScore();
			board.accessScore().addScore(4000);
			board.accessScore().readHighScore();
			assertEquals(16000, board.accessScore().getHighScore());
			message += "Test:\t UT14 - High Score Functionality\nResult:\t Test succeeded.";
		} catch (AssertionError e){
			message += "Test:\t UT14 - High Score Functionality\nResult:\t Test failed.\nError:\t " + e.toString();
		}
	}
	
	/**
	* @brief Creates the board (model and view) to be used for testing 
	*/
	public static void createBoard(){
		boardFrame = new JFrame("Namcap");
		boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new Board();
		boardGUI = new BoardView(board, boardFrame);
		boardFrame.setContentPane(boardGUI);
		boardFrame.setPreferredSize(new Dimension(450,450));
		boardFrame.pack();
		boardFrame.setVisible(true);
	}
	
	/**
	* @brief Sets up all barriers on the map in an ArrayList of coordinates. 
	*/
	public static void setupBarriers(){
		polyPoints = new ArrayList<int[][]>();
		
		polyPoints.add(new int[][]{{0,0},{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},{8,0},{9,0},{10,0},{11,0},{12,0},{13,0},{14,0},{15,0},{16,0},{17,0},{18,0},{19,0},{20,0}}); //top border
		polyPoints.add(new int[][]{{0,20},{1,20},{2,20},{3,20},{4,20},{5,20},{6,20},{7,20},{8,20},{9,20},{10,20},{11,20},{12,20},{13,20},{14,20},{15,20},{16,20},{17,20},{18,20},{19,20},{20,20}}); //bottom border
		polyPoints.add(new int[][]{{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{0,13},{0,14},{0,15},{0,16},{0,17},{0,18},{0,19},{0,20}}); //left border
		polyPoints.add(new int[][]{{20,1},{20,2},{20,3},{20,4},{20,5},{20,6},{20,7},{20,8},{20,9},{20,10},{20,11},{20,12},{20,13},{20,14},{20,15},{20,16},{20,17},{20,18},{20,19},{20,20}}); //right border
		polyPoints.add(new int[][]{{6,4},{6,5},{6,6},{6,7},{6,8},{7,6},{8,6}}); //left sideways T
		polyPoints.add(new int[][]{{14,4},{14,5},{14,6},{14,7},{14,8},{13,6},{12,6}}); //right sideways T
		polyPoints.add(new int[][]{{8,4},{9,4},{10,4},{11,4},{12,4},{10,5},{10,6}}); //top upright T
		polyPoints.add(new int[][]{{8,12},{9,12},{10,12},{11,12},{12,12},{10,13},{10,14}}); //middle upright T
		polyPoints.add(new int[][]{{8,16},{9,16},{10,16},{11,16},{12,16},{10,17},{10,18}}); //bottom upright T
		polyPoints.add(new int[][]{{6,16},{6,17},{2,18},{3,18},{4,18},{5,18},{6,18},{7,18},{8,18}}); //left upside down T
		polyPoints.add(new int[][]{{14,16},{14,17},{12,18},{13,18},{14,18},{15,18},{16,18},{17,18},{18,18}}); //right upside down T
		polyPoints.add(new int[][]{{2,14},{3,14},{4,14},{4,15},{4,16}}); //left upside down L
		polyPoints.add(new int[][]{{18,14},{17,14},{16,14},{16,15},{16,16}}); //right upside down L
		polyPoints.add(new int[][]{{9,8},{8,8},{8,9},{8,10},{9,10},{10,10},{11,10},{12,10},{12,9},{12,8},{11,8}}); //left upside down T
		//rectangles
		polyPoints.add(new int[][]{{2,2},{3,2},{4,2},{6,2},{7,2},{8,2},{12,2},{13,2},{14,2},{16,2},{17,2},{18,2}});
		polyPoints.add(new int[][]{{2,4},{3,4},{4,4},{6,14},{7,14},{8,14},{12,14},{13,14},{14,14},{16,4},{17,4},{18,4}});
		polyPoints.add(new int[][]{{10,1},{10,2},{6,10},{6,11},{6,12},{14,10},{14,11},{14,12}});
		polyPoints.add(new int[][]{{1,16},{2,16},{18,16},{19,16}});
		polyPoints.add(new int[][]{{1,6},{2,6},{3,6},{4,6},{1,7},{2,7},{3,7},{4,7},{1,8},{2,8},{3,8},{4,8}});
		polyPoints.add(new int[][]{{1,10},{2,10},{3,10},{4,10},{1,11},{2,11},{3,11},{4,11},{1,12},{2,12},{3,12},{4,12}});
		polyPoints.add(new int[][]{{16,6},{17,6},{18,6},{19,6},{16,7},{17,7},{18,7},{19,7},{16,8},{17,8},{18,8},{19,8}});
		polyPoints.add(new int[][]{{16,10},{17,10},{18,10},{19,10},{16,11},{17,11},{18,11},{19,11},{16,12},{17,12},{18,12},{19,12}});
	}
}
