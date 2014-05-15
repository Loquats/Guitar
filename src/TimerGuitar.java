import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;


public class TimerGuitar{

	final static GuitarString[] strings = new GuitarString[37];			// 37 keys: A2 to A5
	static int index = 0;

	public static void main(String[] args) throws IOException {

		//		for(int i = 0; i < strings.length; i++){
		//			strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 48.0) / 12.0));
		//			System.out.println(440.0 * Math.pow(2.0, (i - 48.0) / 12.0));
		//		}	
		//		System.out.println();

		for(int i = 0; i < strings.length; i++){
			strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
			System.out.println(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
		}

		strings[0].pluck();
		strings[4].pluck();
		strings[7].pluck();

		JFrame f = new JFrame();
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Guitar");
		f.setVisible(true);


		class PluckTask extends TimerTask{
			int key;

			public PluckTask(int k) {
				key = k;
			}

			@Override
			public void run() {
				strings[key].pluck();
			}
		}

		Hashtable<String, Integer> noteToIndex = new Hashtable<String, Integer>();
		noteToIndex.put("A2", 0);
		noteToIndex.put("A2S", 1);
		noteToIndex.put("B2F", 1);
		BufferedReader r = new BufferedReader(new FileReader("greensleeves.txt"));
		StringTokenizer st;

		Timer[] score = new Timer[10];				// plays 10 notes

		for (int i = 0; i < score.length; i++) {	// initialize'em all
			score[i] = new Timer();
			st = new StringTokenizer(r.readLine());
			String note = st.nextToken();
			int length = Integer.parseInt(st.nextToken());
			score[i].schedule(new PluckTask(convertToIndex(note)), length * 1000);
		}

		//		score[0].schedule(new PluckTask(0), 2000);
		//		Timer t1 = new Timer();
		//		t1.schedule(new PluckTask(0), 2000);


		while(true){
			double sample = sampleAll(strings);
			//			System.out.println(sample);

			StdAudio.play(sample);

			ticAll(strings);
		}


	}



	private static int convertToIndex(String note) {
//		if()
			return 0;
	}



	private static int nextIndex() {
		return index++;
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
