/*0/1 knapSack using bottom up (tabulation)*/

class Solution {

    static int knapsack(int W, int val[], int wt[]) {
        /*Approach - 1 (Space   O(n * W) )*/
        //using 2d dp
        int n = wt.length;
        int[][] dp = new int[n][W + 1];
        for(int i = wt[0]; i <= W; i++) {
            dp[0][i] = val[0];
        }
        
        for(int i = 1; i < n; i++) {
            for(int w = 0; w <= W; w++) {
                int notTake = dp[i - 1][w];
                int take = Integer.MIN_VALUE;
                
                if(wt[i] <= w) {
                    take = val[i] + dp[i - 1][w - wt[i]];
                }
                
                dp[i][w] = Math.max(take, notTake);
            }
        }
        
        return dp[n - 1][W];
        
        
        /*Approach - 2 (Space Optimized from O(n * W) to O(W))*/
        
        //using two W + 1 arrays
        int n = wt.length;
        int[] prev = new int[W + 1];
        int[] curr = new int[W + 1];
        for(int i = wt[0]; i <= W; i++) {
            prev[i] = val[0];
        }
        
        for(int i = 1; i < n; i++) {
            for(int w = 0; w <= W; w++) {
                int notTake = prev[w];
                int take = Integer.MIN_VALUE;
                
                if(wt[i] <= w) {
                    take = val[i] + prev[w - wt[i]];
                }
                
                curr[w] = Math.max(take, notTake);
            }
            
            prev = curr.clone();
        }
        
        return prev[W];
    }
}
