import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class jsonReader {
    private File file;
    private String entries[];
    private boolean fileNotFound = false;
    private int totalLines = 0;

    public jsonReader(String fileName) {
        this.file = new File(fileName);
    }

    /**
     * Determines how large to make the array.
     *
     * @return The amount of lines in the file.
     */
    private int countLines() {
        try {
            Scanner reader = new Scanner(this.file);

            while (reader.hasNextLine()) {
                totalLines++;
                reader.nextLine();
            }
        } catch (FileNotFoundException ex) {

        }
        return totalLines;
    }


    /**
     * Creates an array to store the lines of the file in.
     *
     * @return String array of file lines.
     */
    public String[] createArray() {
        this.totalLines = countLines();

        try {
            Scanner reader = new Scanner(this.file);

            this.entries = new String[totalLines];
            int currentLine = 0;

            while (currentLine < totalLines) {
                String line = reader.nextLine();
                this.entries[currentLine] = line;
                currentLine++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + this.file + "'");
            fileNotFound = true;
        }
        return this.entries;
    }

    public void printLines() {
        int currentLine = 0;
        while (currentLine < entries.length) {
            System.out.println(entries[currentLine]);
            currentLine++;
        }
    }

    // Getters and Setters

    public void setFileNotFound(boolean fileNotFound) {
        this.fileNotFound = fileNotFound;
    }

    public boolean isFileNotFound() {
        return fileNotFound;
    }

    public String[] getEntries() {
        return this.entries;
    }

    public void setEntries(String[] entries) {
        this.entries = entries;
    }

    public int getTotalLines() {
        return this.totalLines;
    }

    public void setTotalLines(int totalLines) {
        this.totalLines = totalLines;
    }
}
