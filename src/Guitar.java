import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Guitar{

	final static String keyboard = "q2we4r5ty7u8i9op-[=]zsxdcfvbhnjm,l.;/";
	final GuitarString[] strings = new GuitarString[37];
	
	public static void main(String[] args) {
		System.out.println(keyboard.length());

		final GuitarString[] strings = new GuitarString[37];

		for(int i = 0; i < strings.length; i++){
			strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
//			System.out.println(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
		}

		// Starts with a C chord as a sample
		strings[0].pluck();
		strings[4].pluck();
		strings[7].pluck();

		// Loads the keyboard image mapping keyboard keys to piano keys
		BufferedImage key = null;
		try {
			key = ImageIO.read(new File("keyboard.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Initializes the JFrame
		JFrame f = new JFrame();
		f.setSize(500, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Guitar");
		f.setVisible(true);
		Graphics g = f.getGraphics();
		
		// Adds a key listener
		f.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {

				char c = e.getKeyChar();
				int keyIndex = keyboard.indexOf(c);
				if(keyIndex >= 0 && keyIndex < strings.length){
					strings[keyIndex].pluck();
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {}

		});

		//
		while(true){
			double sample = sampleAll(strings);
			StdAudio.play(sample);
			ticAll(strings);
			g.drawImage(key, 20, 40, null);  // This definitely shouldn't go here... but it doesn't seem to work anywhere else yet. 
		}
	}

	private static void ticAll(GuitarString[] strings) {
		for(int i = 0; i < strings.length; i++){
			strings[i].tic();
		}
	}

	private static double sampleAll(GuitarString[] strings) {
		double sample = 0;
		for(int i = 0; i < strings.length; i++){
			sample += strings[i].sample();
		}
		return sample;
	}
}
