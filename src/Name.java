public class Name {
    private String name;

    public Name(String entry) {
        if (entry.contains("\"name\"")) {
            this.name = entry.substring(entry.indexOf("name") + 6, entry.indexOf(",", entry.indexOf("name")));
            this.name = this.name.replaceAll("\"", "");
            this.name = this.name.trim();
        }
    }

    public boolean isEmpty() {
        return this.name == null;
    }

    @Override
    public String toString() {
        return "Name: " + this.name;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
