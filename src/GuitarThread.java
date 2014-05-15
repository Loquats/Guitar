import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GuitarThread extends Applet implements Runnable, KeyListener{
	
	static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
	static GuitarString[] strings = new GuitarString[37];
	double sample;

	int width, height;
	int i = 0;
	Thread t = null;
	boolean threadSuspended;

	// Executed when the applet is first created.
	public void init() {
		
		for(int i = 0; i < strings.length; i++){
			//		System.out.println(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
			strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
		}
		addKeyListener(this);
		strings[0].pluck();
		strings[4].pluck();
		strings[7].pluck();

		
	//	System.out.println("init(): begin");
		width = getSize().width;
		height = getSize().height;
		setBackground( Color.black );
	//	System.out.println("init(): end");
	}

	// Executed when the applet is destroyed.
	public void destroy() {
		System.out.println("destroy()");
	}

	// Executed after the applet is created; and also whenever
	// the browser returns to the page containing the applet.
	public void start() {
//		System.out.println("start(): begin");
		if ( t == null ) {											// creates new thread if no thread
//			System.out.println("start(): creating thread");
			t = new Thread( this );
//			System.out.println("start(): starting thread");
			threadSuspended = false;
			t.start();												// starts thread
		}
		else {
			if ( threadSuspended ) {
				threadSuspended = false;
				System.out.println("start(): notifying thread");
				synchronized( this ) {
					notify();
				}
			}
		}
//		System.out.println("start(): end");
	}

	// Executed whenever the browser leaves the page containing the applet.
	public void stop() {
		System.out.println("stop(): begin");
		threadSuspended = true;
	}

	// Executed within the thread that this applet created.
	public void run() {
		System.out.println("run(): begin");
		try {
			while (true) {
//				System.out.println("run(): awake");

				sample = sampleAll(strings);
				StdAudio.play(sample);
				ticAll(strings);
				
				// Here's where the thread does some work
				++i;
				if ( i == 10 ) {
					i = 0;
				}
	//			showStatus( "i is " + i );

				// Now the thread checks to see if it should suspend itself
				if ( threadSuspended ) {
					synchronized( this ) {
						while ( threadSuspended ) {
							System.out.println("run(): waiting");
							wait();
						}
					}
				}
//				System.out.println("run(): requesting repaint");
				repaint();
				System.out.println("run(): sleeping");
				t.sleep( 100 );  // interval given in milliseconds
			}
		}
		catch (InterruptedException e) { }
		System.out.println("run(): end");
	}

	// Executed whenever the applet is asked to redraw itself.
	public void paint( Graphics g ) {
		System.out.println("paint()");
		g.setColor( Color.green );
		g.drawLine( width, height, i * width / 10, 0 );
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
	//	repaint();
	}

}