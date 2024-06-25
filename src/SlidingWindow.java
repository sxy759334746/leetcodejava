import java.awt.*;
import java.util.LinkedList;

public class SlidingWindow {

    //双端队列 由大到小的双端队列
    //维持的信息 如果r不动 l右滑的过程中 谁会依次成为最大值
    public static class WindowMax{
        private int L;
        private int R;
        private int[] arr;
        private LinkedList<Integer> qmax;

        public WindowMax(int[] a){
            arr = a;
            L = -1;
            R = 0;
            qmax = new LinkedList<Integer>();
        }

        public void addNumFromRight(){
            if (R == arr.length)return;
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]){
                qmax.pollLast();
            }
            qmax.addLast(R);
            R++;
        }
        public void removeNumFromLeft(){
            if (L >= R-1){
                return;
            }
            L++;
            if (qmax.peekFirst() == L && !(qmax.isEmpty())){
                qmax.pollFirst();
            }
        }
        public Integer getMax(){
            if (!qmax.isEmpty())return arr[qmax.peekFirst()];

            return null;
        }

    }

    public static int[] getMaxWindow(int[] arr, int w){
        if (arr == null || w < 1 || arr.length < w)return null;

        LinkedList<Integer> qmax = new LinkedList<>();
        //存放下标 值从大到小
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++){
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i - w ){ //如果满足说明现在窗口满了
                qmax.pollFirst();
            }
            if (i >= w-1)res[index++] = arr[qmax.peekFirst()];
        }
        return res;
    }








}
