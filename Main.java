import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Define the Strategy interface
interface SortingStrategy {
    void sort(List<Integer> numbers);
}

// Concrete implementation of sorting strategy: Bubble Sort
class BubbleSortStrategy implements SortingStrategy {
    @Override
    public void sort(List<Integer> numbers) {
        System.out.println("Sorting using Bubble Sort");
        // Implement Bubble Sort algorithm (placeholder)
        Collections.sort(numbers);
        System.out.println("Sorted numbers: " + numbers);
    }
}

// Concrete implementation of sorting strategy: Quick Sort
class QuickSortStrategy implements SortingStrategy {
    @Override
    public void sort(List<Integer> numbers) {
        System.out.println("Sorting using Quick Sort");
        // Implement Quick Sort algorithm (placeholder)
        Collections.sort(numbers);
        System.out.println("Sorted numbers: " + numbers);
    }
}

// Context class that uses the Strategy
class SortContext {
    private SortingStrategy strategy;

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void performSort(List<Integer> numbers) {
        strategy.sort(numbers);
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a context
        SortContext context = new SortContext();

        // Set the Bubble Sort strategy
        context.setStrategy(new BubbleSortStrategy());
        // Perform sorting
        List<Integer> numbers1 = new ArrayList<>(List.of(5, 3, 8, 1, 2));
        context.performSort(numbers1);

        // Set the Quick Sort strategy
        context.setStrategy(new QuickSortStrategy());
        // Perform sorting
        List<Integer> numbers2 = new ArrayList<>(List.of(5, 3, 8, 1, 2));
        context.performSort(numbers2);
    }
}
