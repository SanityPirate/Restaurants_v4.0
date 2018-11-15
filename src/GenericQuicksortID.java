// Generic quick sort from http://www.learntosolveit.com/java/GenericQuicksortComparable.html
// Only change is T to RestaurantEntry

public class GenericQuicksortID {

    public static <T extends Comparable<T>> void qsort(RestaurantEntry[] arr, int a, int b) {
        if (a < b) {
            int i = a, j = b;
            RestaurantEntry x = arr[(i + j) / 2];

            do {
                while (arr[i].getId().compareTo(x.getId()) < 0) i++;
                while (x.getId().compareTo(arr[j].getId()) < 0) j--;

                if ( i <= j) {
                    RestaurantEntry tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i++;
                    j--;
                }

            } while (i <= j);

            qsort(arr, a, j);
            qsort(arr, i, b);
        }
    }
}
