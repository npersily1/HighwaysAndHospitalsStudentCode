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
            long i = (long) n * hospitalCost;
            return i;
        }
        int[] subgraphs = new int[n + 1];



        int c = n;

        for (int i = 0; i < cities.length; i++) {
            int leftCity = cities[i][0];
            int rightCity = cities[i][1];

            // Traverse up their respective graphs to find the root and set it equal to left and right
            while (subgraphs[leftCity] > 0) {
                leftCity = subgraphs[leftCity];
            }
            while (subgraphs[cities[i][0]] > 0) {
                int temp = subgraphs[cities[i][0]];
                subgraphs[cities[i][0]] = leftCity;
                cities[i][0] = temp;
            }
            while (subgraphs[rightCity] > 0) {
                rightCity = subgraphs[rightCity];
            }
            while (subgraphs[cities[i][1]] > 0) {
                int temp = subgraphs[cities[i][1]];
                subgraphs[cities[i][1]] = rightCity;
                cities[i][1] = temp;
            }
            if(leftCity != rightCity) {
                // If the left root is of higher order than the right root
                if (subgraphs[leftCity] < subgraphs[rightCity]) {
                    subgraphs[leftCity] += subgraphs[rightCity] - 1;
                    c--;
                    subgraphs[rightCity] = leftCity;
                } else {
                    subgraphs[rightCity] += subgraphs[leftCity] - 1;
                    c--;
                    subgraphs[leftCity] = rightCity;
                }
            }
        }
        return (c * (long) hospitalCost) + (n - c) * (long) highwayCost;

    }


}
