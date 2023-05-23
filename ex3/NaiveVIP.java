import java.lang.*;
import java.util.*;
import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*;


public class NaiveVIP implements VIPAlgorithm
{

   public boolean VIPexists(VIPProblemData data)
   {
	int n = data.numPeople();
	int count = 0;
	//int[] aOne = new int[n];
    	//int[] aTwo = new int[n];

	for (int i = 0; i < n; i++)
    	{
      	   for (int j = 0; j < n; j++)
           {
		if (i != j && (data.knows(j,i)) && !data.knows(i,j))
		{
		   count++;
		}
		if (count == n-1)
		{
		   return true;
		}
	   }
	   count = 0;
	}
	return false;
   }
   
   public java.lang.String getName()
   {
	return "NaiveVIP";
   }

   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {
	return;
   }

   public static void main (String[] argv)
   {	

   }
}
