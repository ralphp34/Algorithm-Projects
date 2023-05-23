
public class ex1 {

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
    // INSERT YOUR CODE HERE: return the position in the text
    // where the pattern starts (if it is found), else return -1.
    for (int i = 0; i < text.length; i++)
    {
	if (text[i] == pattern[0])
	{
	   boolean match = true;
           int num = i;
           for (int j=0; j < pattern.length; j++)
	   {
		if (text[i+j] != pattern[j])
	   	{
		   match = false;
           	}
           }
	   if (match)
	   {
		return num;
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
        // Test pattern and text:
        String textStr = "ABABACBABABA";
        String patternStr = "BAC";
        text = textStr.toCharArray();
        pattern = patternStr.toCharArray();
      }
      else {
        // Random generation of pattern and text:
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
