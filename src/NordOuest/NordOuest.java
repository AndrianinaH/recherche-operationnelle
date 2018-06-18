package NordOuest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 18/06/2018.
 */
public class NordOuest {
    private int SourcesCount; // ligne
    private int DestinationsCount; //colonne
    private double[] supplies; //ligne
    private double[] demands; //colonne
    private double[][] costs;
    private double[][] solutions;
    private double Z;

    //---------- init
    public NordOuest(int sourcesCount, int destinationsCount, double[] supplies, double[] demands, double[][] costs) {
        this.SourcesCount = sourcesCount;
        this.DestinationsCount = destinationsCount;
        this.supplies = supplies;
        this.demands = demands;
        this.costs = costs;
    }

    public int getSourcesCount() {
        return SourcesCount;
    }

    public void setSourcesCount(int sourcesCount) {
        SourcesCount = sourcesCount;
    }

    public int getDestinationsCount() {
        return DestinationsCount;
    }

    public void setDestinationsCount(int destinationsCount) {
        DestinationsCount = destinationsCount;
    }

    public double[] getSupplies() {
        return supplies;
    }

    public void setSupplies(double[] supplies) {
        this.supplies = supplies;
    }

    public double[] getDemands() {
        return demands;
    }

    public void setDemands(double[] demands) {
        this.demands = demands;
    }

    public double[][] getCosts() {
        return costs;
    }

    public void setCosts(double[][] costs) {
        this.costs = costs;
    }

    public double[][] getSolutions() {
        return solutions;
    }

    public void setSolutions(double[][] solutions) {
        this.solutions = solutions;
    }

    public double getZ() {
        return Z;
    }

    public void setZ(double z) {
        Z = z;
    }

    //---------------------- Algorithme
    public void eligibleSolution() {
        solutions = new double[getSourcesCount()][getDestinationsCount()];
        for(int i=0; i<solutions.length; i++) {
            for (int j=0; j<solutions[0].length; j++) {
                if(supplies[i] > 0 && demands[j] > 0) {
                    if(demands[j] < supplies[i]) {
                        solutions[i][j] = demands[j];
                    } else {
                        solutions[i][j] = supplies[i];
                    }
                    supplies[i] -= solutions[i][j];
                    demands[j] -= solutions[i][j];
                } else {
                    solutions[i][j] = 0;
                }
            }
        }
    }

    public List lowestMC() {
        double marginalCost = 0;
        double value = 0;
        List ret = new ArrayList();
        for(int i=0; i<SourcesCount; i++) {
            for(int j=0; j<DestinationsCount; j++) {
                if(solutions[i][j] == 0) {
                    int[] point = new int[2];
                    point[0] = i;
                    point[1] = j;
                    List response = marginalCost(point);
                    double costValue = (double) response.get(0);
                    double tempVal = (double) response.get(1);
                    if(costValue < 0 && costValue <= marginalCost && value <= tempVal) {
                        marginalCost = costValue;
                        value = tempVal;
                        ret = response;
                    }
                }
            }
        }
        return ret;
    }

    public void updateSolutions(double value, List<int[]> liste) {
        for(int i=0; i<liste.size(); i++) {
            if(i%2 == 0) {
                solutions[liste.get(i)[0]][liste.get(i)[1]] += value;
            } else {
                solutions[liste.get(i)[0]][liste.get(i)[1]] -= value;
            }
        }
    }

    public List marginalCost(int[] point) {
        List ret = new ArrayList();
        List<int[]> liste = new ArrayList<>();
        double marginalCost = 0;
        double value = 0;
        for(int i=0; i<DestinationsCount; i++) {
            for(int j=0; j<SourcesCount; j++) {
                if(solutions[point[0]][i] > 0 && solutions[j][point[1]] > 0 ) {
                    double costValue = costs[point[0]][point[1]] - costs[point[0]][i] - costs[j][point[1]] + costs[j][i];
                    double tempVal = (solutions[point[0]][i] < solutions[j][point[1]]) ? solutions[point[0]][i] : solutions[j][point[1]];
                    if(costValue < 0 && costValue <= marginalCost && value <= tempVal) {
                        marginalCost = costValue;
                        value = tempVal;
                        liste.clear();
                        int[] point1 = point;
                        int[] point2 = {point[0], i};
                        int[] point3 = {j, i};
                        int[] point4 = {j, point[1]};
                        liste.add(point1);
                        liste.add(point2);
                        liste.add(point3);
                        liste.add(point4);
                    }
                }
            }
        }
        ret.add(marginalCost);
        ret.add(value);
        ret.add(liste);
        return ret;
    }

    public void run() {
        eligibleSolution();
        while (true) {
            List liste = lowestMC();
            if(liste.size() == 0) {
                break;
            }
            updateSolutions((double)liste.get(1), (List<int[]>) liste.get(2));
        }
        display();
    }

    public void display() {
        double value = 0;
        for(int i=0; i<solutions.length; i++ ){
            for(int j=0; j<solutions[0].length; j++) {
                value += solutions[i][j] * costs[i][j];
            }
        }
        System.out.println("Z = " + value);
        this.setZ(value);
    }


}
