import java.util.*;

public class QuickSortTest {
  
    public void sortInPlace (int[] data)
    {
	quickSortRecursive (data, 0, data.length-1);
    }
  
    void swap (int[] data, int i, int j)
    {
	int temp = data[i];
	data[i] = data[j];
	data[j] = temp;
    }

    int leftPartition (int[] data, int left, int right)
    {
	// INSERT YOUR CODE HERE. Use the leftmost element as
	// the partitioning element.
	int count = 0;
	if (left == right)
        {    	
		return left;
	}
            int partitionElement = data[left];
            int currentSwapPosition = left; 
            for (int i=left+1; i<=right; i++) 
	    {
		if (data[i] < partitionElement) 
		{
		   count++;
                   currentSwapPosition++;
                   swap (data, currentSwapPosition, i);
            	}
            }
            swap (data, currentSwapPosition, left);
	    count++;
            return currentSwapPosition; 
    }
    
    void quickSortRecursive (int[] data, int left, int right)
    {
	// For sub-ranges larger than 1, split and recurse:
	if (left < right) {
	    // 1. Partition:
	    int k = pickMedian3 (data, left, (left+right)/2, right);
      		swap (data, k, left);
	    int partitionPosition = leftPartition (data, left, right);
	    
	    // 2. Sort left side:
	    quickSortRecursive (data, left, partitionPosition-1);
	    
	    // 3. Sort right side:
	    quickSortRecursive (data, partitionPosition+1, right);
	    
	    // Partition element is already in the correct place.
	}
    }
	
    int pickMedian3 (int[] data, int a, int b, int c)
    {
	int val = 0;
	if ((data[a] < data[b]) && (data[a] > data[c]))
	{
	    val = a;
	} 
	else if ((data[a] > data[b]) && (data[a] < data[c]))
	{
	    val = a;
	}
	else if ((data[b] < data[a]) && (data[b] > data[c]))
	{
	    val = b;
	} 
	else if ((data[b] > data[a]) && (data[b] < data[c]))
	{
	    val = b;
	}
	else 
	{
	    val = c;
	}	
	return val;
    }

    public static void test (int dataSize)
    {
	QuickSortTest qSort = new QuickSortTest ();
	int[] data = new int [dataSize];
	// Create the data:
	for (int i=0; i < dataSize; i++) {
	    data[i] = (int) UniformRandom.uniform ( (int) 100, (int) 1000);
	}

	// Sort:
	qSort.sortInPlace (data);

	// Check:
	boolean ok = true;
	for (int i=0; i < dataSize-1; i++) {
	    if (data[i] > data[i+1]) {
		ok = false;
	    }
	}
	if (! ok) {
	    System.out.println ("Failure in sorting algorithm");
	    System.exit (0);
	}
	System.out.println ("Passed: " + Arrays.toString(data));
    }
    
    public static void main (String[] argv)
    {
	test (10);
	// If that worked, try test (1000);
    }
  
}
