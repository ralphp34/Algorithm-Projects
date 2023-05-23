import java.lang.*;
import java.util.*;
import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*;

public class NaiveClosestPair implements ClosestPairAlgorithm
{
   public double findClosestPairDistance(edu.gwu.geometry.Pointd[] points)
   {
      	// INSERT YOUR CODE HERE
	double smallest_dist = Integer.MAX_VALUE;
	for (int i = 0; i < points.length; i++)
	{
	   for (int j = i+1; j < points.length; j++)
	   {
		if (Math.sqrt((Math.pow(Math.abs(points[i].y - points[j].y), 2)) + (Math.pow(Math.abs(points[i].x - points[j].x), 2))) < smallest_dist)
		{
		   smallest_dist = Math.sqrt((Math.pow(Math.abs(points[i].y - points[j].y), 2)) + (Math.pow(Math.abs(points[i].x - points[j].x), 2))); 
		}
	   }
  	}
	return smallest_dist;
   }

   public java.lang.String getName()
   {
	return "Naive Closest Pair";
   }

   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {
	return;
   }
   //main
   // call the initialize, insert the values, the call search, and call max and min
   public static void main (String[] argv)
   {
	NaiveClosestPair ncp = new NaiveClosestPair();	
	edu.gwu.geometry.Pointd[] testPoints = new edu.gwu.geometry.Pointd[6];
	testPoints[0] = new edu.gwu.geometry.Pointd(11.5, 12.0);
	testPoints[1] = new edu.gwu.geometry.Pointd(10.5, 12.0);
	testPoints[2] = new edu.gwu.geometry.Pointd(1.5, 22.0);
	testPoints[3] = new edu.gwu.geometry.Pointd(17.5, 42.0);
	testPoints[4] = new edu.gwu.geometry.Pointd(30.5, 14.0);
	testPoints[5] = new edu.gwu.geometry.Pointd(13.5, 9.0);

	System.out.print(ncp.findClosestPairDistance(testPoints));

	
   }

} 
