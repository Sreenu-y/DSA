class FoodRatings {
    Map<String, Integer> foodRating = new HashMap<>();
    Map<String, String> foodCuisine = new HashMap<>();
    Map<String, TreeSet<Pair<Integer, String>>> cuisineRatingFood = new HashMap<>();
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;

        for(int i = 0; i < n; i++) {
            foodRating.put(foods[i], ratings[i]);
            foodCuisine.put(foods[i], cuisines[i]);

            cuisineRatingFood.computeIfAbsent(cuisines[i], k -> new TreeSet<>((a, b) -> {
                int compareByRating = Integer.compare(a.getKey(), b.getKey());

                if(compareByRating == 0) {
                    return a.getValue().compareTo(b.getValue());
                }

                return compareByRating;
            })).add(new Pair(-ratings[i], foods[i]));
        }
    }
    
    public void changeRating(String food, int newRating) {
        String cuisine = foodCuisine.get(food);
        TreeSet<Pair<Integer, String>> cuisineRatingSet = cuisineRatingFood.get(cuisine);
        Pair<Integer, String> oldRating = new Pair(-foodRating.get(food), food);
        cuisineRatingSet.remove(oldRating);
        cuisineRatingSet.add(new Pair(-newRating, food));
        foodRating.put(food, newRating);
    }
    
    public String highestRated(String cuisine) {
        Pair<Integer, String> highestRated =  cuisineRatingFood.get(cuisine).first();
        return highestRated.getValue();
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
