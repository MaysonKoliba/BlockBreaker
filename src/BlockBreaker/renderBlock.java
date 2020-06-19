package BlockBreaker;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class renderBlock extends JPanel implements ActionListener, KeyListener {
	
	public int paddleX = 400;
	public int paddleY = 720;
	public Random coord = new Random();
	public int ballX = coord.nextInt(300)+150;
	public int ballY = coord.nextInt(100)+580;
	public int ballXDir = -1;
	public int ballYDir = -1;
	public int brickCount = 21;
	public int score = 0;
	public boolean gameOver = false;
	public boolean gameWon = false;
	public boolean pause = true;
	public blockGrid grid;
	public Timer timer = new Timer(2,this);
	
	public renderBlock() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		grid = new blockGrid();
		timer.start();
	}
	
	public void newGame() {
		paddleX = 400;
		paddleY = 720;
		coord = new Random();
		ballX = coord.nextInt(300)+150;
		ballY = coord.nextInt(100)+580;
		ballXDir = -1;
		ballYDir = -1;
		brickCount = 21;
		score = 0;
		gameOver = false;
		gameWon = false;
		pause = true;
		grid = new blockGrid();
		timer.start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//background
		g.setColor(Color.BLACK);
		g.fillRect(0,0,900,800);
		
		//boarders
		g.setColor(Color.YELLOW);
		g.fillRect(0,0,900,5);
		g.fillRect(0,0,5,800);
		g.fillRect(890,0,5,800);
		
		//paddle
		g.setColor(Color.GREEN);
		g.fillRect(paddleX,paddleY,100,10);
		
		//ball
		g.setColor(Color.YELLOW);
		g.fillOval(ballX,ballY,20,20);
		
		//draw block grid
		grid.draw((Graphics2D)g);
		
		//score/pause/gameOver/gameWon
		if(!gameOver) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,16));
			g.drawString("Score: " + score,800,25);
		}
		else if(gameOver) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,16));
			g.drawString("Score: " + score,800,25);
			g.drawString("GAME OVER!",780,40);
		}
		
		if(pause) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,16));
			g.drawString("Score: " + score,800,25);
			g.drawString("PAUSED",800,40);
		}
		
		if(gameWon) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,16));
			g.drawString("Score: " + score,800,25);
			g.drawString("YOU WON!",800,40);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		
		if(!gameOver && !gameWon) {
			if(i == KeyEvent.VK_RIGHT && !pause) {
				if(paddleX + 20 > 800) {
					paddleX = 790;
				}
				else {
					paddleX += 20;
				}
			}
			if(i == KeyEvent.VK_LEFT && !pause) {
				if(paddleX - 20 < 0) {
					paddleX = 5;
				}
				else {
					paddleX -= 20;
				}
			}
			if(i == KeyEvent.VK_SPACE) {
				pause = !pause;
			}
		}
		
		if(gameOver) {
			if(i == KeyEvent.VK_SPACE) {
				newGame();
			}
		}
		
		if(gameWon) {
			if(i == KeyEvent.VK_SPACE) {
				newGame();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!gameOver && !pause && !gameWon) {
			
			repaint();
			ballX += ballXDir;
			ballY += ballYDir;
			
			if(new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(paddleX,paddleY,100,10))) {
				ballYDir = -ballYDir;
			}
			
			if(ballX > 870 || ballX < 0) {
				ballXDir = -ballXDir;
			}
			if(ballY < 0) {
				ballYDir = -ballYDir;
			}
			if(ballY > 800) {
				gameOver = true;
			}
			
			for(int i = 0; i < grid.grid.length; i++) {
				for(int k = 0; k < grid.grid[0].length; k++) {
					if(grid.grid[i][k] > 0) {
						
						Rectangle block = new Rectangle(k*grid.blockWidth+70, i*grid.blockHeight+60, grid.blockWidth, grid.blockHeight);
						Rectangle ball = new Rectangle(ballX,ballY,20,20);
						
						if(block.intersects(ball)) {
							grid.grid[i][k] = 0;
							brickCount--;
							score += 5;
							
							if(brickCount == 0) {
								gameWon = true;
							}
							
							if(ballX + 1 >= block.x + grid.blockWidth) {
								ballXDir = -ballXDir;
							}
							else {
								ballYDir = -ballYDir;
							}
						}
					}	
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}