class Solution {
    private boolean isVowel(char ch) {
        return "AEIOUaeiou".indexOf(ch) != -1;
    }
    public String sortVowels(String s) {
        Map<Character, Integer> mpp = new HashMap<>();

        for(char ch : s.toCharArray()) {
            if(isVowel(ch)) {
                mpp.put(ch, mpp.getOrDefault(ch, 0) + 1);
            }
        }

        String str = "AEIOUaeiou";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(char ch : s.toCharArray()) {
            if(isVowel(ch)) {
                while(i < 10) {
                    char c = str.charAt(i);
                    if(mpp.containsKey(c)) {
                        sb.append(c);
                        mpp.put(c, mpp.get(c) - 1);
                        if(mpp.get(c) == 0) mpp.remove(c);
                        break;
                    }
                    i++;
                }
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
