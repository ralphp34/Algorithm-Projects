
public class Combinations {

  static int[] positions;
  static int size;

  static boolean[] getFirstCombination (int N, int k)
  {
    // Need to store this so that getNextCombination can use it.
    size = N;
    // Create space:
    positions = new int [N];
    boolean[] member = new boolean [N];

    // INSERT YOUR CODE HERE.
    for (int i = 0; i < k; i++) {
	    positions[i] = 1;
    }
    int i = 0;
    while (i < positions.length) {
	    if (positions[i] == 1) {
		    member[i] = true;
	    }
	    i++;
    }
    return member;
  }
  
  static boolean[] getNextCombination (boolean[] member)
  {
    // INSERT YOUR CODE HERE.
    if (size ==1) {
	    return null;
    }
    int r = -1;
    for (int i=0; i < size-1; i++) {
	    if (positions[i] == 1) {
		    if (positions[i+1] == 0) {
			    r = i;
		    }
	    }
    }
    if (r < 0) {
	    return null;
    }
    int count = 0;
    for (int i=r+1; i < size; i++) {
	    if (positions[i] == 1) {
		    count++;
	    }
    }
    int temp = positions[r];
    positions[r] = positions[r+1];
    positions[r+1] = temp;

    for (int j = 1; j <= count; j++) {
	    positions[r+1+j] = 1;
    }

    int j = size-1;
    while (j > (r+1+count)) {
	    positions[j] = 0;
	    j--;
    }

    int i = 0;
    while (i < positions.length) {
	    if (positions[i] == 1) {
		    member[i] = true;
	    }
	    i++;
    }
    return member;
  }
  
  public static void main (String[] argv)
  {
    try {
      int N = 8, k = 5;

      // Read number of items from command-line, if given.
      if (argv.length != 0) {
        N = Integer.parseInt (argv[0]);
        k = Integer.parseInt (argv[1]);
      }
      

      runTest (N, k);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void runTest (int N, int k)
  {
    boolean[] member = getFirstCombination (N, k);
    int count = 0;
    while (member != null) {
      //printCombination (member);
      count ++;
      member = getNextCombination (member);
    }
    
    int numCombinations = (int) combinations (N, k);
    if (numCombinations != count) {
      System.out.println ("Wrong number of combinations: num=" + count + " correct val=" + numCombinations);
      System.exit(1);
    }
  }

  static void printCombination (boolean[] member)
  {
    String str = "";
    for (int i=0; i<member.length; i++) {
      if (member[i])
        str += " 1";
      else 
        str += " 0";
    }
    System.out.println (str);
  }


  static long factorial (long n)
  {
    if (n == 0)
      return 1;
    int fact = 1;
    for (int i=1; i<=n; i++)
      fact = fact * i;
    return fact;
  }

  static long combinations (long n, long k)
  {
    if ( (k ==0) || (k == n) )
      return 1;
    
    long lastOne = k-1;
    long comb = n;
    for (int i=1; i<=lastOne; i++)
      comb = comb * (n - i);
    
    // Remove permutations.
    comb = comb / factorial (k);
    return comb;
  }
  
}
