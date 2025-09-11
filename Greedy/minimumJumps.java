class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if(arr[0] == 0) return -1;
        int max = arr[0], jumps = arr[0], ans = 1;
        
        for(int i = 1; i < n; i++) {
            max--;
            jumps--;
            
            max = Math.max(max, arr[i]);
            
            if(jumps == 0 && i != n - 1) {
                if(max <= 0) {
                    return -1;
                }
                jumps = max;
                //max = 0;
                ans++;
            }
        }
        
        return ans;
    }
}
