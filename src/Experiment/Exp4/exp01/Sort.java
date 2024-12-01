package Experiment.Exp4.exp01;

import java.util.Comparator;

public class Sort {

    // 适配对象类型的 mergeSort 方法
    public static <T> void mergeSort(T[] arr, int left, int right, Comparator<T> cmp) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid, cmp);
        mergeSort(arr, mid + 1, right, cmp);

        merge(arr, left, mid, right, cmp);
    }

    public static <T> void merge(T[] arr, int left, int mid, int right, Comparator<T> cmp) {
        T[] temp = (T[]) new Object[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (cmp.compare(arr[i], arr[j]) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }
}
