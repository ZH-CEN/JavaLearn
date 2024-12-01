package Experiment.Exp3.exp01;

public class Sort {
    private Elephant[] elephants;

    Sort(Elephant[] elephants) {
        this.elephants = elephants;
    }

    private void swap(Elephant[] array, int index1, int index2) {
        Elephant temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public void sortName() {
        for (int i = 0; i < this.elephants.length - 1; i++) {
            for (int j = 0; j < this.elephants.length - 1 - i; j++) {
                if (this.elephants[j].getName().compareTo(this.elephants[j + 1].getName()) > 0) {
                    swap(this.elephants, j, j + 1);
                }
            }
        }
    }

    public void sortWeight() {
        for (int i = 0; i < this.elephants.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < this.elephants.length; j++) {
                if (this.elephants[min].getWeight() > this.elephants[j].getWeight()) {
                    min = j;
                }
            }
            swap(this.elephants, i, min);
        }
    }

    public void sortHeight() {
        for (int i = 0; i < this.elephants.length - 1; i++) {
            for (int j = 0; j < this.elephants.length - 1 - i; j++) {
                if (this.elephants[j].getHeight() < this.elephants[j + 1].getHeight()) {
                    swap(this.elephants, j, j + 1);
                }
            }
        }
    }

    public void sortGuiBing() {
        mergeSort(elephants, 0, elephants.length - 1);
    }

    public static void mergeSort(Elephant[] array, int left, int right) {
        if (left < right) {
            // 找到中间点
            int middle = (left + right) / 2;

            // 对左半部分进行排序
            mergeSort(array, left, middle);

            // 对右半部分进行排序
            mergeSort(array, middle + 1, right);

            // 合并已排序的部分
            merge(array, left, middle, right);
        }
    }

    // 合并两个已排序的子数组
    public static void merge(Elephant[] array, int left, int middle, int right) {
        // 找到两个子数组的大小
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // 创建临时数组
        Elephant[] leftArray = new Elephant[n1];
        Elephant[] rightArray = new Elephant[n2];

        // 拷贝数据到临时数组
        for (int i = 0; i < n1; ++i)
            leftArray[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = array[middle + 1 + j];

        // 合并临时数组

        // 初始索引分别为两个子数组的起始位置
        int i = 0, j = 0;

        // 初始索引为合并后数组的起始位置
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].inIceBoxTime >= rightArray[j].inIceBoxTime) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // 拷贝剩余元素（如果有）
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
