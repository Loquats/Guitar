This is a virtual guitar I implemented. It plays 3 octaves of guitar-ish notes by using the (computer) keyboard.

The best thing to run is Guitar.jar, or alternatively Guitar.java, and don't forget to turn on the sound!

Guitar-playing Details
----------------------
The tones that can be played range 3 octaves from A2 to A5. Using a QWERTY keyboard is highly recommended. The layout approximately corresponds to the positions of black and white keys on a keyboard.

See keyboard.png for details.

Implementation Details
----------------------
Each of the 37 guitar string objects (GuitarString.java), corresponding to 37 distinct pitches, is modeled by a circular buffer (RingBuffer.java). 
"Plucking" a string fills the ring buffer with random noise (double values), which slowly average with each other while decreasing in intensity geometrically. This surprisingly mimics the sound of a guitar string pretty decently.

File Descriptions
----------------------
**Key Files:**
Guitar.jar - Open this to start playing the virual guitar! Having keyboard.png in the same directory allows the keyboard to be displayed in the program as well.

Guitar.java - Run this to start playing the virtual guitar! Having keyboard.png in the project directory allows the frame to display a keyboard as well. 

GuitarString.java - Guitar string object, modeled by a ring buffer.

RingBuffer.java - Essential to modeling guitar strings.

StdAudio.java - Some Princeton guy's audio toolkit that allows sounds to actually be played.

**Non-key Files:**
Guitar2.java - Doesn't work.
GuitarApplet.java - Doesn't work either.
GuitarLite.java - Only has two notes. Probably just for testing.
GuitarThread.java - Made this before I knew how threading works so natuarally it doesn't work.
StdDraw.java - Another toolkit. Not very useful.
TimerGuitar.java - Never mind this.

Sources
-----------
This is based on a project description from Stanford's Nifty site (http://nifty.stanford.edu/), which in turn was pulled from some CS program at Princeton. 

http://nifty.stanford.edu/
http://introcs.cs.princeton.edu/java/assignments/guitar.html


Thanks for checking it out!
Andy
5/14/2014
