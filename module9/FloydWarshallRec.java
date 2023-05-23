// Floyd-Warshall algorithm - recursive version (for illustration)

import java.util.*;
import java.io.*;


public class FloydWarshallRec {

    // Number of vertices (when initialized).
    int numVertices;                  
    
    // The adjacency matrix (given as input). Recall: adjMatrix[i][j]=0
    // if there's no edge between i and j.
    double[][] adjMatrix;              

    // Matrix used in dynamic programming.
    double[][] D;                      


    public void initialize (int numVertices)
    {
	this.numVertices = numVertices;
	D = new double [numVertices][numVertices];
    }

  
    // Recursive computation of D. k is the "k" in "S_k".

    double computeD (int k, int i, int j) 
    {
	// INSERT YOUR CODE HERE.
	if (k == -1)
	{
	   return adjMatrix[i][j];
	}
	else 
	{
	   D[i][j] = Math.min(computeD(k-1,i,j), (computeD(k-1,i, k) + computeD(k-1, k, j)));
	   return D[i][j];
	}
    }


    public void allPairsShortestPaths (double[][] adjMatrix)
    {
	this.adjMatrix = adjMatrix;
    
	for (int i=0; i<numVertices; i++) {
	    for (int j=0; j<numVertices; j++) {
		if (i != j) {
		    // D(n-1,i,j):
		    D[i][j] = computeD (numVertices-1, i, j);
		}
	    }
	}
	
	// ... (path construction not shown) ...

	// Print D
	for (int i=0; i<numVertices; i++) {
	    for (int j=0; j<numVertices; j++) {
		System.out.printf ("  %4.2f", D[i][j]);
	    }
	    System.out.println ();
	}

    }
  
  
    public static void main (String[] argv)
    {
	// A test case.
	double[][] adjMatrix = {
	    {0, 1, 7, 0, 5, 0, 1},
	    {1, 0, 1, 0, 0, 7, 0},
	    {7, 1, 0, 1, 7, 0, 0},
	    {0, 0, 1, 0, 1, 0, 0},
	    {5, 0, 7, 1, 0, 1, 0},
	    {0, 7, 0, 0, 1, 0, 1},
	    {1, 0, 0, 0, 0, 1, 0},
	};

	int n = adjMatrix.length;
	FloydWarshallRec fwAlg = new FloydWarshallRec ();
	fwAlg.initialize (n);
	fwAlg.allPairsShortestPaths (adjMatrix);
      

	// ... print paths (not shown) ...
  }

}
