public class Cuisine {
    private String cuisine;

    public Cuisine(String entry) {
        if (entry.contains("\"cuisine\"")) {
            this.cuisine = entry.substring((entry.indexOf("cuisine") + 9), (entry.indexOf("\", \"grades\"") + 1));
            this.cuisine = this.cuisine.replaceAll("\"", "");
            this.cuisine = this.cuisine.trim();
        }
    }

    public boolean isEmpty() {
        return this.cuisine == null;
    }

    @Override
    public String toString() {
        return "Cuisine: " + this.cuisine;
    }

    // Getters and Setters

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
