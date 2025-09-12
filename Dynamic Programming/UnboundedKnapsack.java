class Solution {
    static int knapSack(int val[], int wt[], int capacity) {
        int n = wt.length;
        int[][] dp = new int[n][capacity + 1];
        
        for(int w = 0; w <= capacity; w++) {
            dp[0][w] = (int)(w / wt[0]) * val[0];
        }
        
        for(int i = 1; i < n; i++) {
            for(int w = 0; w <= capacity; w++) {
                int notTake = dp[i - 1][w];
                int take = 0;
                if(wt[i] <= w) {
                    take = val[i] + dp[i][w - wt[i]];
                }
                dp[i][w] = Math.max(take, notTake);
            }
        }
        
        return dp[n - 1][capacity];
    }
}
