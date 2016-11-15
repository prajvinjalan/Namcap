/**
* @file BoardView.java
* @title BoardView
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class is the view (display) for the game board
* @details This class extends JPanel to create a board using paint methods that the game is played on.
*/
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

import controller.KeyController;
import model.Board;
import model.MainMenu;
import model.Ghost;


public class BoardView extends JPanel{

	/**
	* Corresponding Board model for the current Board View.
	*/
	Board board;
	/**
	* Represents a grid square size parameter (in terms of pixels).
	*/
	int sq;
	/**
	* Represent the GUI sprites used to display each player position and the ghost.
	*/
	Image playerLeft, playerRight, playerUp, playerDown, ghost;
	/**
	* The frame timer for the game; used to set the frequency of BoardView repainting (update).
	*/
	javax.swing.Timer frameTimer;
	/**
	* Represent the text label and corresponding number labels for both score and high score values
	* shown on screen. Points labels are number values and score labels are the corresponding text.
	*/
	JLabel scoreLabel, pointsLabel, highScoreLabel, highPointsLabel;
	/**
	* Corresponding Frame object to contain the Board View (actual GUI element).
	*/
	JFrame frame;

	/**
	* @brief Constructor for the board display
	* @details This method connects the board display to its model, sets up the images for the player and ghost, and starts a timer that controls the frames of the game.
	* @param board - Primary model (in this case Board object) that the display connects to.
	* @param frame - Frame object in which the board model is displayed (GUI).
	*/
	public BoardView(Board board, JFrame frame){
		this.board = board;
		this.board.setView(this);
		this.setFocusable(true);
		this.frame = frame;
		//grid is 21x21 (19x19 with extra this.squares for thin borders)
		this.sq = 20; //square size on the grid
		
		playerLeft = Toolkit.getDefaultToolkit().getImage(BoardView.class.getResource("/assets/player_left.jpeg"));
		playerRight = Toolkit.getDefaultToolkit().getImage(BoardView.class.getResource("/assets/player_right.jpeg"));
		playerUp = Toolkit.getDefaultToolkit().getImage(BoardView.class.getResource("/assets/player_up.jpeg"));
		playerDown = Toolkit.getDefaultToolkit().getImage(BoardView.class.getResource("/assets/player_down.jpeg"));
		this.ghost = Toolkit.getDefaultToolkit().getImage(BoardView.class.getResource("/assets/ghost_red.png"));
		
		this.registerControllers();
		/*
		this.playerLeft = Toolkit.getDefaultToolkit().getImage("assets/player_left.jpeg");
		this.playerRight = Toolkit.getDefaultToolkit().getImage("assets/player_right.jpeg");
		this.playerUp = Toolkit.getDefaultToolkit().getImage("assets/player_up.jpeg");
		this.playerDown = Toolkit.getDefaultToolkit().getImage("assets/player_down.jpeg");
		this.ghost = Toolkit.getDefaultToolkit().getImage("assets/ghost_red.png");
		*/
		this.frameTimer = new javax.swing.Timer(30, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				stepFrame();
			}
		});
		
		
		this.frameTimer.start();
	
		scoreLabel = new JLabel("SCORE:");
		scoreLabel.setLocation(100,0);
		scoreLabel.setForeground(Color.white);
		this.add(scoreLabel);


		pointsLabel = new JLabel(this.board.accessScore().getScore() + "");
		pointsLabel.setLocation(150,0);
		pointsLabel.setForeground(Color.white);
		this.add(pointsLabel);

		highScoreLabel = new JLabel("HIGHSCORE:");
		highScoreLabel.setLocation(100,50);
		highScoreLabel.setForeground(Color.white);
		this.add(highScoreLabel);

		highPointsLabel = new JLabel(this.board.accessScore().getHighScore() + "");
		highPointsLabel.setLocation(150,10);
		highPointsLabel.setForeground(Color.white);
		this.add(highPointsLabel);


	}

	/**
	* @brief Registers a controller for key presses by the user (adds a listener)
	*/
	private void registerControllers() {
		KeyController keysClicked = new KeyController(this.board);
		this.addKeyListener(keysClicked);
	}

	/**
	* @brief Calls a step of the game frame
	* @details Directs board to move both the player and the ghost, and calls the repaint method to update the view.
	*/
	public void stepFrame(){
		this.board.getPlayer().move();
		for (Ghost g : this.board.getGhost()){
			g.ghostMove();
		}
		this.repaint();
		this.board.checkTimeRunning();
	}

	/**
	* @brief Draws the map (level) onto the board
	* @details Sets up all the barriers on the map grid (drawn through graphics) and calls a method to update the barriers after initialization.
	* @param g - Graphics object needed to draw the appropriate pieces of the board on the GUI.
	*/
	public void drawMap(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 420, 420);
		g.setColor(Color.yellow);
		for (int i = 0; i < 420; i += this.sq){
			for (int j = 0; j < 420; j+= this.sq){
				//g.drawRect(i, j, this.sq, this.sq);
			}
		}
		
		//draws rectangles onto the map and updates them as barriers
		g.setColor(Color.BLUE);
		g.drawRect(this.sq*2, this.sq*2, this.sq*3, this.sq);
		this.updateRectangleBarriers(2, 2, 3, 1);
		g.drawRect(this.sq*6, this.sq*2, this.sq*3, this.sq);
		this.updateRectangleBarriers(6, 2, 3, 1);
		g.drawRect(this.sq*12, this.sq*2, this.sq*3, this.sq);
		this.updateRectangleBarriers(12, 2, 3, 1);
		g.drawRect(this.sq*16, this.sq*2, this.sq*3, this.sq);
		this.updateRectangleBarriers(16, 2, 3, 1);
		g.drawRect(this.sq*2, this.sq*4, this.sq*3, this.sq);
		this.updateRectangleBarriers(2, 4, 3, 1);
		g.drawRect(this.sq*16, this.sq*4, this.sq*3, this.sq);
		this.updateRectangleBarriers(16, 4, 3, 1);
		g.drawRect(this.sq*6, this.sq*14, this.sq*3, this.sq);
		this.updateRectangleBarriers(6, 14, 3, 1);
		g.drawRect(this.sq*12, this.sq*14, this.sq*3, this.sq);
		this.updateRectangleBarriers(12, 14, 3, 1);
		g.drawRect(this.sq, this.sq*6, this.sq*4, this.sq*3);
		this.updateRectangleBarriers(1, 6, 4, 3);
		g.drawRect(this.sq*16, this.sq*6, this.sq*4, this.sq*3);
		this.updateRectangleBarriers(16, 6, 4, 3);
		g.drawRect(this.sq, this.sq*10, this.sq*4, this.sq*3);
		this.updateRectangleBarriers(1, 10, 4, 3);
		g.drawRect(this.sq*16, this.sq*10, this.sq*4, this.sq*3);
		this.updateRectangleBarriers(16, 10, 4, 3);
		g.drawRect(this.sq, this.sq*16, this.sq*2, this.sq);
		this.updateRectangleBarriers(1, 16, 2, 1);
		g.drawRect(this.sq*18, this.sq*16, this.sq*2, this.sq);
		this.updateRectangleBarriers(18, 16, 2, 1);
		g.drawRect(this.sq*10, this.sq, this.sq, this.sq*2);
		this.updateRectangleBarriers(10, 1, 1, 2);
		g.drawRect(this.sq*6, this.sq*10, this.sq, this.sq*3);
		this.updateRectangleBarriers(6, 10, 1, 3);
		g.drawRect(this.sq*14, this.sq*10, this.sq, this.sq*3);
		this.updateRectangleBarriers(14, 10, 1, 3);
		
		//draws outer rectangles (for borders) onto the map
		g.drawRect(this.sq, this.sq, this.sq*19, this.sq*19);
		g.drawRect(this.sq-5, this.sq-5, this.sq*19+10, this.sq*19+10);
		
		//draws the polygons onto the map 
		g2.drawPolygon(new int[]{this.sq*2, this.sq*5, this.sq*5, this.sq*4, this.sq*4, this.sq*2}, new int[]{this.sq*14, this.sq*14, this.sq*17, this.sq*17, this.sq*15, this.sq*15}, 6);
		g2.drawPolygon(new int[]{this.sq*19, this.sq*16, this.sq*16, this.sq*17, this.sq*17, this.sq*19}, new int[]{this.sq*14, this.sq*14, this.sq*17, this.sq*17, this.sq*15, this.sq*15}, 6);
		
		g2.drawPolygon(new int[]{this.sq*6, this.sq*6, this.sq*7, this.sq*7, this.sq*9, this.sq*9, this.sq*7, this.sq*7}, new int[]{this.sq*4, this.sq*9, this.sq*9, this.sq*7, this.sq*7, this.sq*6, this.sq*6, this.sq*4}, 8);
		g2.drawPolygon(new int[]{this.sq*15, this.sq*15, this.sq*14, this.sq*14, this.sq*12, this.sq*12, this.sq*14, this.sq*14}, new int[]{this.sq*4, this.sq*9, this.sq*9, this.sq*7, this.sq*7, this.sq*6, this.sq*6, this.sq*4}, 8);
		
		g2.drawPolygon(new int[]{this.sq*8, this.sq*8, this.sq*10, this.sq*10, this.sq*11, this.sq*11, this.sq*13, this.sq*13}, new int[]{this.sq*4, this.sq*5, this.sq*5, this.sq*7, this.sq*7, this.sq*5, this.sq*5, this.sq*4}, 8);
		g2.drawPolygon(new int[]{this.sq*8, this.sq*8, this.sq*10, this.sq*10, this.sq*11, this.sq*11, this.sq*13, this.sq*13}, new int[]{this.sq*12, this.sq*13, this.sq*13, this.sq*15, this.sq*15, this.sq*13, this.sq*13, this.sq*12}, 8);
		g2.drawPolygon(new int[]{this.sq*8, this.sq*8, this.sq*10, this.sq*10, this.sq*11, this.sq*11, this.sq*13, this.sq*13}, new int[]{this.sq*16, this.sq*17, this.sq*17, this.sq*19, this.sq*19, this.sq*17, this.sq*17, this.sq*16}, 8);
		
		g2.drawPolygon(new int[]{this.sq*2, this.sq*2, this.sq*9, this.sq*9, this.sq*7, this.sq*7, this.sq*6, this.sq*6}, new int[]{this.sq*18, this.sq*19, this.sq*19, this.sq*18, this.sq*18, this.sq*16, this.sq*16, this.sq*18}, 8);
		g2.drawPolygon(new int[]{this.sq*12, this.sq*12, this.sq*19, this.sq*19, this.sq*15, this.sq*15, this.sq*14, this.sq*14}, new int[]{this.sq*18, this.sq*19, this.sq*19, this.sq*18, this.sq*18, this.sq*16, this.sq*16, this.sq*18}, 8);
		
		g2.drawPolygon(new int[]{this.sq*8, this.sq*8, this.sq*13, this.sq*13, this.sq*11, this.sq*11, this.sq*12, this.sq*12, this.sq*9, this.sq*9, this.sq*10, this.sq*10}, new int[]{this.sq*8, this.sq*11, this.sq*11, this.sq*8, this.sq*8, this.sq*9, this.sq*9, this.sq*10, this.sq*10, this.sq*9, this.sq*9, this.sq*8}, 12);
		
		//adds black lines to places to make the map look finished
		g.setColor(Color.BLACK);
		g.drawLine(this.sq*10, this.sq, this.sq*11, this.sq);
		g.drawLine(this.sq, this.sq*6, this.sq, this.sq*9);
		g.drawLine(this.sq, this.sq*10, this.sq, this.sq*13);
		g.drawLine(this.sq*20, this.sq*6, this.sq*20, this.sq*9);
		g.drawLine(this.sq*20, this.sq*10, this.sq*20, this.sq*13);
		g.drawLine(this.sq, this.sq*16, this.sq, this.sq*17);
		g.drawLine(this.sq*20, this.sq*16, this.sq*20, this.sq*17);
		
		this.updateAbnormalBarriers();
	}
	
	/**
	* @brief Updates rectangular map pieces to be barriers
	* @param x - Starting x-coordinate for the currently handled rectangular barrier.
	* @param y - Starting y-coordinate for the currently handled rectangular barrier.
	* @param width - Width dimension for the currently handled rectangular barrier.
	* @param height - Height dimension for the currently handled rectangular barrier.
	*/
	public void updateRectangleBarriers(int x, int y, int width, int height){
		for (int i = x; i < x + width; i++){
			for (int j = y; j < y + height; j++){
				this.board.updateBarrier(i, j);
			}
		}
	}
	
	/**
	* @brief Updates abnormal map pieces to be barriers (polygons and borders of map)
	* @details Updates all the abnormal pieces manually and directs the board model to update the corresponding barrier points.
	*/
	public void updateAbnormalBarriers(){
		for (int i = 0; i < 21; i++){
			this.board.updateBarrier(i, 0); //top border
			this.board.updateBarrier(i, 20); //bottom border
		}
		
		for (int j = 0; j < 21; j++){
			this.board.updateBarrier(0, j); //left border
			this.board.updateBarrier(20, j); //right border
		}
		
		ArrayList<int[][]> polyPoints = new ArrayList<int[][]>();
		
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
		//polyPoints.add(new int[][]{{10,8}}); //blocks off enemy spawn
		
		this.board.updateBarrier(polyPoints);
	}
	
	/**
	* @brief Draws the dots onto the board
	* @details Sets up all the dots on the map grid (drawn through graphics) and directs the board to update the dots after initialization.
	* @param g - Graphics object needed to draw the appropriate dots on the board (GUI element).
	*/
	public void drawDots(Graphics g){
		this.pointsLabel.setText(this.board.accessScore().getScore() + "");
		Graphics2D g2 = (Graphics2D) g;
		
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				if (this.board.getBarrier(i, j)){
					this.board.updateDot(i, j, 0);
				}
			}
		}
		
		ArrayList<int[][]> dots = new ArrayList<int[][]>();
		dots.add(new int[][]{{9,6},{11,6},{7,7},{8,7},{9,7},
			{10,7},{11,7},{12,7},{13,7},{7,8},{10,8},{13,8},
			{6,9},{7,9},{9,9},{10,9},{11,9},{13,9},{14,9},{7,10},
			{13,10},{7,11},{8,11},{9,11},{10,11},{11,11},{12,11},
			{13,11},{7,12},{13,12},{1,9},{2,9},{3,9},{4,9},
			{16,9},{17,9},{18,9},{19,9}});
		this.board.updateDot(dots);
		
		g.setColor(new Color(255,184,151));
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				if (this.board.getDot(i, j) != 0){
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.fillOval(i*this.sq + 9, j*this.sq + 9, 4, 4);
				}
			}
		}
	}

	/**
	* @brief Draws the player onto the board
	* @details Sets up the player's appropriate image based on their direction.
	* @param g - Graphics object needed to draw the player (GUI element).
	*/
	public void drawPlayer(Graphics g){
		switch(board.getPlayer().getCurrDirection()){
		case 'L':
			g.drawImage(playerLeft, board.getPlayer().getCurrX(), board.getPlayer().getCurrY(), Color.BLACK,null);
			break;
		case 'R':
			g.drawImage(playerRight, board.getPlayer().getCurrX(), board.getPlayer().getCurrY(), Color.BLACK,null);
			break;
		case 'U':
			g.drawImage(playerUp, board.getPlayer().getCurrX(), board.getPlayer().getCurrY(), Color.BLACK,null);
			break;
		case 'D':
			g.drawImage(playerDown, board.getPlayer().getCurrX(), board.getPlayer().getCurrY(), Color.BLACK,null);
			break;
		}
	}
	
	/**
	* @brief Draws the ghosts onto the board
	* @param g - Graphics object needed to draw the ghost (GUI element).
	*/
	public void drawGhost(Graphics g){
		for (Ghost gh : this.board.getGhost()){
			g.drawImage(ghost, gh.getCurrX(), gh.getCurrY(), Color.BLACK, null);
		}
	}	

	public void drawLives(Graphics g){
		for(int i = 1; i <= this.board.getPlayer().getLives(); i++){
			g.drawImage(playerRight, 400 - (i*20), 10, Color.BLACK, null);
		}
		//g.drawImage(playerRight, 400, 10, Color.BLACK, null);
	}

	/**
	* @brief Paint method that calls internal functions to initialize the display
	* @details Calls the super class's paintComponent method (as necessary) and calls internal functions to draw the map, dots, player and ghost.
	* @param g - Graphics object needed to draw the appropriate board (GUI element).
	*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		this.drawMap(g);
		this.drawDots(g);
		this.drawLives(g);
		this.drawPlayer(g);
		this.drawGhost(g);
		
	}
	
	/**
	* @brief Repaints (updates) the appropriate parts of the board
	* @details This class's repaint method that specifically repaints on the player and ghost in order to save processing power (opposed to always redrawing the board for player and ghost movements).
	* @param g - Graphics object needed to draw the appropriate pieces of the board (GUI element).
	*/
	public void repaint(Graphics g){
		//this.drawPlayer(g);
		super.repaint((this.board.getPlayer().getCurrX())-20, (this.board.getPlayer().getCurrY())-20, 80, 80);
		for (Ghost gh : this.board.getGhost()){
			super.repaint((gh.getCurrX())-20, (gh.getCurrY())-20, 80, 80);
			}
		//this.pointsLabel.setText(this.board.accessScore().getScore() + "");
	}

	/**
	* @brief Pauses the game timer
	* @param pauseTime - The amount of time to pause the timer for.
	*/
	public void pauseTimer(long pauseTime){
		long startTime = System.nanoTime();
		long estimatedTimeElapsed = System.nanoTime() - startTime;
		this.frameTimer.stop();
		while (estimatedTimeElapsed < pauseTime){
			//runs this loop until the pauseTime has passed
			estimatedTimeElapsed = System.nanoTime() - startTime;
		}
		this.frameTimer.start();
	}

	/**
	* @brief Ends the game
	* @details Stops the game timer and displays the final message to the player with their score. Goes back to the main menu when the player confirms the dialog box.
	*/
	public void endGame() {
		this.frameTimer.stop();
		String message = "Your final score was: "+this.board.accessScore().getScore()+".";
		
		Object[] options = {"Okay"};
		JOptionPane.showOptionDialog(frame,
		message,
		"Game Over",
		JOptionPane.DEFAULT_OPTION,
		JOptionPane.INFORMATION_MESSAGE,
		null,
		options,
		options[0]);
		
		//go back to main menu
		JFrame frame = new JFrame("Namcap");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainMenu menu = new MainMenu();
		MainMenuView menuGUI = new MainMenuView(menu, frame);
		frame.setContentPane(menuGUI);
		frame.setPreferredSize(new Dimension(450,450));
		frame.pack();
		frame.setVisible(true);
		this.frame.dispose(); //close this frame
	}

	/**
	* @brief Quits the game
	* @details Pauses the game timer and displays an option message to the user asking if they are sure they wish to quit; if they choose yes the game closes, if they choose no the game continues.
	*/
	public void quitGame(){
		this.frameTimer.stop();
		String message = "Are you sure you want to quit the game?";
		int choice = JOptionPane.showConfirmDialog(this.frame,
			message,
			"Quit Game",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.ERROR_MESSAGE);
		
		if (choice == JOptionPane.YES_OPTION){
			System.exit(0);
		}else{
			this.frameTimer.start();
		}
	}

	/**
	* @brief Prompts the user to take a break
	* @details Stops the game timer (pausing the game) and displays a break message to the user. When they confirm it, the game continues and the break timer is reset.
	*/
	public void promptBreak() {
		this.frameTimer.stop();
		String message = "Playing for long periods of time is not\nhealthy so remember to take a break!\nPress 'Okay' to continue the game.";
		
		Object[] options = {"Okay"};
		JOptionPane.showOptionDialog(frame,
		message,
		"Break Time!",
		JOptionPane.DEFAULT_OPTION,
		JOptionPane.INFORMATION_MESSAGE,
		null,
		options,
		options[0]);

		this.frameTimer.start();
		this.board.restartTimer();
	}
}
