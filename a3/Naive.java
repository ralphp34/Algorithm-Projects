import java.util.*;

import edu.gwu.algtest.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;


public class Naive implements MTSPAlgorithm 
{

   int algId;
   PropertyExtractor prop;
    
   public int[][] computeTours(int m, Pointd[] points)
   {
	int[][] A = new int[m][];
        int count = 0;
        int avg = points.length / m;
        for (int i = 0; i < m-1; i++) 
	{
           A[i] = new int[avg];
           count += avg;
        }
        A[m-1] = new int[points.length - count];
        count = 0;
        for(int i = 0; i < m; i++)
	{
           for(int j = 0; j < A[i].length; j++)
	   {
                A[i][j] = count;
		count++;
           }
        }
        return A;
    }
    
    public String getName(){
        return "Naive TSP";
    }
    
    public void setPropertyExtractor(int algId, PropertyExtractor prop){
        this.algId=algId;
        this.prop=prop;
    }
    public static void main(String[] args) 
    { 
		
		

    }
}
