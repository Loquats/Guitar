import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Guitar2 extends JComponent implements KeyListener{
	
	private final int DISPLAY_WIDTH;   
	private final int DISPLAY_HEIGHT;

	static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
	static GuitarString[] strings = new GuitarString[37];

	public Guitar2(int width, int height) {
		for(int i = 0; i < strings.length; i++){
	//		System.out.println(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
			strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
		}

		
		DISPLAY_WIDTH = width;
		DISPLAY_HEIGHT = height;
		init();
	}
	
	public void init() {
		
		setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		addKeyListener(this);
		System.out.println(keyboard.length());
		
		setFocusable(true);
        requestFocusInWindow();
        		
		strings[0].pluck();
		strings[4].pluck();
		strings[7].pluck();
		repaint();
		
		System.out.println("done init");
	}
	
	public void start(){
		System.out.println("starting");
//		while(true){
//			double sample = sampleAll(strings);
//			StdAudio.play(sample);
//			ticAll(strings);
//		}
	}
//	public void paintComponent(Graphics g) {
//		final int TIME_BETWEEN_REPLOTS = 10;
//
//		try {
//			Thread.sleep(TIME_BETWEEN_REPLOTS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		guitarCycle();
//		repaint();
//
//	}
	
	private void guitarCycle(){
		double sample = sampleAll(strings);
		StdAudio.play(sample);
		ticAll(strings);
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

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		char c = e.getKeyChar();
		int keyIndex = keyboard.indexOf(c);
		
		System.out.println(c);
		
		if(keyIndex >= 0 && keyIndex < strings.length){
			strings[keyIndex].pluck();
		}
	}



}
