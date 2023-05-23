import java.lang.*;
import java.util.*;
import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;

public class MyAlgorithm implements GraphDisplayAlgorithm
{
   public Pointd[] displayGraphContinuous(double[][] adjMatrix)
   {
	edu.gwu.geometry.Pointd[] points = new edu.gwu.geometry.Pointd[adjMatrix.length];
	int minInter = 2147483647;
	for (int i = 0; i < 10000; i++) 
	{
	   
  	   int inter = 0;
	   Pointd[] graph = new Pointd[adjMatrix.length];
  	   for (int j = 0; j < adjMatrix.length; j++)
	   {
		graph[j] = new Pointd(UniformRandom.uniform(), UniformRandom.uniform());
	   }
	   for (int j = 0; j < adjMatrix.length;j++) 
	   {
		for (int k = 0; k < adjMatrix[j].length; k++) 
		{
		   if (adjMatrix[j][k] == 1) 
		   {
			for (int n = 0; n < adjMatrix.length / 2; n++)
			{
			   for (int m = 0; m < adjMatrix[n].length / 2; m++) 
			   {
				if (adjMatrix[n][m] == 1 && (n != j) && (m != k)) 
				{
			 	   boolean doesIntersect = Geometry.lineSegmentIntersection(graph[j], graph[k], graph[n], graph[m]);
				   if (doesIntersect) 
				   {
				   	inter++;
				   }
				}
			   }
			}
		   }
		}
	   }
	   if (inter < minInter)
	   {
		minInter = inter;
		points = graph;
	   }
	   
	}
	return points;
   }

   public java.util.LinkedList[][] displayGraphDiscrete(double[][] adjMatrix, int gridSize)
   {
	return null;
   }
   public java.lang.String getName()
   {
	return "MyAlgorithm";
   }

   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {
	return;
   }
   public static void main (String[] argv)
   {

   }
}
