import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*; 

public class SequentialSearch implements OrderedSearchAlgorithm
{
   private edu.gwu.algtest.ComparableKeyValuePair data[];

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
	data[getCurrentSize()] = kvp;
	if(search(kvp.key) != null)
	{
	   for (int i = 0; i < data.length; i++)
	   {
		if ((data[i].key).compareTo(kvp.key) == 0)
		{
		   java.lang.Object temp = data[i].value;
		   data[i] = kvp;
		   return temp;
		}
	   }
	}
	if (data.length == getCurrentSize())
	{
	   initialize(data.length+1);
	   data[data.length] = kvp;
	   return null;
	}

	return null;
   }

   public edu.gwu.algtest.ComparableKeyValuePair maximum()
   {
	edu.gwu.algtest.ComparableKeyValuePair kvp;
	kvp = data[0];
	for (int i = 0; i < getCurrentSize(); i++)
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
	for (int i = 0; i < getCurrentSize(); i++)
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
	for(int i = 0; i < getCurrentSize(); i++)
	{
	   
	   if ((key.compareTo(data[i].key)) == 0)
	   {
		return data[i];
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
	return "Seq Search";
   }

   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {

   }

//main
// call the initialize, insert the values, the call search, and call max and min
   public static void main (String[] argv)
   {
	SequentialSearch ss = new SequentialSearch();
	ss.initialize(5);
	System.out.println("Inserting");

	ss.insert(10, "hi");
	ss.insert(20, "hello");
	ss.insert(3, "good morning");
	ss.insert(40, "greetings");
	ss.insert(5, "hey");


	for (int i = 0; i < ss.data.length; i++)
	{
	   System.out.println(ss.data[i]);
	}
	System.out.println("Searching");
	System.out.println(ss.search(10));

	System.out.println("Max");
	
	System.out.println(ss.maximum());
	
	System.out.println("Min");
	System.out.println(ss.minimum());
   }
}


