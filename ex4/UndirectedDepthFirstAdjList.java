import edu.gwu.util.*;
import edu.gwu.algtest.*;
import edu.gwu.debug.*;

import java.util.*;

public class UndirectedDepthFirstAdjList implements UndirectedGraphSearchAlgorithm {

	public int vertices; 
	public int edges; 
	public boolean isWeighted; 
	public boolean matrix = false; 
	public double [][] adjMatrix; 
	public int[] visitOrder;                 
	public int visitCount;                   
	public int[] completionOrder;            
	public int completionCount;              

	public List<LinkedList<GraphEdge>> adjList;
	public int[] componentLabel;
	public int currentComponentLabel;

	public void initialize(int vertices, boolean isWeighted) {
		this.vertices = vertices;
		this.isWeighted = true;

		visitOrder = new int[vertices];
		completionOrder = new int[vertices];
		componentLabel = new int[vertices];

		adjMatrix = new double[vertices][vertices];
		adjList = new ArrayList<LinkedList<GraphEdge>>(vertices);

		initSearch();

	}

	public UndirectedDepthFirstAdjList (int vertices) {
		initialize(vertices, false);
	}

	public UndirectedDepthFirstAdjList() {}


	public void insertUndirectedEdge(int startVertex, int endVertex, double weight) {
		GraphEdge e = new GraphEdge(startVertex, endVertex, weight);

		if (adjList.get(startVertex).indexOf(e) != -1) {
			return;
		}

		adjList.get(startVertex).add(new GraphEdge(startVertex, endVertex, weight));
		adjList.get(endVertex).add(new GraphEdge(endVertex, startVertex, weight));
	}

	public int[] depthFirstVisitOrder() {		
		depthFirstList();
		return visitOrder;
	}

	public int[] depthFirstCompletionOrder() {
		depthFirstList();

		return completionOrder;
	}

	public int numConnectedComponents() {
		depthFirstList();
		int max = -1;

		for (int i = 0; i < vertices; i++) {
			if (componentLabel[i] > max) {
				max = componentLabel[i];
			}
		}
		return max+1;
	}

	public int[] componentLabels() {		
		depthFirstList();
		return componentLabel;
	}

	public void initSearch() {
    		visitCount = -1;
    		completionCount = -1;
    		currentComponentLabel = -1;
    		for (int i=0; i < vertices; i++) {
      			visitOrder[i] = -1;
      			completionOrder[i] = -1;
      			componentLabel[i] = -1;
      			adjList.add(new LinkedList<>());
      			for (int j = 0; j < vertices; j++) {
      				adjMatrix[i][j] = -1;
      			}
    		}
  	}

  	// List implementation of depth-first search.
  	public void depthFirstList() {
    		initSearch ();
    		for (int i=0; i < vertices; i++) {
      			if (visitOrder[i] < 0) {
      				currentComponentLabel++;
      				componentLabel[i] = currentComponentLabel;
        			depthFirstListRecursive (i);
      			}
    		}
  	}

  	// Recursive visiting of vertices starting from a vertex. 
  	public void depthFirstListRecursive(int v) {
    		visitCount++;
    		visitOrder[v] = visitCount;

    		LinkedList<GraphEdge> vtxs = adjList.get(v);

    		for (GraphEdge vtx: vtxs) {    	
    			int i = vtx.endVertex;
    			if (visitOrder[i] == -1) {
    				depthFirstListRecursive(i);
    				componentLabel[i] = currentComponentLabel;
    			}
    		}

    		completionCount++;
    		completionOrder[v] = completionCount;
  	}

  	// Random coin flip
  	public static boolean randomCoinFlip (double p) {
    		if (UniformRandom.uniform() < p) {
      			return true;
		}
    		else {
      			return false;
		}
  	}

  	public static int coinFlipGraphComponents (int vertices, double p) {
  		UndirectedDepthFirstAdjList g = new UndirectedDepthFirstAdjList(vertices);

  		for (int i = 0; i < vertices; i++) {
  			for (int j = i+1; j < vertices; j++) {
  				if (randomCoinFlip(p)) {
  					g.insertUndirectedEdge(i, j, 1);
				}
  			}
  		}
  		int a = g.numConnectedComponents();
  		return a;
  	}

	public String getName() {
		return "UndirectedDepthFirstAdjList";
	}

   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop) 
   {
   }
   
   public int[] breadthFirstVisitOrder() 
   {
	return null;
   }
	public boolean existsCycle() {return false;}
	
   public int[] articulationVertices() 
   {
	return null;
   }
	
   public GraphEdge[] articulationEdges() {return null;}
	
   public boolean existsOddCycle() 
   {
	return false;
   }

   public static void main(String[] args) 
   {
	Test.testDFS();
	Test.testCoinFlip();
   }
	private static class Test {
		public static boolean testDFS() {
			boolean success = false;
			UndirectedDepthFirstAdjList graph = new UndirectedDepthFirstAdjList(10);
			graph.insertUndirectedEdge(0, 1, 1);
			graph.insertUndirectedEdge(0, 2, 1);
			graph.insertUndirectedEdge(1, 2, 1);
			graph.insertUndirectedEdge(3, 4, 1);
			graph.insertUndirectedEdge(3, 5, 1);
			graph.insertUndirectedEdge(4, 6, 1);
			graph.insertUndirectedEdge(5, 6, 1);
			graph.insertUndirectedEdge(7, 8, 1);
			graph.insertUndirectedEdge(7, 9, 1);
			graph.insertUndirectedEdge(8, 9, 1);
			if (graph.numConnectedComponents() == 3)
				success = true;
			else {
				System.out.println("Test(s) have failed!");
				return false;
			}
			graph.insertUndirectedEdge(2, 3, 1);
			if (graph.numConnectedComponents() == 2)
				success = true;
			else {
				System.out.println("Test(s) have failed!");
				return false;
			}
			graph.insertUndirectedEdge(5, 7, 1);
			if (graph.numConnectedComponents() == 1)
				success = true;
			else {
				System.out.println("Test(s) have failed!");
				return false;
			}

			System.out.println("All tests passed!");
			return success;
		}

		public static void testCoinFlip() {
			// Declare array to store average number of components for values 0.1, 0.2, ..., 0.9
			double[] average = new double[20];
			double p;
			for (int i = 1; i < 20; i++) {
				p = (double) i / 20.0;
				int sum = 0;
				for (int j = 0; j < 1000; j++) {
					int n = 20;
					int numComponents = UndirectedDepthFirstAdjList.coinFlipGraphComponents(n, p);
					sum += numComponents;
				}
				average[i] = (double) sum / 1000.0;
				System.out.println("Number of components: " + average[i] + "\tfor p = " + p);
			}
		}

	}

}
