
import java.util.*;
import java.io.*;

public class HashAnalysis {

    public static void main (String[] argv) 
    {
        String[] words = getDictionary ("words.txt");
        // Now words[i] has the i-th word in the dictionary.

        // Hint: you can call the hashCode() method in String to
        // get the hashvalue of any string.
        
        // INSERT YOUR CODE HERE
	    System.out.println("First Letter");
	
	int [] stats = new int[26];
	for (int i = 0; i < words.length; i++)
	{
	     char character = words[i].charAt(0); // This gives the character 'a'
	     int numVal = (int) character - 97;
	     stats[numVal]++;
	}
	for (int i = 0; i < stats.length; i++)
	{
	    System.out.println(stats[i]);
	}
	
	System.out.println("Last Letter");
	int [] stats2 = new int[26];
	for (int i = 0; i < words.length; i++)
	{
	     char character = words[i].charAt(words[i].length()-1); // This gives the character 'a'
	     //System.out.println(character);
		
	     int numVal = (int) character - 97;
	     stats2[numVal]++;
	}
	for (int i = 0; i < stats.length; i++)
	{
	    System.out.println(stats2[i]);
	}
	
	System.out.println("Hashcode");	
	int hash[] = new int[26];
	for (int i = 0; i < words.length; i++)
	{
	    String get = words[i].substring(0,1);
	    int hashcode = get.hashCode();
	    hash[hashcode % 26]++;
	}
	for (int i = 0; i < hash.length; i++)
	{
	    System.out.println(hash[i]);
	}

	
    }


    /////////////////////////////////////////////////////////////////
    // DO NOT READ BELOW THIS LINE - you don't need to.

    static boolean isValidWord (String str)
    {
        for (int i=0; i < str.length(); i++)
            if (! (Character.isLetter(str.charAt(i))) )
                return false;
        return true;
    }
    
    
    static String[] getDictionary (String fullPathName)
    {
        try {
            File f = new File (fullPathName);
            LineNumberReader lnr = new LineNumberReader (new FileReader (f));
            String line = lnr.readLine();
            int count = 0;
            while (line != null) {
                String str = line.trim().toLowerCase();
                if (isValidWord (str))
                    count++;
                line = lnr.readLine();
            }
            lnr.close();
            
            System.out.println ("Number of words: " + count);
            // OK, now make the space.
            String[] strArray = new String [count];
            lnr = new LineNumberReader (new FileReader (f));
            count = 0;
            line = lnr.readLine();
            while (line != null) {
                String str = line.trim().toLowerCase();
                if (isValidWord (str)) {
                    strArray [count] = line.trim().toLowerCase();
                    count++;
                }
                line = lnr.readLine();
            }
            lnr.close();
            return strArray;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
