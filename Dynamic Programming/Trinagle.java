//Leetcode 120. Triangle
class Solution {

    // private static int solve(List<List<Integer>> triangle, int i, int j, int[][] dp) {
    //     if(i == 0) {
    //         return 0;
    //     }

    //     if(dp[i][j] != -1) {
    //         return dp[i][j];
    //     }

    //     int down = triangle.get(i).get(j) + solve(triangle, i + 1, j, dp);
    //     int diagonal = triangle.get(i).get(j) + solve(triangle, i + 1, j + 1, dp);

    //     return dp[i][j] = Math.min(down, diagonal);
    // }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][];
        for (int i = 0; i < n; i++) {
            dp[i] = new int[i + 1];
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = triangle.get(0).get(0);
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                int down = j >= dp[i - 1].length ? Integer.MAX_VALUE : triangle.get(i).get(j) + dp[i - 1][j];
                int diag = j - 1 < 0 ? Integer.MAX_VALUE : triangle.get(i).get(j) + dp[i - 1][j - 1];
                dp[i][j] = Math.min(down, diag);
            }
        }

        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            minDist = Math.min(minDist, dp[n - 1][i]);
        }

        return minDist;
    }
}
