public class ID implements Comparable<ID> {
    private String ID;
    private int idNumber;

    public ID(String entry) {
        if (entry.contains("\"restaurant_id\"")) {
            this.ID = entry.substring(entry.indexOf("restaurant_id") - 1, entry.indexOf("}", entry.indexOf("restaurant_id")));
            this.idNumber = convertInt(this.ID);
        }
    }

    private int convertInt(String findInt) {
        String id = this.ID.substring(this.ID.indexOf(": \"") + 3);
        id = id.replace("\"", "");
        return Integer.parseInt(id);
    }

    public int getIdNumber() {
        return idNumber;
    }

    public boolean isEmpty() {
        return this.ID == null;
    }

    @Override
    public int compareTo(ID id) {
        return this.getIdNumber() - id.getIdNumber();
    }

    @Override
    public String toString() {
        return "Restaurant_ID: " + this.idNumber;
    }
}
