
public class SelectionSort {

  static int rangeLow = 100;
  static int rangeHigh = 1000;
  static int comparisons = 0;
  static int[] makeRandomData (int numValues)
  {
    int[] data = new int [numValues];
    for (int i=0; i<data.length; i++)
      data[i] = (int) UniformRandom.uniform ( (int) rangeLow, (int) rangeHigh );
    return data;
  }

  static void printData (int[] data)
  {
    System.out.print ("Data: ");
    for (int i=0; i<data.length; i++)
      System.out.print (" " + data[i]);
    System.out.println ("");
  }
  

  static void sort (int[] data)
  {
    // INSERT YOUR CODE HERE:
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
		comparisons++;
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
    if ( (argv == null) || (argv.length == 0) ) {
      System.out.println ("Usage: java SelectionSort <number-of-values>");
      System.exit(0);
    }

    try {
      int numValues = Integer.parseInt (argv[0]);
      int[] data = makeRandomData (numValues);
      System.out.println ("BEFORE SORTING: ");  
      printData (data);
      sort (data);
      System.out.println ("AFTER SORTING: ");
      printData (data);
	System.out.println(comparisons);
    }
    catch (Exception e) {
      System.out.println (e);
    }
  }

}
