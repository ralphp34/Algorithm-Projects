import java.util.*;
// Bin-packing with unit bin sizes and item sizes drawn
// randomly between 0 and 1.

public class BinPacking {

  // Implement the First-Fit algorithm. The bin size is 1.
  // itemSizes[i] = size of item i.

  static int[] firstFit (double[] itemSizes)
  {
    // INSERT YOUR CODE HERE.
    // Return an int array bins[i] where
    // bins[i] = which bin item i gets assigned to.

    // Temporarily:
    double[] bins = new double[1];
    int[] intBins = new int[itemSizes.length];

    for (int i = 0; i < itemSizes.length; i++) 
    {
 	for (int j = 0; j <= bins.length; j++) 
	{
	    if (j == bins.length) 
	    {
		bins = Arrays.copyOf(bins, bins.length+1);
		bins[j] = bins[j] + itemSizes[i];
		intBins[i] = j;
		break;
	    }
	    if (itemSizes[i] < 1 - bins[j]) 
	    {
	    	bins[j] = bins[j] + itemSizes[i];
		intBins[i] = j;
		break;
	    }
	}
    }
    return intBins;
  }

  // Implement the Decreasing-First-Fit algorithm. The bin size is 1.
  // itemSizes[i] = size of item i.

  static int[] decreasingFirstFit (double[] itemSizes)
  {
    // INSERT YOUR CODE HERE.
    // Return an int array bins[i] where
    // bins[i] = which bin item i gets assigned to.

    // Temporarily:
    for (int i = 0; i < itemSizes.length-1; i++) {
	    int maxIndex = 0;
	    for (int j = 1; j < itemSizes.length;j++) {
		    if (itemSizes[j] > itemSizes[maxIndex]) {
			    maxIndex = j;
		    }
	    }
	    double temp = itemSizes[maxIndex];
	    itemSizes[maxIndex] = itemSizes[i];
	    itemSizes[i] = temp;
    }
    return firstFit(itemSizes);
  }
  
  /////////////////////////////////////////////////////////////////////

  // Number of samples used in each experiment trial.
  static int numSamples = 10;

  public static void main (String[] argv)
  {
    try {
      int numItems = 10;

      // Read number of items from command-line, if given.
      if (argv.length != 0)
        numItems = Integer.parseInt (argv[0]);

      runTests (numItems);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  static void runTests (int numItems)
  {
    // Maintain totals for averages of First-Fit (FF) and Decreasing-First-Fit (DFF).
    double totalFF=0, totalDFF=0;

    for (int n=0; n<numSamples; n++) {

      // Create data. 
      double[] itemSizes = generateItemSizes (numItems);

      // Print item sizes to screen.
      System.out.print ("Itemsizes: ");
      for (int i=0; i<numItems; i++) 
        System.out.print (" " + itemSizes[i]);
      System.out.println ("");
      
      // FF first.
      int[] bins = firstFit (copy(itemSizes));
      int numBins = computeNumBins (bins, itemSizes);
      System.out.println ("First Fit: number of bins: " + numBins);
      totalFF += numBins;

      // Now DFF.
      bins = decreasingFirstFit (itemSizes);
      numBins = computeNumBins (bins, copy(itemSizes));
      System.out.println ("Decreasing First Fit: number of bins: " + numBins);
      totalDFF += numBins;
    }

    // Averages.
    System.out.println ("Average #bins (FF): " + (totalFF/numSamples));
    System.out.println ("Average #bins (DFF): " + (totalDFF/numSamples));
  }


  static double[] copy (double[] A)
  {
    double[] B = new double [A.length];
    for (int i=0; i<A.length; i++)
      B[i] = A[i];
    return B;
  }
  

  static double[] generateItemSizes (int numItems)
  {
    double[] itemSizes = new double [numItems];
    for (int i=0; i<numItems; i++)
      itemSizes[i] = UniformRandom.uniform();
    return itemSizes;
  }


  static int computeNumBins (int[] bins, double[] itemSizes)
  {
    int numItems = itemSizes.length;
    
    // First check length of array.
    if (bins.length != numItems) {
      System.out.println ("Wrong array length: bins");
      System.exit(1);
    }

    // Next, check that every bin# is between 0 and numItems-1
    // Also, track the largest bin number.
    int max = 0;
    for (int i=0; i<numItems; i++) {
      if ( (bins[i] < 0) || (bins[i] >= numItems) ) {
        System.out.println ("Bad bin number");
        System.exit(1);
      }
      if (bins[i] > max)
        max = bins[i];
    }

    // Now check sizes.
    for (int i=0; i<numItems; i++) {
      // Find all items assigned to bin i.
      double sum = 0;
      for (int j=0; j<numItems; j++) {
        if (bins[j] == i) {
          sum += itemSizes[j];
        }
      }
      if (sum > 1.0) {
        System.out.println ("Bin overflow: bin# " + i);
        System.exit(1);
      }
    }
    
    // Return number of bins used.
    return max+1;
  }

}
