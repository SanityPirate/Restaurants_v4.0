public class Borough {
    private String borough;

    public Borough(String entry) {
        this.borough = entry.substring((entry.indexOf("borough") + 9), entry.indexOf("\", \"cuisine\"") + 1);
        this.borough = this.borough.replaceAll("\"", "");
        this.borough = this.borough.trim();
    }

    public boolean isEmpty() {
        return this.borough == null;
    }

    @Override
    public String toString() {
        return "Borough: " + this.borough;
    }
}
