import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * SortedList class that maintains a sorted list of strings.
 * It allows adding new strings while keeping the list sorted.
 */
public class SortedList
{
    private String[] sortedList;
    private int size;

    /**
     * Constructor to initialize the sorted list with a specified initial size.
     * @param initialSize The initial size of the sorted list.
     */
    public SortedList(int initialSize)
    {
        sortedList = new String[initialSize];
        size = 0;
    }

    /**
     * Adds a new string to the sorted list while maintaining the sorted order.
     * If the list is full, it resizes the array to accommodate more elements.
     * @param newString The new string to be added.
     */
    public void add(String newString)
    {
        if (size == sortedList.length)
        {
            String[] newList = new String[sortedList.length * 2];
            System.arraycopy(sortedList, 0, newList, 0, sortedList.length);
            sortedList = newList;
        }

        int insertionIndex = findInsertionIndex(newString);
        System.out.println("Adding: " + newString);
        System.out.println("Before shifting: " + Arrays.toString(sortedList));
        System.out.println("Insertion index: " + insertionIndex);

        System.out.println("Before shifting: " + Arrays.toString(sortedList));
        for (int i = size - 1; i >= insertionIndex; i--)
        {
            System.out.println("Moving " + sortedList[i] + " to index " + (i + 1));
            sortedList[i + 1] = sortedList[i];
        }
        System.out.println("After shifting: " + Arrays.toString(sortedList));

        sortedList[insertionIndex] = newString;

        size++;
        System.out.println("After adding: " + Arrays.toString(sortedList));
    }

    /**
     * Finds the index where the new string should be inserted to maintain sorted order.
     * @param target The string to be inserted.
     * @return The index where the string should be inserted.
     */
    private int findInsertionIndex(String target) {
        int low = 0;
        int high = size - 1;
        int insertionPoint = size; // Default to inserting at the end

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparisonResult = target.compareTo(sortedList[mid]);

            if (comparisonResult < 0) {
                // Target is smaller, so it should be in the left half
                insertionPoint = mid; // Potential insertion point
                high = mid - 1;
            } else {
                // Target is larger, so it should be in the right half
                low = mid + 1;
            }
        }
        return insertionPoint;
    }

    public int size ()
    {
        return size;
    }

    /**
     * Returns the current elements in the sorted list.
     * @return An array of strings representing the current elements.
     */
    public String[] getElements()
    {
        String[] currentElements = new String[size];
        System.arraycopy(sortedList, 0, currentElements, 0, size);
        return currentElements;
    }

    /**
     * Searches for a string in the sorted list using binary search.
     * @param target The string to search for.
     * @return The index of the string if found, or the index where it would be inserted.
     */
    public int search(String target) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparisonResult = target.compareTo(sortedList[mid]);

            if (comparisonResult == 0) {
                return mid; // Element found at this index
            } else if (comparisonResult < 0) {
                high = mid - 1; // Search in the left half
            } else {
                low = mid + 1; // Search in the right half
            }
        }
        return low; // Element not found, return the index where it would be inserted
    }
}
