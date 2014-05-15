import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {


		final int DISPLAY_WIDTH = 400;
		final int DISPLAY_HEIGHT = 400;

		JFrame f = new JFrame();
		f.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		
		Guitar2 g = new Guitar2(DISPLAY_WIDTH, DISPLAY_WIDTH);
		f.add(g);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Guitar2Main");
		f.setVisible(true);
	}

}
