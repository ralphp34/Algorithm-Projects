import java.util.*;

public class SelectionSortTiming {

    static int rangeLow = 100;
    static int rangeHigh = 1000;
    
    static Integer[] makeRandomData (int numValues)
    {
        Integer[] data = new Integer [numValues];
        for (int i=0; i<data.length; i++) {
            data[i] = (int) UniformRandom.uniform ( (int) rangeLow, (int) rangeHigh );
        }
        return data;
    }

    static Integer[] copy (Integer[] data)
    {
        Integer[] dataCopy = new Integer [data.length];
        for (int i=0; i<data.length; i++) {
            dataCopy[i] = data[i];
        }
        return dataCopy;
    }
    

    static void selectionSort (Integer[] data)
    {
        // INSERT YOUR CODE HERE.
		for (int i = data.length - 1; i >= 0; i--) {
	    // Find i-th smallest and swap.
	    int largest = data[i];
	    int pos = i;

            // Look from i+1 and up.
	    for (int j= 0; j< i; j++)
	    {
		if (data[j] > largest) {
		    largest = data[j];
		    pos = j;
		}
	    }
	    // Swap into position i.
	    int temp = data[i];
	    data[i] = data[pos];
	    data[pos] = temp;
}
    }
    

    public static void main (String[] argv) 
    {
        Integer[] data = makeRandomData (50000);
        Integer[] dataCopy = copy (data);
        
        // Java's sort algorithm.
        long startTime = System.currentTimeMillis ();
        Arrays.sort (data);
        double timeTaken = System.currentTimeMillis() - startTime;
        System.out.println ("Time taken by Java's sort: " + timeTaken);

        startTime = System.currentTimeMillis ();
        selectionSort (dataCopy);
        timeTaken = System.currentTimeMillis() - startTime;
        System.out.println ("Time taken by your selection sort: " + timeTaken);

        
    }
    
}
