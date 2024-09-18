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


        if (highwayCost > hospitalCost) {
            long i = (long)n * hospitalCost;
            return i;
        }
        int[] subgraphs = new int[n + 1];

        int c = n;

        for (int i = 0; i < cities.length; i++) {
            if(!(subgraphs[cities[i][1]] == cities[i][0])) {
                subgraphs[cities[i][1]] = cities[i][0];
                c--;
            }
        }
        return (c * (long)hospitalCost) + (n-c) * (long)highwayCost;

    }


}
