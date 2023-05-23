import java.util.*;

import edu.gwu.algtest.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;

public class Annealing implements MTSPAlgorithm {

    int algId;
    PropertyExtractor prop;

    double num = 100.0;
    int n;

    public int[][] computeTours(int m, Pointd[] points) {
        n = m;
        Naive naive = new Naive();
        int[][] assignedPoints = naive.computeTours(m, points);
        double tourCost = computeTotalCost(assignedPoints, points);
        while (num > 1.0) {
            int[][] newAssignedPoints = swapTour(assignedPoints, points);
           
            double tempCost = computeTotalCost(newAssignedPoints, points);
            
            if(tempCost < tourCost) {
                tourCost = tempCost;
                assignedPoints = newAssignedPoints;
            } else {
                boolean toss = coinToss(assignedPoints, newAssignedPoints, points);
                if (toss) {
                    tourCost = tempCost;
                    assignedPoints = newAssignedPoints;
                }
            }
            
            num = num - 1.0;
        }
        return assignedPoints;
    }

    public boolean coinToss(int[][] a, int[][] b, Pointd[] points) {
        double calc = Math.exp((-computeTotalCost(b, points) - computeTotalCost(a, points))/num);
        double rand = Math.random();
        
        if (rand < calc)
            return true;
        return false;
    }

    public int[][] swapTour(int[][] assignedPoints, Pointd[] points){
        int[][] duplicatePtsMatrix = assignedPoints;
        
        double rA = Math.random() * points.length;
        double rB = Math.random() * points.length;
        int intA = (int) rA;
        int intB = (int) rB;
        Pointd a = points[intA];
        Pointd b = points[intB];
        
        for (int i = 0; i < duplicatePtsMatrix.length; i++) {
            for (int j = 0; j < duplicatePtsMatrix[i].length; j++){
                if (assignedPoints[i][j] == getIndex(a,points)) {
                    duplicatePtsMatrix[i][j] = getIndex(b,points);
                }
                else if (assignedPoints[i][j] == getIndex(b,points)) {
                    duplicatePtsMatrix[i][j] = getIndex(a,points);
                }
            }
        }
        return duplicatePtsMatrix;
    }

    public double computeAvgDistance(Pointd p, Pointd[] points) {
        double avg = 0.0;
        for (int i = 0; i < points.length; i++) {
            avg = avg + computeDistance(p, points[i]);
        }
        avg = avg / points.length;
        return avg;
    }
    
    public double computeTotalCost(int[][] ptMatrix, Pointd[] points) {
        int[] costs = new int[ptMatrix.length];
        for (int i = 0; i < costs.length; i++) {
            costs[i] = 0;
        }
        for (int i = 0; i < ptMatrix.length; i++) {
            for (int j = 1; j < ptMatrix[i].length; j++) {
                costs[i] += computeDistance(points[ptMatrix[i][j-1]],points[ptMatrix[i][j]]);
            }
        }
        double total = 0.0;
        for (int i = 0; i < costs.length; i++) {
            total += costs[i];
        }
        return total;
    }

    public double computeDistance(Pointd a, Pointd b){
        double dX = a.x - b.x;
        double dY = a.y - b.y;
        double dist = Math.sqrt(dX*dX + dY*dY);
        return dist;
    }
    
  
    public int getIndex(Pointd p, Pointd[] points) {
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            if (p.equals(points[i])) {
                return count;
            }
            count++;
        }
        return -1;    
    }
    
    public String getName(){
        return "Annealing TSP";
    }
    
    public void setPropertyExtractor(int algId, PropertyExtractor prop){
        this.algId=algId;
        this.prop=prop;
    }

    public static void main(String[] args) {
		
		

	}

}
