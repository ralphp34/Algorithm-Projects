
public class SinSum2 {

    public static void main (String[] argv)
    {
	Function Fsum = new Function ("sin-sum");
	for (double x=0; x<=1; x+=0.01) {
	    double y = Math.sin (2*Math.PI*x)
            + 1.0/3.0 * Math.sin (2*Math.PI*3*x);

            // INSERT MORE HARMONICS TO ABOVE STATEMENT.

	    Fsum.add (x, y);
	}
	Fsum.show ();
    }

}
