public class Manacher {
    //找到字符串中最长的回文子串（不是子序列

    //1221 -> #1#2#2#1# 加轴变成处理串
    //然后比较两侧得到从每一个字符出发（包括轴）能得到的回文长度 然后除以2
    //char[] str
    //int[] pArr = new int[str.length] 求回文半径数组

    //回文半径 int c =
    //最右回文右边界 int r = -1

    //for(i<str.length)
    //  if(i在 R外部)从i开始往两侧暴力扩充 R变大
    //  else{
    //      if(i‘ 回文区域彻底在L R内）pArr[i] =
    //      elseif(i'回文区域部分在L R外) pArr[i]=
    //      else i' i 压线 L R  需要从R之外的字符开始比对 然后确定pArr[i]
    //  }

    //  如果第一步扩失败了  R不变
    //  否则R变大
    //  每个位置扩的时候最多失败一次

    public static char[] manacher(String str){

        char[] c = str.toCharArray();
        char[] res = new char[str.length()*2 + 1];
        int index = 0;
        for (int i = 0; i!= res.length;i++){
            res[i] = (i & 1) == 0 ? '#' : c[index++];
        }
        return res;
    }
    public static int maxLcpsLength(String s){
        if (s == null || s.length() == 0)return 0;
        char[] str = manacher(s);
        int[] p = new int[str.length];//回文半径数组
        int c = -1;//中心轴
        int r = -1; //回文右边界再往右一个 最右有效区域是r-1
        int max = Integer.MIN_VALUE;

        for (int i = 0; i!= str.length; i++){ //每个位置都求回文
            //i至少的回文区域 先给p[i]
            //至少不用验证的的区域 要么i在r外 他肯定是1
            //要么i在r内就取最短的
            p[i] = r > i ? Math.min( p[2*c - i] , r-i ):1;//2*c-i 就是i'的位置

            while (i+p[i] < str.length && i-p[i] > -1){
                if (str[i + p[i]] == str[i- p[i]]){
                    p[i]++;
                }else {
                    break;
                }
            }

            if (i+p[i] > r){
                r = i+p[i];
                c = i;
            }
            max = Math.max(max,p[i]);

        }

        return max - 1;

    }






















}
