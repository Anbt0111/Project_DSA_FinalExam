package org.example;

public class HeapSort {
  public static void heapSort(int[] arr) {
    int n = arr.length;

    for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);

    for (int i = n - 1; i >= 0; i--) {
      int temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;

      heapify(arr, i, 0);
    }
  }

  public static void heapify(int[] arr, int n, int i) {
    int largest = i;
    int leftChildIdx = 2 * i + 1;
    int rightChildIdx = 2 * i + 2;

    if (leftChildIdx < n && arr[leftChildIdx] > arr[largest]) largest = leftChildIdx;

    if (rightChildIdx < n && arr[rightChildIdx] > arr[largest]) largest = rightChildIdx;

    if (largest != i) {
      int swap = arr[i];
      arr[i] = arr[largest];
      arr[largest] = swap;

      heapify(arr, n, largest);
    }
  }
}
