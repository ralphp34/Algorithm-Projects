
public class ex2 {

  static char[] generateChars (int length)
  {
    char[] charArray = new char [length];
    for (int i=0; i<length; i++) {
      charArray[i] = (char) UniformRandom.uniform ( (long)'A', (long) 'C' );
    }
    return charArray;
  }
  

  static int findFirstOccurrence (char[] pattern, char[] text)
  {
    // INSERT YOUR CODE HERE
    int M = pattern.length;
    int N = text.length;
    int i = 0;
    int j = 0;
    int d = 256;
    int s = 101;
    int p = 0;
    int e = 0; 
    int h = 1;

    for (i = 0; i < M - 1; i++){
      h = (h * d) % s;
    }

    for (i = 0; i < M; i++) 
    {
       p = (d * p + pattern[i]) % s;
       e = (d * e + text[i]) % s;
    }

    for (i = 0; i <= N - M; i++) 
    {
      	if (p == e) 
   	{
           for (j = 0; j < M; j++) 
	   {
          	if (text[i + j] != pattern[j])
		{
           	   break;
	        }
           }
           if (j == M)
	   {
             	return i;
	   }
      	}
      	if (i < N - M) 
  	{
           e = (d * (e - text[i] * h) + text[i + M]) % s;
           if (e < 0)
	   {
		e = (e + s);
	   }
      	}
    }
    return -1;
    
  }

  public static void main (String[] argv)
  {
    try {
      char[] text = null;
      char[] pattern = null;
      if (argv.length != 2) {
        String textStr = "ABABACBABABA";
        String patternStr = "BAC";
        text = textStr.toCharArray();
        pattern = patternStr.toCharArray();
      }
      else {
        int textLength = Integer.parseInt (argv[0].trim());
        int patternLength = Integer.parseInt (argv[1].trim());
        text = generateChars (textLength);
        pattern = generateChars (patternLength);
      }
      
      int position = findFirstOccurrence (pattern, text);
      if (position >= 0)
        System.out.println ("Found at position=" + position);
      else
        System.out.println ("Not found");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  

}
