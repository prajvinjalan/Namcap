/**
* @file MainMenuView.java
* @title MainMenuView
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class is the view (display) for the main menu
* @details This class extends JPanel to create a main menu using buttons and labels
*/
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
	/**
	* @brief Constructor for the main menu display
	* @details This method connects this main menu display to its model, sets up the image labels and buttons and calls internal methods for the view's layout and controllers
	* @param menuModel The model (in this case MainMenu object) that this display is connected to
	* @param frame The frame that this menu display is created on
	*/
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
	/**
	* @brief Creates the layout for the menu
	* @details Uses the the BorderLayout to place components and adds them appropriately (buttons and labels)
	*/
	private void layoutView(){
		this.setLayout(layout);
		
		this.imagePanel.add(this.imageLabel);
		this.menuPanel.add(this.startGame);
		
		this.add(imageLabel, BorderLayout.NORTH);
		this.add(menuPanel, BorderLayout.SOUTH);
	}
	/**
	* @brief Registers a controller for the buttons of the menu (adds listener)
	*/
	private void registerControllers(){
		ButtonController startGameClicked = new ButtonController(this.menuModel, this.startGame);
		this.startGame.addActionListener(startGameClicked);
	}
	/**
	* @brief Sets the frame of this menu to be invisible
	*/
	public void setFrameInvis(){
		this.frame.setVisible(false);
	}
}
