import java.io.FileWriter;
import java.io.IOException;
import java.util.ListIterator;

public class jsonWriter {
    private String fileName;

    public jsonWriter(String fileName) {
        this.fileName = fileName;
    }


    /**
     * Creates a file with the given name and also adds a new line to each line.
     *
     * @param content The array to be written to a file.
     */
    public void createFile(DoublyLinkedList<RestaurantEntry> content) {
        ListIterator<RestaurantEntry> iterator = content.iterator();
        try {
            FileWriter writer = new FileWriter(this.fileName);
            while (iterator.hasNext()) {
                RestaurantEntry current = iterator.next();
                if (current != null) {
                    writer.write(current.toString().concat("\n"));
                }
            }
            System.out.println("File " + "'" + this.fileName + "' created.");
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to create '" + this.fileName + "'");
        }
    }
}
