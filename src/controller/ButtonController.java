package controller;

import java.awt.event.*;
import javax.swing.*;

import model.MainMenu;

public class ButtonController implements ActionListener{

	private MainMenu menu;
	private JButton button;
	
	public ButtonController(MainMenu menu, JButton button){
		this.menu = menu;
		this.button = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.button.getText() == "Start Game"){
			this.menu.startGame();
		}
	}

}
