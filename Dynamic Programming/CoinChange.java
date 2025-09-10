class Solution {
    private int solve(int i, int currAmount, int amount, int[] coins, int[][] dp) {
        if(currAmount > amount) return 0;
        if(i == coins.length) {
            return currAmount == amount ? 1 : 0;
        }

        if(dp[i][currAmount] != -1) return dp[i][currAmount];

        int take = solve(i, currAmount + coins[i], amount, coins, dp);
        int notTake = solve(i + 1, currAmount, amount, coins, dp);
        
        return dp[i][currAmount] = take + notTake;
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int total = Arrays.stream(coins).sum();
        int[][] dp = new int[n][amount + 1];

        for(int[] row : dp) Arrays.fill(row, -1);

        return solve(0, 0, amount, coins, dp);
    }
}
