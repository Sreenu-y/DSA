// Problem Link: https://leetcode.com/problems/maximum-population-year/description/
class Solution {
    public int maximumPopulation(int[][] logs) {
        int[] hash = new int[102];
        for(int[] log : logs) {
            hash[log[0] - 1950]++;
            hash[log[1] - 1950]--;
        }

        int max = 0;
        int maxYear = -1;
        for(int i = 0; i < 101; i++) {
            hash[i] += (i > 0 ? hash[i - 1] : 0);
            if(hash[i] > max) {
                max = hash[i];
                maxYear = i + 1950;
            }
        }

        return maxYear;
    }
}
