
public class MakeSound {

    public static void main (String[] argv)
    {
        int numSamples = 1600;
	int samplesPerSecond = 400;

	byte[] samples = new byte [numSamples];

        // INSERT YOUR CODE HERE: set all values in array to 100.

	
	// Now play the sound.
	SignalProcUtil.playSoundBytes (samples, samplesPerSecond);
    }

}
