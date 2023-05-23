
public class Subsets {

  static boolean[] getFirstSubset (int N)
  {
    boolean[] member = new boolean[N];
    for (int i = 0; i < N; i++) 
    {
	member[i] = false;
    }
    return member;
  }
  
  static boolean[] getNextSubset (boolean[] member)
  {
    // INSERT
    int i = member.length-1;
    while ((i >= 0) && (member[i] == true)) 
    {
	member[i] = false;
	i = i - 1;
    }
    if (i < 0) 
    {
	return null;
    }
    else 
    {
	member[i] = true;
	return member;
    }
  }

  ////////////////////////////////////////////////////////////////

  public static void main (String[] argv)
  {
    try {
      int N = 10;

      // Read number of items from command-line, if given.
      if (argv.length != 0)
        N = Integer.parseInt (argv[0]);

      runTest (N);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void runTest (int N)
  {
    boolean[] member = getFirstSubset (N);
    int count = 0;
    while (member != null) {
      printSubset (member);
      count ++;
      member = getNextSubset (member);
    }
    
    int numSubsets = powerOfTwo (N);
    if (numSubsets != count) {
      System.out.println ("Wrong number of subsets: num=" + count + " correct val=" + numSubsets);
      System.exit(1);
    }
  }
  
  
  static int powerOfTwo (int k)
  {
    int power = 1;
    int n = 0;
    while (n < k) {
      n ++;
      power = power * 2;
    }
    return power;
  }

  static void printSubset (boolean[] member)
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
  
}
