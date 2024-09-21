

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

        // If it cheaper to build hospitals, do it in every city
        if (highwayCost > hospitalCost) {
            return  (long) n * hospitalCost;

        }

        // Map for each city and its root
        int[] subgraphs = new int[n + 1];
        // Keeps track of the number of subgraphs
        int clusters = n;

        // For every possible edge
        for (int i = 0; i < cities.length; i++) {
            // Assign variables to keep track of the current edge
            int leftCity = cities[i][0];
            int rightCity = cities[i][1];

            // Traverse up the subgraph until leftCity equals its original cities root
            while (subgraphs[leftCity] > 0) {
                leftCity = subgraphs[leftCity];
            }
            // Traverse up the subgraph assigning each node the root we found in the previous while loop
            while (subgraphs[cities[i][0]] > 0) {
                int temp = subgraphs[cities[i][0]];
                subgraphs[cities[i][0]] = leftCity;
                cities[i][0] = temp;
            }
            // Same two for loops but for right city
            while (subgraphs[rightCity] > 0) {
                rightCity = subgraphs[rightCity];
            }
            while (subgraphs[cities[i][1]] > 0) {
                int temp = subgraphs[cities[i][1]];
                subgraphs[cities[i][1]] = rightCity;
                cities[i][1] = temp;
            }
            // If they aren't in the same subgraph
            // I think it is slightly more efficient for this to be the first check
            if(leftCity != rightCity) {
                // If the left root is of higher order than the right root
                if (subgraphs[leftCity] < subgraphs[rightCity]) {
                    // adjust the order of the root so it accounts for the new vertices
                    subgraphs[leftCity] += subgraphs[rightCity] - 1;
                    // decrease the amount of subgraphs and assign the left to be the root of the right
                    clusters--;
                    subgraphs[rightCity] = leftCity;
                } else {
                    // Same as above but for the right city
                    subgraphs[rightCity] += subgraphs[leftCity] - 1;
                    clusters--;
                    subgraphs[leftCity] = rightCity;
                }
            }
        }
        // Returns subgraphs number of hospitals and number of cities minus the number of subgraphs highways
        return (clusters * (long) hospitalCost) + (n - clusters) * (long) highwayCost;

    }


}
