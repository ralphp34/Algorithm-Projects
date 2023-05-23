    import edu.gwu.algtest.*;
    import edu.gwu.util.*;
    import edu.gwu.debug.*;

    public class MyPartition implements PartitionAlgorithm {

        public int leftIncreasingPartition(int[] data, int left, int right)
        {
            // Your code here
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
                   currentSwapPosition++;
                   swap (data, currentSwapPosition, i);
            	}
            }
            swap (data, currentSwapPosition, left);
            return currentSwapPosition; 
        }

        public int rightIncreasingPartition(int[] data, int left, int right)
        {
            // Empty implementation 
            return -1;
        }
   	public java.lang.String getName()
   	{
	    return null;
   	}
   	public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   	{
	
   	}

    	static void swap (int[] data, int i, int j)
    	{
           int temp = data[i];
           data[i] = data[j];
           data[j] = temp;
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
            // Your own test code here 
	    MyPartition partition = new MyPartition();
	    int[] test = {4,10,5,6,78,43,3,12};
	    System.out.println("Before sorting:");
	    printArray(test);
	    System.out.println("After sorting:");
	    partition.leftIncreasingPartition(test,1,6);
	    printArray(test);	    
	    
        }

    }
