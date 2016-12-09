//This code follows the Google Java Standards referenced at https://google.github.io/styleguide/javaguide.html
/**
* @file MainMenuView.java
* @title MainMenuView
* @author VPB Game Studio
* @date 11/13/2016
* @brief This class is the view (display) for the main menu
* @details This class extends JPanel to create a main menu using buttons and labels.
*/
package view;

import java.awt.*;
import javax.swing.*;

import controller.ButtonController;
import model.MainMenu;

public class MainMenuView extends JPanel{

	/**
	* Corresponding Main Menu instance model for the Main Menu View.
	*/
	private MainMenu menuModel;
	/**
	* Corresponding Frame object to contain the Main Menu View (actual GUI element).
	*/
	private JFrame frame;
	/**
	* JPanel objects to organize image conten and menu (text) content of the frame.
	*/
	private JPanel imagePanel, menuPanel;
	/**
	* Button object representing the start game button on the Main Menu View.
	*/
	private JButton startGame;
	/**
	* Represents the layout format for the Main Menu View frame.
	*/
	private BorderLayout layout;
	/**
	* Corresponding label for image icron displayed on the Main Menu View.
	*/
	private JLabel imageLabel;

	/**
	* @brief Constructor for the main menu display
	* @details This method connects the main menu display to its model, sets up the image labels 
	* and buttons and calls internal methods for the view's layout and controllers.
	* @param menuModel - Model (in this case MainMenu object) that this display is connects to.
	* @param frame - Frame that the menu display initializes on.
	*/
	public MainMenuView(MainMenu menuModel, JFrame frame){
		this.frame = frame;
		this.menuModel = menuModel;
		this.menuModel.setView(this);
		this.imagePanel = new JPanel();
		this.menuPanel = new JPanel();
		this.startGame = new JButton("Start Game");
		this.layout = new BorderLayout();
		this.imageLabel = new JLabel(new ImageIcon(MainMenuView.class.getResource("/assets/instructions.png")));
		this.layoutView();
		this.registerControllers();
	}

	/**
	* @brief Creates the layout for the menu
	* @details Uses the the BorderLayout to place components and adds them appropriately (buttons and labels).
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
	* @brief Sets the frame of this menu to be invisible (used on game start)
	*/
	public void setFrameInvis(){
		this.frame.setVisible(false);
	}

	/**
	 * @brief Returns the start game button
	 * @return JButton - start game button.
	 */
	public JButton getButton(){
		return this.startGame;
	}
}
