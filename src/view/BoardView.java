package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import model.Board;

public class BoardView extends JPanel {

	Board board;
	int sq;
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Six Men's Morris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Board board = new Board();
		BoardView boardGUI = new BoardView(board);
		frame.setContentPane(boardGUI);
		frame.setPreferredSize(new Dimension(437,520));
		frame.pack();
		frame.setVisible(true);
	}
	
	public BoardView(Board board){
		this.board = board;
		this.board.setView(this);
		
		//grid is 21x21 (19x19 with extra this.squares for thin borders)
		this.sq = 20; //square size on the grid
	}
	
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
	
	//updates rectangular map pieces to be barriers
	public void updateRectangleBarriers(int x, int y, int width, int height){
		for (int i = x; i < x + width; i++){
			for (int j = y; j < y + height; j++){
				this.board.updateBarrier(i, j);
			}
		}
	}
	
	//updates abnormal map pieces to be barriers (polygons and borders of map)
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
		
		this.board.updateBarrier(polyPoints);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		this.drawMap(g);
	}
}
