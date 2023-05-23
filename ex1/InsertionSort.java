import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*; 

public class InsertionSort implements SortingAlgorithm
{
   public void sortInPlace(int [] data)
   {
	for (int i=0; i<data.length; i++) 
	{
            for (int j=i; (j>0) && (data[j]<data[j-1]); j--) 
	    {
		int temp = data[j];
                data[j] = data[j-1];
                data[j-1] = temp;
            }
	}
   }
   public void sortInPlace(java.lang.Comparable[] data)
   {
	//leaving abstract
   }
   public int[] createSortIndex(int [] data)
   {
	return null;
   }
   public int[] createSortIndex(java.lang.Comparable[] data)
   {
	return null;
   }
   public java.lang.String getName()
   {
	return null;
   }
   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {
	
   }

   public static void printArray(int[] data)
   {
	for (int i=0; i < data.length; i++)
	{
	   System.out.print(data[i] + " ");
	}
	System.out.println();
   }

   public static void main (String[] argv)
   {
	InsertionSort insertionSort = new InsertionSort();
	int[] test = {2,10,7,4,9,4,6};
	System.out.println("Before sorting:");
	printArray(test);
	insertionSort.sortInPlace(test);
	System.out.println("After sorting:");
	printArray(test);
   }

} 
