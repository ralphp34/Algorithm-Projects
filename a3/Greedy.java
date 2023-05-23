import java.util.*;
import edu.gwu.algtest.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;

public class Greedy implements MTSPAlgorithm {
    
    int algId;
    PropertyExtractor prop;

    public int[][] computeTours(int m, Pointd[] points) 
    {
        
        int[][] A = new int[m][];
        int total = 0;
        double min = computeDistance(points[0], points[1]);
        int avg = points.length / m;
        
        int counter = 0;
        for (int i = 0; i < m-1; i++){
            A[i] = new int[avg];
            counter += avg;
        }
        A[m-1] = new int[points.length-counter];
        
        LinkedList<Pointd> pointList = new LinkedList<Pointd>();
        for(int i=0;i<points.length;i++){
            pointList.add(points[i]);
        }
       
        Pointd currentPoint = points[0];
        
        for (int i = 0; i < m-1; i++){
            int tourLength = 0;
            while (tourLength < A[i].length) {
                if (currentPoint == points[0]) {
                    A[i][tourLength] = 0;
                    tourLength++;
                }
                int ind = getListIndex(currentPoint, pointList);
                pointList.remove(ind);
                Pointd nearestNeighbor = nearestNeighbor(currentPoint, pointList);

                A[i][tourLength] = getIndex(nearestNeighbor,points);
                tourLength++;
                total++;
                currentPoint = nearestNeighbor;
            }
            
        }
       
        int tourLengthLast = 0;
        int tourLength = 0;
       
        while (tourLengthLast < A[m-1].length && pointList.size() > 1) {
            int ind = getListIndex(currentPoint, pointList);
            pointList.remove(ind);
            Pointd nearestNeighbor = nearestNeighbor(currentPoint, pointList);

            A[m-1][tourLength] = getIndex(nearestNeighbor, points);
            tourLength++;
            total++;
            currentPoint = nearestNeighbor;
        }
        return A;
    }

    
    public int getListIndex(Pointd point, LinkedList<Pointd> list) {
        LinkedList<Pointd> duplicateList = new LinkedList<>();
        for(Pointd p : list) {
            duplicateList.add(p);
        }
        int count = 0;
        Pointd inquiry = duplicateList.peek();

        while (duplicateList.size() > 0) {
            inquiry = duplicateList.peek();
            if (point.y == inquiry.y && point.x == inquiry.x) {
                return count;
            }
            inquiry = duplicateList.poll();
            count++;
        }
        return -1;
    }
    

    public Pointd nearestNeighbor(Pointd point, LinkedList<Pointd> list) {
        Pointd nearestNeighbor = null;
        double minCost = Double.MAX_VALUE;
        LinkedList<Pointd> duplicateList = new LinkedList<>();
        for (Pointd y : list) {
            duplicateList.add(y);
        }
        int count = 0;
        while (duplicateList.size() != 0) {
            Pointd query = duplicateList.poll();
            if (computeDistance(point, query) < minCost) {
                nearestNeighbor = query;
                minCost = computeDistance(point, query);
            }
        }
        return nearestNeighbor;
    }

    public double computeDistance(Pointd a, Pointd b){
        double dX = a.x - b.x;
        double dY = a.y - b.y;
        double dist = Math.sqrt(dX*dX + dY*dY);
        return dist;
    }

    public int getIndex(Pointd p, Pointd[] points){
        int count=0;
        for(int i=0; i < points.length; i++){
            if (p.equals(points[i]))
                return count;
            count++;
        }
        return -1;
    }


    public String getName(){
        return "Greedy TSP";
    }
    
    public void setPropertyExtractor(int algId, PropertyExtractor prop){
        this.algId=algId;
        this.prop=prop;
    }

    public static void main(String[] args) {
		
		

	}

    


}
