public class KMP {

    //主要在于第i的字符之前的字符串中最长的 前缀等于后缀的长度
    //这个长度决定了在比较时str1移动到哪里

    public static int getIndexOf(String a,String b){
        if (a == null || b == null || a.length() < 1 || a.length() < b.length()){
            return -1;
        }
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);

        while (i1 < str1.length && i2 < str2.length){
            if (str1[i1] == str2[i2]){
                i1++;
                i2++;

            }else if (next[i2] == -1){ //等价于i2 == 0
                i1++;
            }else {
                i2 = next[i2];
            }
        }

        //i1越界或者i2越界
        return i2 == str2.length ? i1-i2 : -1;
    }

    public static int[] getNextArray(char[] ms){
        if (ms.length == 1){
            return new int[] {-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        //cn相当于要拿他和i-1位置的字符相比
        //还代表你当前i-1位置的信息是多少 i-1等于几 cn就是几
        while (i < ms.length){
            if (ms[i-1] == ms[cn]){
                next[i++] = ++cn;
                //相当于三行代码 next[i]= cn+1  i++  cn++
            }
            else if (cn > 0){//等于0就是 cn已经到头了
                cn = next[cn]; //这一步相当于比较不相等 然后cn往前跳
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }














}




























