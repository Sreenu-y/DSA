class MovieRentingSystem {
    class Pair {
        int price, shop;
        Pair(int price, int shop) {
            this.price = price;
            this.shop = shop;
        }
    }
    class Tuple {
        int price, shop, movie;
        Tuple(int price, int shop, int movie) {
            this.price = price;
            this.shop = shop;
            this.movie = movie;
        }
    }

    Map<Integer, TreeSet<Pair>> available;
    Map<Integer, Map<Integer, Integer>> movieToShop;
    Set<Tuple> rentedMovies;
    

    public MovieRentingSystem(int n, int[][] entries) {
        available    = new HashMap<>();
        movieToShop  = new HashMap<>();
        rentedMovies = new TreeSet<>((a, b) -> {
            if(a.price != b.price) {
                return a.price - b.price;
            } else if(a.shop != b.shop) {
                return a.shop - b.shop;
            } else {
                return a.movie - b.movie;
            }
        });
        for(int[] entry : entries) {
            int shop  = entry[0];
            int movie = entry[1];
            int price = entry[2];

            available.computeIfAbsent(movie, k -> new TreeSet<>((a, b) -> {
                if(a.price != b.price) {
                    return a.price - b.price;
                }
                return a.shop - b.shop;
            })).add(new Pair(price, shop));

            movieToShop.computeIfAbsent(movie, k -> new HashMap<>()).put(shop, price);
        }
    }
    
    public List<Integer> search(int movie) {
        int count = 0;
        List<Integer> result = new ArrayList<>();

        if(!available.containsKey(movie)) {
            return result;
        }

        for(Pair it : available.get(movie)) {
            result.add(it.shop);
            count++;
            if(count >= 5) break;
        }

        return result;

    }
    
    public void rent(int shop, int movie) {
        int price = movieToShop.get(movie).get(shop); // {2 -> {0, 5}} => {movie -> {shop, price}}

        available.get(movie).remove(new Pair(price, shop));
        rentedMovies.add(new Tuple(price, shop, movie));
    }
    
    public void drop(int shop, int movie) {
        int price = movieToShop.get(movie).get(shop);
        available.get(movie).add(new Pair(price, shop));

        rentedMovies.remove(new Tuple(price, shop, movie));
    }
    
    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;
        
        for(Tuple it : rentedMovies) {
            result.add(Arrays.asList(it.shop, it.movie));
            count++;
            if(count >= 5) break;
        }

        return result;
    }
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */
