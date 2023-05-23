import java.lang.*;
import java.util.*;
import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;

public class CircleAlgorithm implements GraphDisplayAlgorithm
{
   public Pointd[] displayGraphContinuous(double[][] adjMatrix)
   {
	int n = adjMatrix.length;
	edu.gwu.geometry.Pointd[] points = new edu.gwu.geometry.Pointd[n];

	double angle = 2 * Math.PI / n;
	for (int i = 0; i < n; i++) 
	{
  	   double xPos = Math.cos(angle * i);
  	   double yPos = Math.sin(angle * i);
  	   points[i] = new edu.gwu.geometry.Pointd(xPos, yPos);
	}
	return points;
   }

   public java.util.LinkedList[][] displayGraphDiscrete(double[][] adjMatrix, int gridSize)
   {
	return null;
   }
   public java.lang.String getName()
   {
	return "CircleAlgorithm";
   }

   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {
	return;
   }
   public static void main (String[] argv)
   {

   }
}
