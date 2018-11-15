import java.io.File;
import java.util.ListIterator;
import java.util.Scanner;

public class UI {
    private Scanner reader = new Scanner(System.in);
    private RestaurantEntry[] restaurantEntryArray;
    DoublyLinkedList<RestaurantEntry> restaurants1 = new DoublyLinkedList<>();
    DoublyLinkedList<RestaurantEntry> restaurants2 = new DoublyLinkedList<>();
    ListIterator<RestaurantEntry> iterator;

    public UI() {
    }

    /**
     * Handles user input and the interface the user interacts with.
     */

    public void start() {
        getFile();
        System.out.println("How would you like to sort the data? \n [1] - Name \n [2] - ID \n [3] - Average Score");

        String input1 = reader.nextLine();

        while (true) {
            if (input1.equalsIgnoreCase("1")) {
                // Sorts by Name
                GenericQuicksortName.qsort(restaurantEntryArray, 0, restaurantEntryArray.length - 1);
                break;
            } else if (input1.equalsIgnoreCase("2")) {
                // Sorts by ID
                GenericQuicksortID.qsort(restaurantEntryArray, 0, restaurantEntryArray.length - 1);
                break;
            } else if (input1.equalsIgnoreCase("3")) {
                // Sorts by Average Score
                GenericQuicksortRE.qsort(restaurantEntryArray, 0, restaurantEntryArray.length - 1);
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        // Adds entries to a doubly linked list
        for (RestaurantEntry re : restaurantEntryArray) {
            restaurants1.add(re);
        }

        // Initializes iterator for the doubly linked list
        iterator = restaurants1.iterator();

        while (true) {
            System.out.println("Please enter a command ['S' for search, 'D' for remove, 'M' for merge, 'P' for purge, 'W' for print, 'E' to exit]: ");
            String input2 = reader.nextLine();

            if (input2.equalsIgnoreCase("S")) {
                // search
                while (true) {
                    System.out.print("Search using: \n[1] - Name \n[2] - ID \n[3] - Cuisine \n(Enter number): ");
                    input2 = reader.nextLine();

                    if (input2.equalsIgnoreCase("1")) {
                        // search by name
                        System.out.println("Please enter a restaurant name: ");
                        search(1, reader.nextLine());
                        break;
                    } else if (input2.equalsIgnoreCase("2")) {
                        // search by id
                        System.out.println("Please enter an ID number: ");
                        input2 = reader.nextLine();
                        if (input2.matches("^\\d+$")) { // Checks if input contains only digits
                            search(2, input2);
                        } else {
                            System.out.println("Invalid input.");
                        }
                        break;
                    } else if (input2.equalsIgnoreCase("3")) {
                        // search by cuisine
                        System.out.println("Please enter a cuisine: ");
                        search(3, reader.nextLine());
                        break;
                    }
                }
            } else if (input2.equalsIgnoreCase("D")) {
                // remove
                while (true) {
                    System.out.print("Remove using: \n[1] - Name \n[2] - ID  \n(Enter number): ");
                    input2 = reader.nextLine();

                    if (input2.equalsIgnoreCase("1")) {
                        // remove by name
                        System.out.println("Please enter a restaurant name: ");
                        remove(1, reader.nextLine());
                    } else if (input2.equalsIgnoreCase("2")) {
                        // remove by id
                        System.out.println("Please enter an ID number: ");
                        input2 = reader.nextLine();
                        if (input2.matches("^\\d+$")) { // Checks if input contains only digits
                           remove(2, input2);
                        } else {
                            System.out.println("Invalid input.");
                        }
                    }
                    break;
                }
            } else if (input2.equalsIgnoreCase("M")) {
                int count = 0;
                getFile();

                // Merges entries from a new file into current doubly linked list.
                for (RestaurantEntry re : restaurantEntryArray) {
                    count++;
                    iterator.add(re);
                }
                System.out.println(count + " entries were merged.");

            } else if (input2.equalsIgnoreCase("P")){
                int count = 0;
                getFile();

                // Deletes entries from doubly linked list according to new file.
                for (RestaurantEntry re : restaurantEntryArray) {
                    restaurants2.add(re);
                }
                if (iterator.hasNext()) {
                    while (iterator.hasNext()) {
                        RestaurantEntry current = iterator.next();
                        for (RestaurantEntry re : restaurantEntryArray) {
                            if (current.getId().getIdNumber() == re.getId().getIdNumber()) {
                                count++;
                                iterator.remove();
                            }
                        }
                    }
                    System.out.println(count + " entries were purged.");
                } else if (iterator.hasPrevious()) {
                    while (iterator.hasPrevious()) {
                        RestaurantEntry current = iterator.previous();
                        for (RestaurantEntry re : restaurantEntryArray) {
                            if (current.getId().getIdNumber() == re.getId().getIdNumber()) {
                                count++;
                                iterator.remove();
                            }
                        }
                    }
                    System.out.println(count + " entries were purged.");
                }
            } else if (input2.equalsIgnoreCase("W")) {
                // print to file
                print();
            } else if (input2.equalsIgnoreCase("E")) {
                // exit program
                reader.close();
                break;
            }
        }
    }

    /**
     * getFile() is used to generate a RestaurantEntry array from the file, which is then stored
     * into a doubly linked list.
     */
    private void getFile() {
        while (true) {
            System.out.println("Please enter the name of the file: ");

            jsonReader currentFile = new jsonReader(reader.nextLine()); // Creates a new file with name specified by user
            String[] entries = currentFile.createArray(); // Creates a String array from the file
            if (entries != null) {
                restaurantEntryArray = new RestaurantEntry[entries.length];

                int i = 0;
                while (i < entries.length) { // Converts each entry to a RestaurantEntry object
                    RestaurantEntry restaurantEntry = new RestaurantEntry(entries[i], new Address(entries[i]),
                            new Borough(entries[i]), new Cuisine(entries[i]),
                            new Grade(entries[i]), new Name(entries[i]), new ID(entries[i]));
                    restaurantEntryArray[i] = restaurantEntry;
                    i++;
                }
            }
            if (!currentFile.isFileNotFound()) {
                break;
            }
        }
    }

    /**
     * Handles printing of the RestaurantEntries to a new file or
     * overwrites an existing file if desired.
     */

    private void print() {
        System.out.println("Please enter the name of the new file: ");
        String input = reader.nextLine();
        String name = input;
        File file = new File(input);

        if (input.equals("")) {
            if (iterator.hasNext()) {
                while (iterator.hasNext()) {
                    RestaurantEntry current = iterator.next();
                    System.out.println(current);
                }
            } else if (iterator.hasPrevious()) {
                while (iterator.hasPrevious()) {
                    RestaurantEntry current = iterator.previous();
                    System.out.println(current);
                }
            }

        }
        if (file.isFile()) {
            System.out.println("A file with name '" + input + "' already exists. Would you like to overwrite it? [Y/N]");
            while (true) {
                input = reader.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    System.out.println("Please enter a new name: ");
                    input = reader.nextLine();
                    name = input;
                    break;
                }
            }
        }
        jsonWriter newFile = new jsonWriter(name);
        newFile.createFile(restaurants1);
    }

    /**
     * Searches the RestaurantEntries and outputs the entries that
     * match the searchTerm parameter.
     *
     * @param searchBy Determines whether to search by Name, ID, or Cuisine.
     * @param searchTerm The term that each entry is compared to.
     */

    private void search(int searchBy, String searchTerm) {
        int matches;
        int i;
        RestaurantEntry[] searchResults = new RestaurantEntry[3];

        if (searchBy == 1) {
            matches = 0;
            i = 0;

            if (iterator.hasNext()) {
                while(iterator.hasNext()) {
                    RestaurantEntry current = iterator.next();
                    if (current.Name().getName().equalsIgnoreCase(searchTerm.trim())) {
                        matches++;
                        if (i < 3) {
                            searchResults[i] = current;
                            i++;
                        }
                    }
                }
                System.out.println("Matches: " + matches);
                for (RestaurantEntry re : searchResults) {
                    if (re != null) {
                        System.out.println(re);
                    }
                }
            } else if (iterator.hasPrevious()) {
                while(iterator.hasPrevious()) {
                    RestaurantEntry current = iterator.previous();
                    if (current.Name().getName().equalsIgnoreCase(searchTerm.trim())) {
                        matches++;
                        if (i < 3) {
                            searchResults[i] = current;
                            i++;
                        }
                    }
                }
                System.out.println("Matches: " + matches);
                for (RestaurantEntry re : searchResults) {
                    if (re != null) {
                        System.out.println(re);
                    }
                }
            }

        } else if (searchBy == 2) {
            boolean found = false;
            if (iterator.hasNext()) {
                while(iterator.hasNext()) {
                    RestaurantEntry current = iterator.next();
                    if (current.getId().getIdNumber() == Integer.parseInt(searchTerm)) {
                        System.out.println(current);
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("No restaurants1 found with that ID.");
                }
            } else if (iterator.hasPrevious()) {
                while(iterator.hasPrevious()) {
                    RestaurantEntry current = iterator.previous();
                    if (current.getId().getIdNumber() == Integer.parseInt(searchTerm)) {
                        System.out.println(current);
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("No restaurants1 found with that ID.");
                }
            }

        } else if (searchBy == 3) {
            matches = 0;
            i = 0;

            if (iterator.hasNext()) {
                while (iterator.hasNext()) {
                    RestaurantEntry current = iterator.next();
                    if (current.Cuisine().getCuisine().equalsIgnoreCase(searchTerm.trim())) {
                        matches++;
                        if (i < 3) {
                            searchResults[i] = current;
                            i++;
                        }
                    }
                }
                System.out.println("Matches: " + matches);
                for (RestaurantEntry re : searchResults) {
                    if (re != null) {
                        System.out.println(re);
                    }
                }
            } else if (iterator.hasPrevious()) {
                while (iterator.hasPrevious()) {
                    RestaurantEntry current = iterator.previous();
                    if (current.Cuisine().getCuisine().equalsIgnoreCase(searchTerm.trim())) {
                        matches++;
                        if (i < 3) {
                            searchResults[i] = current;
                            i++;
                        }
                    }
                }
                System.out.println("Matches: " + matches);
                for (RestaurantEntry re : searchResults) {
                    if (re != null) {
                        System.out.println(re);
                    }
                }
            }

        }
    }

    /**
     * Removes a RestaurantEntry specified by the user.
     *
     * @param removeBy Determines whether to search the entries by Name or ID.
     * @param searchTerm The term that each entry is compared to.
     */

    private void remove(int removeBy, String searchTerm) {
        int count = 0;

        if (removeBy == 1) {
            if (iterator.hasNext()) {
                while (iterator.hasNext()) {
                    RestaurantEntry current = iterator.next();
                    if (current.Name().getName().equalsIgnoreCase(searchTerm.trim())) {
                        count++;
                        iterator.remove();
                    }
                }
                System.out.println("Removed " + count + " entries with name '" + searchTerm + "'");
            } else if (iterator.hasPrevious()) {
                    while (iterator.hasPrevious()) {
                        RestaurantEntry current = iterator.previous();
                        if (current.Name().getName().equalsIgnoreCase(searchTerm.trim())) {
                            count++;
                            iterator.remove();
                        }
                    }
                System.out.println("Removed " + count + " entries with name '" + searchTerm + "'");
            }
        } else if (removeBy == 2) {
            if (iterator.hasNext()) {
                while (iterator.hasNext()) {
                    RestaurantEntry current = iterator.next();
                    if (current.getId().getIdNumber() == Integer.parseInt(searchTerm.trim())) {
                        iterator.remove();
                        System.out.println("Removed entry with ID '" + searchTerm + "'");
                    }
                }

            } else if (iterator.hasPrevious()) {
                while (iterator.hasPrevious()) {
                    RestaurantEntry current = iterator.previous();
                    if (current.getId().getIdNumber() == Integer.parseInt(searchTerm.trim())) {
                        iterator.remove();
                        System.out.println("Removed entry with ID '" + searchTerm + "'");
                    }
                }
            }
        }
    }
}