class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        Map<Integer, Integer> lakes = new HashMap<>(); 
        TreeSet<Integer> dryDays = new TreeSet<>(); 
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for(int i = 0; i < n; i++) {
            int lake = rains[i];

            if(lake == 0) {
                dryDays.add(i);
            } else {
                ans[i] = -1;
                if(lakes.containsKey(lake)) {
                    Integer dryDay = dryDays.higher(lakes.get(lake)); // check if dryday present or not, to dry the lake before raining to avoid flooding
                    if(dryDay == null) {
                        return new int[0];
                    } 
                    ans[dryDay] = lake;
                    dryDays.remove(dryDay);
                }

                lakes.put(lake, i);
            }
        }
        return ans;
    }
}
