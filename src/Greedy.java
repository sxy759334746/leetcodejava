import java.util.*;

public class Greedy {
    //贪心策略一般要根据比较器来排序
    //或者根据比较器来建立堆

    //无重叠区间 贪心
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->{
            return Integer.compare(a[1],b[1]);
        });
        if(intervals.length <= 1) return 0;

        int count = 1;
        int right = intervals[0][1];
        for(int cur = 1;cur < intervals.length; cur++){
            if(intervals[cur][0] >= right){
                count++;
                right = intervals[cur][1];
            }
        }
        return intervals.length - count;
    }

    //比较器实现字典序排列
    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String a,String b){
            return (a + b).compareTo(b + a); //字符串的字典序比较
        }
    }
    public static String lowestString(String[] strs){
        if(strs == null || strs.length == 0)return "";
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++){
            res += strs[i];
        }
        return res;
    }

    //代价最小的分金条问题 贪心 霍夫曼编码
    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < arr.length; i++){
            pq.add(arr[i]);
        }
        int res = 0;
        int cur = 0;
        while (pq.size() > 1){
            cur = pq.poll() + pq.poll();
            res += cur;
            pq.add(cur);

        }
        return res;
    }

    //大小根堆求数据流中位数 leetcode295
    //n皇后问题
    //n皇后的位运算优化问题

    //递归逆序一个栈

    public static void reverseStack(Stack<Integer> stack){
        if(stack.isEmpty())return;
        int i = popAndPush(stack);
        reverseStack(stack);
        stack.push(i);
    }
    public static int popAndPush(Stack<Integer> stack){ //弹出栈底元素并返回
        int result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else {
            int last = popAndPush(stack);
            stack.push(result);
            return last;
        }
    }

































}
