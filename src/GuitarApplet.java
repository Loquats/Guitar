import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JApplet;


public class GuitarApplet extends Applet implements KeyListener{

	static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
	static GuitarString[] strings = new GuitarString[37];
	
	String d = "hekllo";

	public GuitarApplet() {

		for(int i = 0; i < strings.length; i++){
			//		System.out.println(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
			strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
		}
	}

	public void init(){
		setSize(400, 300);
		setBackground(Color.BLACK);
		addKeyListener(this);
		System.out.println(keyboard.length());

		//		setFocusable(true);
		//      requestFocusInWindow();

		strings[0].pluck();
		strings[4].pluck();
		strings[7].pluck();
		
	//	GuitarThread p = new GuitarThread(getGraphics());
	 //   p.start();

		System.out.println("done init");
	}

	public void start(){
		System.out.println("starting");
		//		while(true){
		//			double sample = sampleAll(strings);
		//			StdAudio.play(sample);
		//			ticAll(strings);
		//		}

	//	repaint();
	}
	
	public void paint( Graphics g ) {
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		g.setColor( Color.green );
        g.drawRect(0, 0, 100, 40);
		g.drawString( d, 20, 20);
		System.out.println("paint");
	//	repaint();
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
		d = Character.toString(c);

		if(keyIndex >= 0 && keyIndex < strings.length){
			strings[keyIndex].pluck();
		}
	//	repaint();
	}

}
