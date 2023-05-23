import java.lang.*;
import java.util.*;
import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*;
import java.lang.Math.*;


public class DivideAndConquer implements ClosestPairAlgorithm
{
   public double findClosestPairDistance(edu.gwu.geometry.Pointd[] points)
   {
    	edu.gwu.geometry.Pointd[] pointsSortedByX = new edu.gwu.geometry.Pointd[points.length];
    	edu.gwu.geometry.Pointd[] pointsSortedByY = new edu.gwu.geometry.Pointd[points.length];
	for(int i = 0; i < points.length; i++)
	{
	    pointsSortedByX[i] = points[i];
	    pointsSortedByY[i] = points[i];
	}
   	//java.util.Arrays.sort (pointsSortedByX);
    	//java.util.Arrays.sort (pointsSortedByY);	
		Arrays.sort(pointsSortedByX, (p1, p2) -> Double.compare(p1.x,p2.x));
		Arrays.sort(pointsSortedByY, (p1, p2) -> Double.compare(p1.y,p2.y));


        return recursiveClosestPair (pointsSortedByX, 0, points.length-1, pointsSortedByY);
   }

   public double recursiveClosestPair(edu.gwu.geometry.Pointd[] xVals, int start, int end, edu.gwu.geometry.Pointd[] yVals)
   {
	int n = start - end + 1;

	if (n <= 3) 
	{
	   double smallest_dist = Integer.MAX_VALUE;	   
	   for (int i = 0; i < xVals.length-1; i++) 
	   {
		for (int j = i+1; j < xVals.length; j++) 
	   	{
		   if (Math.sqrt((Math.pow(Math.abs(xVals[i].y - xVals[j].y), 2)) + (Math.pow(Math.abs(xVals[i].x - xVals[j].x), 2))) < smallest_dist)
		   {
		   	smallest_dist = Math.sqrt((Math.pow(Math.abs(xVals[i].y - xVals[j].y), 2)) + (Math.pow(Math.abs(xVals[i].x - xVals[j].x), 2))); 
		   }	   
	   	}

	   }
	   return smallest_dist;
	}

	int mid_ind = start + (end - start) / 2;
	edu.gwu.geometry.Pointd median = xVals[mid_ind];

	double leftDist = recursiveClosestPair(xVals, start, mid_ind, yVals);
	double rightDist = recursiveClosestPair(xVals, mid_ind+1, end, yVals);

	double min_dist = Math.min(leftDist, rightDist);

	int leftBand = -1;
	int rightBand = -1;

	for (int i = start; i < end; i++)
 	{
	    if (Math.abs(yVals[i].x - median.x) < min_dist) 
	    {
		if (leftBand == -1) 
		{
		   leftBand = i;
		}
		else 
		{
		   rightBand = i;
		}
	    }
	}
	double minFromBand = getMinBandDist(yVals, leftBand, rightBand);
	return Math.min(min_dist, minFromBand);

   }

   public double getMinBandDist(edu.gwu.geometry.Pointd[] yVals, int left, int right) 
   {
	double min = Double.MAX_VALUE;
      	for (int i = left ; i <= right; i++) 
	{
            for (int j = i+1; j <= right; j++) 
	    {
              	min = Math.min(min, Math.sqrt((Math.pow(Math.abs(yVals[i].y - yVals[j].y), 2)) + (Math.pow(Math.abs(yVals[i].x - yVals[j].x), 2))));
            }
        }
        return min;
   }
   

   public java.lang.String getName()
   {
	return "DivideAndConquer";
   }

   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {
	return;
   }
   //main
   // call the initialize, insert the values, the call search, and call max and min
   public static void main (String[] argv)
   {
	DivideAndConquer dac = new DivideAndConquer();	
	edu.gwu.geometry.Pointd[] testPoints = new edu.gwu.geometry.Pointd[6];
	edu.gwu.geometry.Pointd p1 = new edu.gwu.geometry.Pointd(1.5, 22.0);
	edu.gwu.geometry.Pointd p2 = new edu.gwu.geometry.Pointd(17.5, 42.0);
	testPoints[0] = p1;
	testPoints[1] = p2;
	testPoints[2] = new edu.gwu.geometry.Pointd(11.5, 12.0);
	testPoints[3] = new edu.gwu.geometry.Pointd(10.5, 12.0);
	testPoints[4] = new edu.gwu.geometry.Pointd(30.5, 14.0);
	testPoints[5] = new edu.gwu.geometry.Pointd(13.5, 9.0);

	System.out.print(dac.findClosestPairDistance(testPoints));


   }

} 
