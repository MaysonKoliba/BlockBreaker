package BlockBreaker;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class blockGrid {

	public int grid[][] = new int[3][7];
	public int blockHeight;
	public int blockWidth;
	
	public blockGrid() {
		for(int i = 0; i < grid.length; i++) {
			for(int k = 0; k < grid[0].length; k++) {
				grid[i][k] = 1;
			}
		}
		blockWidth = 750/7;
		blockHeight = 260/3;
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < grid.length; i++) {
			for(int k = 0; k < grid[0].length; k++) {
				if(grid[i][k] > 0) {
					g.setColor(Color.WHITE);
					g.fillRect(k*blockWidth+70, i*blockHeight+60, blockWidth, blockHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLACK);
					g.drawRect(k*blockWidth+70, i*blockHeight+60, blockWidth, blockHeight);
				}
			}
		}
	}
	
	public void blockHit() {
		
	}
}