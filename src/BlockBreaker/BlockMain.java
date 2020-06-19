package BlockBreaker;
import javax.swing.JFrame;

public class BlockMain {
	
	public static renderBlock render = new renderBlock();
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(900,800);
		window.setResizable(false);
		window.setTitle("Block Breaker");
		window.add(render);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}