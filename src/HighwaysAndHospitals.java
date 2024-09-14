import java.util.ArrayList;

/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 * <p>
 * Completed by: [YOUR NAME HERE]
 */

public class HighwaysAndHospitals {

    /**
     * TODO: Complete this function, cost(), to return the minimum cost to provide
     *  hospital access for all citizens in Menlo County.
     */
    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {

        long numConnectedCities = 0;
        if (highwayCost > hospitalCost) {
            return n * hospitalCost;
        }
        ArrayList<ArrayList<Integer>> subgraphs = new ArrayList<ArrayList<Integer>>();
        // Seperate into subgraphs
        for (int i = 0; i < cities.length; i++) {
            int temp1 = isInSubgraph(cities[i][0], subgraphs);
            int temp2 = isInSubgraph(cities[i][1], subgraphs);

            // If its a new subgraph
            if ( temp1 < 0 && temp2 < 0) {
                // Create new subgraph with 2 new elements
                subgraphs.add(new ArrayList<Integer>());
                subgraphs.get(subgraphs.size() - 1).add(cities[i][0]);
                subgraphs.get(subgraphs.size() - 1).add(cities[i][1]);

                numConnectedCities += 2;
            }
            // if temp1 is marked down
            else if (temp1 >= 0 && temp2 < 0) {
                subgraphs.get(temp1).add(cities[i][1]);
                numConnectedCities += 1;
            }
            // if temp two is marked down
            else if (temp2 >= 0 && temp1 < 0) {
                subgraphs.get(temp2).add(cities[i][0]);
                numConnectedCities += 1;
            }

            else if(temp1 != temp2){
                subgraphs.get(temp1).addAll(subgraphs.get(temp2));
                subgraphs.remove(temp2);
            }
        }

        //  for each subgraph add the cost of hospital + (n-1) * highway cost to the total sum


        long sum = (hospitalCost * subgraphs.size()) +  (n - numConnectedCities) * hospitalCost;
        for (int i = 0; i < subgraphs.size(); i++) {
            sum += (subgraphs.get(i).size() - 1) * highwayCost;
        }
        return sum;
    }

    public static int isInSubgraph(int n, ArrayList<ArrayList<Integer>> subgraphs) {

        for (int i = 0; i < subgraphs.size(); i++) {
            if (subgraphs.get(i).contains(n)) {
                return i;
            }
        }
        return -1;
    }
}
