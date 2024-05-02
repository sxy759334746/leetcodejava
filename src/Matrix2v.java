public class Matrix2v {
    
    public static void main(String[] args) {
        
        int [][] m = new int[][]{{5,5,5,5,5},
        {4,4,4,4,4},
        {3,3,3,3,3},
        {2,2,2,2,2},
        {1,1,1,1,1}};
        int x = m.length;
        int y = m[0].length;
        int preSum [][] = new int [x+1][y+1];
        for(int i = 1;i <= m.length;i++){
            for(int j = 1;j <= m[0].length;j++){

                preSum[i][j] = 
                preSum[i-1][j]+
                preSum[i][j-1]+
                m[i-1][j-1]
                -preSum[i-1][j-1];
//前缀和数组的构造方法
//pre（i，j-1）+pre（i-1，j）+原数组（i-1，j-1）-pre（i-1，j-1） 
                System.out.print(preSum[i][j]+ "  ");
            }
            System.out.println("");
        
        }


    }
}
