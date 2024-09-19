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
        for (int i = 1; i < subgraphs.length; i++) {
            subgraphs[i] = i;
        }


        int c = n;

        for (int i = 0; i < cities.length; i++) {
            int leftCity = cities[i][0];
            int rightCity = cities[i][1];

            // Traverse up their respective graphs to find the root and set it equal to left and right
            while (subgraphs[leftCity] != leftCity) {
                leftCity = subgraphs[leftCity];
            }
            while (subgraphs[rightCity] != rightCity) {
                rightCity = subgraphs[rightCity];
            }


            if (leftCity != rightCity) {

                subgraphs[rightCity] = leftCity;
                c--;
            }
        }
        return (c * (long) hospitalCost) + (n - c) * (long) highwayCost;

    }


}
