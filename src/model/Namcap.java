package model;

import java.awt.Dimension;

import javax.swing.JFrame;

import view.MainMenuView;

public class Namcap {

	public static void main(String[] args){
		JFrame frame = new JFrame("Namcap");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainMenu menu = new MainMenu();
		MainMenuView menuGUI = new MainMenuView(menu, frame);
		frame.setContentPane(menuGUI);
		frame.setPreferredSize(new Dimension(450,450));
		frame.pack();
		frame.setVisible(true);
	}
}
