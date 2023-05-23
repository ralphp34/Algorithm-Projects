import java.lang.*;
import java.util.*;
import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*; 

public class BinarySearch implements OrderedSearchAlgorithm
{
   public ComparableKeyValuePair data[];

   public java.lang.Object delete(java.lang.Comparable key)
   {
	return null;	
   }
   
   public java.lang.Object insert(java.lang.Comparable key, java.lang.Object value)
   {
	edu.gwu.algtest.ComparableKeyValuePair kvp = new edu.gwu.algtest.ComparableKeyValuePair();
	kvp.key = key;
	kvp.value = value;

	if (getCurrentSize() == 0) 
	{
		data[0] = kvp;
		return null;
	}
	else 
	{
	   int i = 0;
	   for (i = getCurrentSize()-1; (i >= 0) && (data[i].key.compareTo(key) > 0); i--) 
	   {
		if (data[i].key.compareTo(key) == 0) 
		{
   		   data[i].value = kvp.value;
   		   return data[i];
		}
  		data[i+1] = data[i];
	   }
			
	   data[i+1] = kvp;

	}
	return null;
   }

   public edu.gwu.algtest.ComparableKeyValuePair maximum()
   {
	edu.gwu.algtest.ComparableKeyValuePair kvp;
	kvp = data[0];
	for (int i = 0; i < data.length; i++)
	{
	   if (data[i].key.compareTo(kvp.key) > 0)
	   {
		kvp = data[i];
	   }
	}
	return kvp;

   }	

   public edu.gwu.algtest.ComparableKeyValuePair minimum()
   {
	edu.gwu.algtest.ComparableKeyValuePair kvp;
	kvp = data[0];
	for (int i = 0; i < data.length; i++)
	{
	   if (data[i].key.compareTo(kvp.key) < 0)
	   {
		kvp = data[i];
	   }
	}
	return kvp;
   }	    
   
   public java.lang.Comparable predecessor(java.lang.Comparable key)
   {
	return null;
   }

   public edu.gwu.algtest.ComparableKeyValuePair search(java.lang.Comparable key)
   {
	int low = 0;
	int high = getCurrentSize() - 1;
	while (low <= high)
	{
	   int mid = (low + high) / 2;
	   if (key.compareTo(data[mid].key) == 0)
	   {
		return data[mid];
	   }
	   else if(key.compareTo(data[mid].key) < 0)
	   {
		high = mid - 1;
	   }
	   else 
	   {
		low = mid + 1;
	   }
	}
	return null; 
   }

   public java.lang.Comparable successor(java.lang.Comparable key)
   {
	return null;
   }

   public void initialize(int maxSize)
   {
	data = new edu.gwu.algtest.ComparableKeyValuePair[maxSize];	
   }

   public int getCurrentSize()
   {
	int count = 0;
	for (int i = 0; i < data.length; i++)
	{
	   if (data[i] != null)
	   {
		count++;
	   }
	}
	return count;
   }

   public java.util.Enumeration getKeys()
   {
	return null;
   }

   public java.util.Enumeration getValues()
   {
	return null;
   }

   public java.lang.String getName()
   {
	return "Binary Search";
   }

   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {
	return;
   }
//main
// call the initialize, insert the values, the call search, and call max and min
   public static void main (String[] argv)
   {
	BinarySearch bs = new BinarySearch();
	
	bs.initialize(5);
	System.out.println("Inserting");

	bs.insert(10, "hi");
	bs.insert(20, "hello");
	bs.insert(3, "good morning");
	bs.insert(40, "greetings");
	bs.insert(5, "hey");

	for (int i = 0; i < bs.data.length; i++)
	{
	   System.out.println(bs.data[i]);
	}
	System.out.println("Searching");
	System.out.println(bs.search(10));

	System.out.println("Max");
	
	System.out.println(bs.maximum());
	
	System.out.println("Min");
	System.out.println(bs.minimum());
	

   }

}

