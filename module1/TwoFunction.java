
public class TwoFunction {

    public static void main (String[] argv)
    {
        Function f = new Function ("F");
        Function g = new Function ("G");
        for (int n=0; n<=2000; n+=10) {
            f.add (n, 9*n*n + 1000*n);
            g.add (n, 10*n*n);
        }
        Function.show (f,g);
    }

}
