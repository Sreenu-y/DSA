//problem link: https://leetcode.com/problems/points-that-intersect-with-cars/
class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] hash = new int[102];

        for(List<Integer> num : nums) {
            hash[num.get(0)]++;
            hash[num.get(1) + 1]--;
        }

        int count = 0;
        for(int i = 1; i < 102; i++) {
            hash[i] += hash[i - 1];
            if(hash[i] > 0) count++;
        }

        return count;
    }
}
