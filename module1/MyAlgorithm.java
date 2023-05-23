
public class MyAlgorithm {

  public double findLargestDistance (Pointd[] points)
  {
      // INSERT YOUR CODE HERE
	double largest_dist = 0;
	
	for (int i = 0; i < points.length; i++)
	{
	   for (int j = i+1; j < points.length; j++)
	   {
		if (Math.sqrt((Math.pow(Math.abs(points[i].y - points[j].y), 2)) + (Math.pow(Math.abs(points[i].x - points[j].x), 2))) > largest_dist)
		{
		   largest_dist = Math.sqrt((Math.pow(Math.abs(points[i].y - points[j].y), 2)) + (Math.pow(Math.abs(points[i].x - points[j].x), 2))); 
		}
		System.out.print("x");
	   }
	   System.out.println();
  	}
	return largest_dist;
  }
}
