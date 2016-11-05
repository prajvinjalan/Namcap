package view;

import java.awt.*;
import javax.swing.*;

import controller.ButtonController;
import model.MainMenu;

public class MainMenuView extends JPanel{

	private MainMenu menuModel;
	private JFrame frame;
	private JPanel imagePanel, menuPanel;
	private JButton startGame;
	private BorderLayout layout;
	private JLabel imageLabel;
	
	public MainMenuView(MainMenu menuModel, JFrame frame){
		this.frame = frame;
		this.menuModel = menuModel;
		this.menuModel.setView(this);
		this.imagePanel = new JPanel();
		this.menuPanel = new JPanel();
		this.startGame = new JButton("Start Game");
		this.layout = new BorderLayout();
		this.imageLabel = new JLabel(new ImageIcon("assets/ghost_red.png"));
		this.layoutView();
		this.registerControllers();
	}
	
	private void layoutView(){
		this.setLayout(layout);
		
		this.imagePanel.add(this.imageLabel);
		this.menuPanel.add(this.startGame);
		
		this.add(imageLabel, BorderLayout.NORTH);
		this.add(menuPanel, BorderLayout.SOUTH);
	}
	
	private void registerControllers(){
		ButtonController startGameClicked = new ButtonController(this.menuModel, this.startGame);
		this.startGame.addActionListener(startGameClicked);
	}
	
	public void setFrameInvis(){
		this.frame.setVisible(false);
	}
}
