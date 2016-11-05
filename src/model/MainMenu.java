package model;

import java.awt.Dimension;

import javax.swing.JFrame;

import view.BoardView;
import view.MainMenuView;

public class MainMenu {

	private MainMenuView menuView;
	
	public MainMenu(){
		super();
	}
	
	public void setView(MainMenuView menuView){
		this.menuView = menuView;
	}
	
	public void startGame(){
		this.menuView.setFrameInvis();
		JFrame frame = new JFrame("Namcap");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Board board = new Board();
		BoardView boardGUI = new BoardView(board, frame);
		Player player = new Player(board);
		Ghost ghost = new Ghost(board);
		frame.setContentPane(boardGUI);
		frame.setPreferredSize(new Dimension(437,450));
		frame.pack();
		frame.setVisible(true);
	}
}
