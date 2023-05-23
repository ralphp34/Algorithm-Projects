import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*; 

public class SelectionSort implements SortingAlgorithm
{
   public void sortInPlace(int [] data)
   {
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
		}
	    }
	    // Swap into position i.
	    int temp = data[i];
	    data[i] = data[pos];
	    data[pos] = temp;

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
	SelectionSort selectionSort = new SelectionSort();
	int[] test = {1,3,7,1,2,4,6};
	System.out.println("Before sorting:");
	printArray(test);
	selectionSort.sortInPlace(test);
	System.out.println("After sorting:");
	printArray(test);
   }

} 

