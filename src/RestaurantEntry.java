public class RestaurantEntry implements Comparable<RestaurantEntry> {
    private String entryFull;    // The entire, unedited String of the file line
    private Address address;     // Address field of the restaurant
    private Borough borough;     // Borough field of the restaurant
    private Cuisine cuisine;     // Cuisine field of the restaurant
    private Grade grade;         // Grade field of the restaurant
    private Name name;           // Name field of the restaurant
    private ID id;               // ID field of the restaurant
    private int[] scores;        // Scores of the grade field
    private double averageScore; // Average score of the score array

    /**
     * RestaurantEntry is what each line of data is stored as. The class contains
     * all of the other classes that hold necessary information about each restaurant.
     *
     * @param entryFull String representation of the entry, with formatted dates.
     * @param address   The address field of the entry.
     * @param borough   The borough field of the entry.
     * @param cuisine   The cuisine filed of the entry.
     * @param grade     The grade field of the entry.
     * @param name      The name field of the entry.
     * @param id        The ID field of the entry.
     */
    public RestaurantEntry(String entryFull, Address address, Borough borough,
                           Cuisine cuisine, Grade grade, Name name, ID id) {
        this.entryFull = Grade.formatGrade(entryFull.substring(0, entryFull.length() - 1));
        this.address = address;
        this.borough = borough;
        this.cuisine = cuisine;
        this.grade = grade;
        this.name = name;
        this.id = id;
        this.scores = createScoreArray();
        this.averageScore = averageScore();
    }

    /**
     * Returns the highest letter grade found in the grade field.
     *
     * @return Returns the String representation of highest grade.
     */

    public String getHighestGrade() {
        int index = 0;

        while (index >= 0) {
            index = this.grade.getGrade().indexOf("grade\"", index + 1); // Finds each instance of grade
            if (index != -1) {
                if (grade.toString().contains("\"A\"")) {
                    return "A";
                } else if (grade.toString().contains("\"B\"")) {
                    return "B";
                } else if (grade.toString().contains("\"C\"")) {
                    return "C";
                } else if (grade.toString().contains("\"D\"")) {
                    return "D";
                } else if (grade.toString().contains("\"P\"")) {
                    return "P";
                } else if (grade.toString().contains("\"F\"")) {
                    return "F";
                }
            }
        }
        return null;
    }

    /**
     * Creates and populates an array with scores found in the grade field.
     *
     * @return Returns the array containing the scores.
     */

    private int[] createScoreArray() {
        int index = 0;
        int i = 0;
        int count = -1;
        int scoreInt;
        String score;

        while (index >= 0) {
            index = grade.toString().indexOf("\"score\"", index + 1); // Tracks how many instances of score appear
            count++;
        }

        int[] scores = new int[count];
        index = 0;

        while (index >= 0) {
            index = grade.toString().indexOf("\"score\"", index + 1); // Gets each score
            if (index != -1) {
                score = grade.toString().substring(index, grade.toString().indexOf("}", index)); // Format
                score = score.replace("\"score\":", ""); // Format
                score = score.trim(); // Format
                scoreInt = Integer.parseInt(score); // Convert String to Integer
                scores[i] = scoreInt;
                i++;
            }
        }
        return scores;
    }

    /**
     * Finds the highest score contained in the scores array.
     *
     * @return Returns the highest score.
     */

    public int highestScore() {
        int highestScore = 0;

        for (int score : this.scores) {
            if (score > highestScore) {
                highestScore = score;
            }
        }
        return highestScore;
    }

    /**
     * Calculates the average of the scores in the scores array.
     *
     * @return The double representation of the average score.
     */

    public double averageScore() {
        int total = 0;

        for (int score : this.scores) {
            total += score;
        }
        return (double) total / this.scores.length;
    }

    /**
     * Counts how many instances of grade appear in the grade field.
     *
     * @return The amount of grade fields counted.
     */

    public int getGradeCount() {
        int index = 0;
        int gradeCount = 0;

        while (index >= 0) {
            index = grade.toString().indexOf("grade\"", index + 1);
            if (index != -1) {
                gradeCount++;
            }
        }
        return gradeCount;
    }

    /**
     * Determines how many fields each entry contains.
     *
     * @return The amount of fields
     */

    public int getCount() {
        int count = 0;

        if (!address.isEmpty()) {
            count++;
        }

        if (!borough.isEmpty()) {
            count++;
        }

        if (!cuisine.isEmpty()) {
            count++;
        }

        if (!grade.isEmpty()) {
            count++;
        }

        if (!name.isEmpty()) {
            count++;
        }

        if (!id.isEmpty()) {
            count++;
        }
        return count;
    }

    @Override
    public int compareTo(RestaurantEntry restaurant) {
        if(this.averageScore == restaurant.averageScore) {
            return 0;
        } else if (this.averageScore > restaurant.getAverageScore()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Returns the entry line with formatted dates as well as additional information.
     *
     * @return Concatenated string.
     */

    @Override
    public String toString() {
        return entryFull + ", count: \"" + getCount() + "\"" + ", grade_count: \"" + getGradeCount() + "\"" + ", highest_grade: \"" + getHighestGrade() + "\"" + ", highest_score: \"" + highestScore() + "\"" + ", average_score: \"" + String.format("%.2f", averageScore()) + "\"}";
    }

    // Getters and Setters

    public String getEntryFull() {
        return entryFull;
    }

    public void setEntryFull(String entryFull) {
        this.entryFull = entryFull;
    }

    public Address Address() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Borough Borough() {
        return borough;
    }

    public void setBorough(Borough borough) {
        this.borough = borough;
    }

    public Cuisine Cuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Grade Grade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Name Name() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }
}