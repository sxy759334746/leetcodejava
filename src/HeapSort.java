package Leetcode;
import java.util.*;

public class HeapSort {
    public static void headSor(int[] arr) {
        if(arr ==null || arr.length < 2){
            return;
        }
        //也可以用heapify 从下往上建立堆
        for(int i = 0;i < arr.length;i++){
            heapInsert(arr,i);
        }

        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while(heapSize > 0){
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }
    }

    //堆化 从index开始将arr变成堆（大根堆）
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index *2 + 1;
        while (left < heapSize){
            int largest = left+1 < heapSize && arr[left+1]> arr[left]? left +1 : left;
            if (largest ==index)break;
            swap(arr,largest,index);
            index = largest;
            left = index*2 + 1;
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index-1) /2]){
            swap(arr,index,(index-1)/2);
            index = (index-1) / 2;
        }
    }
    public static void swap(int[] arr, int index1,int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

}
