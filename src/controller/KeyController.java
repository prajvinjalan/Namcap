package controller;

import java.awt.event.*;
import javax.swing.*;

import model.Board;

public class KeyController implements KeyListener{

	private Board board;
	
	public KeyController(Board board){
		this.board = board;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			this.board.changePlayerDirection('L');
			break;
		case KeyEvent.VK_RIGHT:
			this.board.changePlayerDirection('R');
			break;
		case KeyEvent.VK_UP:
			this.board.changePlayerDirection('U');
			break;
		case KeyEvent.VK_DOWN:
			this.board.changePlayerDirection('D');
			break;
		}
		this.board.updateView();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
