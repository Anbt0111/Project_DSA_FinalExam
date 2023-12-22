package soft;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = { 3, 2, 5, 0, 1, 8, 7, 6, 9, 4 };
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high); // pivot is the index of correctly placed element
            quickSort(arr, low, pivot - 1); // sort left subarray
            quickSort(arr, pivot + 1, high); // sort right subarray
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // pivot is the last element
        int i = low - 1; // index of smaller element
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) { // if current element is smaller than pivot
                i++; // increment index of smaller element
                int temp = arr[i]; // swap arr[i] and arr[j]
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; // swap arr[i+1] and arr[high] (or pivot)
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1; // return index of correctly placed element
    }
}