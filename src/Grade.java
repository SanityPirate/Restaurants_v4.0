import java.text.SimpleDateFormat;
import java.util.Date;

public class Grade {
    private String grade;

    public Grade(String entry) {
        if (entry.contains("\"grades\"")) {
            this.grade = entry.substring(entry.indexOf("grades") + 8, entry.indexOf("}],") + 2);
            this.grade = this.grade.trim();
        }
    }

    public static String formatGrade(String toFormat) {
        int index, startIndex;
        long jsonDate;

        index = 0;
        while (index >= 0) {
            index = toFormat.indexOf("$date", index + 1); // Finds each instance of $date
            if (index != -1) { // Discards results of -1
                startIndex = toFormat.indexOf("1", index);
                jsonDate = Long.parseLong(toFormat.substring(startIndex, toFormat.indexOf("},", index)));
                String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date(jsonDate)); // Epoch to date conversion from https://www.epochconverter.com/
                toFormat = toFormat.replaceAll(toFormat.substring(startIndex, toFormat.indexOf("},", index)), date); // Replaces all instances of JSON date with formatted date
            }
        }
        return toFormat;
    }

    public String getGrade() {
        return grade;
    }

    public boolean isEmpty() {
        return this.grade == null;
    }

    @Override
    public String toString() {
        return "Grades: " + formatGrade(this.grade);
    }
}
