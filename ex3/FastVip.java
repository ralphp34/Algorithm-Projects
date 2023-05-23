import java.lang.*;
import java.util.*;
import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*;


public class FastVIP implements VIPAlgorithm
{
   public boolean VIPexists(VIPProblemData data)
   {  
   	int n = data.numPeople();
	Stack<Integer> st = new Stack<>(); 
        int c; 

        for (int i = 0; i < n; i++)  
        { 
            st.push(i); 
        } 
  
        while (st.size() > 1)  
        {  
            int i = st.pop(); 
            int j = st.pop(); 

            if (data.knows(i, j))  
            { 
                st.push(j); 
            } 
  
            else
                st.push(i); 
        } 
  
        c = st.pop(); 
  
        for (int k = 0; k < n; k++)  
        { 
            if (k != c && (data.knows(c, k) || !data.knows(k, c))) 
                return false; 
        } 
        return true; 
   }

   public java.lang.String getName()
   {
	return "FastVIP";
   }

   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {
	return;
   }

   public static void main (String[] argv)
   {	

   }
}
