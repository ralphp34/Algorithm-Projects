
import java.util.*;

public class MapSetExample {

    public static void main (String[] argv)
    {
        setExample ();
        mapExample ();
    }

    static void setExample ()
    {
        // Example: use strings or numbers.

        // INSERT YOUR CODE HERE.

	Set<Integer> set = new HashSet<>();
 
	set.add(1);
	set.add(2);
	set.add(3);
	System.out.println(set);
    }

    static void mapExample ()
    {
        // Example: use a map from numbers to strings.

        // INSERT YOUR CODE HERE.
	Map<String, String> m = new HashMap<String, String>();

	m.put("10", "hi");
	m.put("30", "hey");
	m.put("20", "sup");

	System.out.println(m);
	
	
    }

}
