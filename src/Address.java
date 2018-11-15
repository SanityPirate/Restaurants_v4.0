public class Address {
    private String address;

    public Address(String entry) {
        if (entry.contains("\"address\"")) {
            this.address = entry.substring((entry.indexOf("address") + 9), entry.indexOf("},") + 1);
            this.address = this.address.trim();

        }
    }

    public boolean isEmpty() {
        return this.address == null;
    }

    @Override
    public String toString() {
        return "Address: " + this.address;
    }

    // Getters and Setters

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
