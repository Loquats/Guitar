import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;


public class GuitarVisualizer extends JComponent{

	public void init() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {

		g.setColor(Color.BLACK);
		g.drawLine(50, 50, 300, 50);
		g.setColor(Color.RED);
	}

}
