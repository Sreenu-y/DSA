class Solution {

    private int solve(int i, int currSum, int totalSum, int[] nums, int target, int[][] dp) {
        if(i == nums.length) {
            int remainingSum = totalSum - currSum;
            return currSum - remainingSum == target ? 1 : 0;
        }

        if(dp[i][currSum] != -1) return dp[i][currSum];


        int take = solve(i + 1, currSum + nums[i], totalSum, nums, target, dp);
        int notTake = solve(i + 1, currSum, totalSum, nums, target, dp);
        
        return dp[i][currSum] = take + notTake;
    }

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int totalSum = Arrays.stream(nums).sum();
        int[][] dp = new int[n][totalSum + 1];

        for(int[] row : dp) Arrays.fill(row, -1);

        return solve(0, 0, totalSum, nums, target, dp);
    }
}