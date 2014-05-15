import java.util.Random;

/*
 * public class GuitarString
------------------------------------------------------------------------------------------------------------------------
       GuitarString(double frequency)  // create a guitar string of the given frequency, using a sampling rate of 44,100
       GuitarString(double[] init)     // create a guitar string whose size and initial values are given by the array
  void pluck()                         // set the buffer to white noise
  void tic()                           // advance the simulation one time step
double sample()                        // return the current sample
   int time()                          // return number of tics
 */

public class GuitarString {

	private RingBuffer string;
//	private int tics;

	public GuitarString(double frequency){
		string = new RingBuffer((int) Math.ceil(44100 / frequency));
	}

	//	public GuitarString(double[] init){
	//		
	//	}

	public void pluck(){
		Random rand = new Random();
		for(int i = 0; i < string.capacity(); i++){
			string.enqueue(rand.nextDouble() - 0.5);						// a number [-0.5, 0.5)
		}
	}

	public void tic(){
		double val1 = string.dequeue();
		double val2 = string.peek();
		string.enqueue((val1 + val2) / 2.0 * 0.995);
//		tics++;
	}

	public double sample(){
		return string.peek();
	}
//	public int time(){
//		return tics;
//	}

	public void print() {
		string.print();
		
	}

}
