
public class LargestSubsequence {


  static double naiveAlg (double[] X)
  {
    // INSERT YOUR CODE HERE.
    // The naive algorithm simply tries all possible contiguous subsequences.
    double sum = 0.0;
    double result = 0.0;
    for (int i = 0; i < X.length; i++)
    {	
	for (int j = 1; j < X.length; j++)
	{
	   sum = 0;
	   for (int k = i; k <= j; k++)
	   {
		sum += X[k]; 
	   }
	   if (sum > result)
	   { 
	 	result = sum;
	   }
	}
    }
    return result;
  }


  static double fastAlg (double[] X)
  {
    // INSERT YOUR CODE HERE.
    double max = Integer.MIN_VALUE, maxh = 0, start = 0, end = 0, s = 0;
 
        for (int i = 0; i < X.length; i++) {
            maxh += X[i];
 
            if (max < maxh) {
                max = maxh;
                start = s;
                end = i;
            }
 
            if (maxh < 0) {
                maxh = 0;
                s = i + 1;
            }
        }
	return max;
    
  }


  public static void main (String[] argv)
  {
    // Correctness tests.
    double[] A = {-1, 8, -2, 5, -3, -1, 2};
    testCorrectness ("naiveAlg", A, 11);
    testCorrectness ("fastAlg", A, 11);

    double[] A2 = {-3, 1.5, -1, 3, -2, -3, 3};
    testCorrectness ("naiveAlg", A2, 3.5);
    testCorrectness ("fastAlg", A2, 3.5);

    // Speed tests.
    testSpeed ("naiveAlg", 1000);
    testSpeed ("fastAlg", 1000);
    
  }



  // Run a correctness test.

  static void testCorrectness (String whichAlg, double[] A, double correctSum) 
  {
    double sum;
    if (whichAlg.equalsIgnoreCase ("naiveAlg")) {
      sum = naiveAlg (A);
    }
    else {
      sum = fastAlg (A);
    }
    if (sum != correctSum) {
      System.out.println ("ERROR: " + whichAlg + " doesn't work");
    }
    else {
      System.out.println ("Test 1 passed by " + whichAlg + ". Sum = " + sum);
    }
  }
  


  // Generate a random array of length problemSize and time the execution.

  static void testSpeed (String whichAlg, int problemSize)
  {
    double[] A = new double [problemSize];
    for (int i=0; i<A.length; i++) {
      A[i] = UniformRandom.uniform (-1.0, 1.0);
    }
    // Make at least one number positive.
    double a = UniformRandom.uniform ();
    int k = (int) UniformRandom.uniform ((int) 0, (int)(problemSize-1));
    A[k] = a;

    // Time the execution.
    long startTime = System.currentTimeMillis ();
    double sum;
    if (whichAlg.equalsIgnoreCase ("naiveAlg")) {
      sum = naiveAlg (A);
    }
    else {
      sum = fastAlg (A);
    }
    long timeTaken = System.currentTimeMillis() - startTime;

    System.out.println ("Time taken by " + whichAlg + ": " + timeTaken + " ms");
  }


}
