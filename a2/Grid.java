import java.lang.*;
import java.util.*;
import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*;

public class Grid implements ClosestPairAlgorithm
{
   public double findClosestPairDistance(edu.gwu.geometry.Pointd[] points)
   {
	//find how to split up the points. find first and last data and split it up by region
	if (points.length == 2)
	{
	   return Math.sqrt((Math.pow(Math.abs(points[1].y - points[0].y), 2)) + (Math.pow(Math.abs(points[1].x - points[0].x), 2)));
	}
	double maxX = 0;	
	double maxY = 0;
	for (int i = 0; i < points.length; i++)
	{
	    if (points[i].x  > maxX)
	    {
		maxX = points[i].x;
	    }
	    if (points[i].y  > maxY)
	    {
		maxY = points[i].y;
	    }

	}
	int interval = points.length;
	double yInt = (maxY + 1.0)/ (double) interval;
	double xInt = (maxX + 1.0)/ (double) interval;	

	LinkedList<edu.gwu.geometry.Pointd>[][] grid = new LinkedList[interval][interval];
	// create grid 
	for (int i = 0; i < grid.length; i++)
	{
 	    for (int j = 0; j < grid[i].length; j++)
 	    {
		grid[i][j] = new LinkedList<edu.gwu.geometry.Pointd>();
 	    }
 	}

	//go through all of the points and put them in the correct cell
	 for (int k = 0; k < points.length; k++)
	 {
	    for (int i = 0; i < grid.length; i++)
	    {
		for (int j = 0; j < grid[i].length; j++)
		{

		    if (((i * xInt <= points[k].x) && (points[k].x < (i+1) * xInt)) && ((j * yInt <= points[k].y) && (points[k].y < (j+1) * yInt)))
		    {
			grid[i][j].add(points[k]);
		    }

		}
	    } 
	}
	    for (int i = 0; i < grid.length; i++)
	    {
		for (int j = 0; j < grid[i].length; j++)
		{
		   for (int k = 0; k < grid[i][j].size(); k++)
		   {
			//System.out.print("grid[" + i + "][" + j + "] = " + grid[i][j].get(k));
		   }
		   //System.out.println();
		}

	    } 
	//System.out.println("x interval = " + xInt + ", y interval = " + yInt);

	double smallest_dist = Integer.MAX_VALUE;	
	// put them in a linked list 

	// compare values in each cell and the ones close by the find distance
	for (int i = 0; i < grid.length; i++)
	{
	    for (int j = 0; j < grid[i].length; j++)
	    {
		for (int k = 0; k < grid[i][j].size(); k++)
		{
		    for (int p = k+1; p < grid[i][j].size(); p++)
		    {
			if (grid[i][j].size() != 0)
			{
			    //System.out.println("i = " + i + ", j = " + j);			    
			    if (Math.sqrt((Math.pow(Math.abs(grid[i][j].get(k).y - grid[i][j].get(p).y), 2)) + (Math.pow(Math.abs(grid[i][j].get(k).x - grid[i][j].get(p).x), 2))) < smallest_dist)
			    {
				//System.out.println("do I get here");
			    	smallest_dist = Math.sqrt((Math.pow(Math.abs(grid[i][j].get(k).y - grid[i][j].get(p).y), 2)) + (Math.pow(Math.abs(grid[i][j].get(k).x - grid[i][j].get(p).x), 2)));
			    }
			}
		    }
		}
		for (int k = 0; k < grid[i][j].size(); k++)
		{

		    for (int p = 0; p < grid[i][j].size(); p++)
		    {
			//System.out.println("i = " + i + ", j = " + j);

			//System.out.println("k = " + k + ", p = " + p);
			if (i+1 < grid.length)
			{
			if (grid[i+1][j].size() != 0 && grid[i][j].size() !=0)
			{
			    if (Math.sqrt((Math.pow(Math.abs(grid[i][j].get(k).y - grid[i+1][j].get(p).y), 2)) + (Math.pow(Math.abs(grid[i][j].get(k).x - grid[i+1][j].get(p).x), 2))) < smallest_dist)
			    {
			    	smallest_dist = Math.sqrt((Math.pow(Math.abs(grid[i][j].get(k).y - grid[i+1][j].get(p).y), 2)) + (Math.pow(Math.abs(grid[i][j].get(k).x - grid[i+1][j].get(p).x), 2)));
			    }
			}
			}
			if (j+1 < grid.length)
			{
			if (grid[i][j+1].size() != 0 && grid[i][j].size() != 0)
			{
			    if (Math.sqrt((Math.pow(Math.abs(grid[i][j].get(k).y - grid[i][j+1].get(p).y), 2)) + (Math.pow(Math.abs(grid[i][j].get(k).x - grid[i][j+1].get(p).x), 2))) < smallest_dist)
			    {
			    	smallest_dist = Math.sqrt((Math.pow(Math.abs(grid[i][j].get(k).y - grid[i][j+1].get(p).y), 2)) + (Math.pow(Math.abs(grid[i][j].get(k).x - grid[i][j+1].get(p).x), 2)));
			    }
			}
			}
			if (i+1 < grid.length && j+1 < grid.length)
			{
			if (grid[i+1][j+1].size() != 0 && grid[i][j].size() != 0)
			{
			    if (Math.sqrt((Math.pow(Math.abs(grid[i][j].get(k).y - grid[i+1][j+1].get(p).y), 2)) + (Math.pow(Math.abs(grid[i][j].get(k).x - grid[i+1][j+1].get(p).x), 2))) < smallest_dist)
			    {
			    	smallest_dist = Math.sqrt((Math.pow(Math.abs(grid[i][j].get(k).y - grid[i+1][j+1].get(p).y), 2)) + (Math.pow(Math.abs(grid[i][j].get(k).x - grid[i+1][j+1].get(p).x), 2)));
			    }
			}
			}

		    }
		   
		}
	    }
	}


	return smallest_dist;
   }

   public java.lang.String getName()
   {
	return "Grid";
   }

   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {
	return;
   }
   //main
   // call the initialize, insert the values, the call search, and call max and min
   public static void main (String[] argv)
   {
	Grid g = new Grid();	
	edu.gwu.geometry.Pointd[] testPoints = new edu.gwu.geometry.Pointd[6];
	testPoints[0] = new edu.gwu.geometry.Pointd(2, 5);
	testPoints[1] = new edu.gwu.geometry.Pointd(7, 5);
	testPoints[2] = new edu.gwu.geometry.Pointd(8, 5);
	testPoints[3] = new edu.gwu.geometry.Pointd(17.5, 42.0);
	testPoints[4] = new edu.gwu.geometry.Pointd(30.5, 14.0);
	testPoints[5] = new edu.gwu.geometry.Pointd(13.5, 9.0);

	System.out.print(g.findClosestPairDistance(testPoints));
   }

} 
