public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(23>>1);
        int xy = 78;
        System.out.println(xy);

        
        int [] tmparr = new int []{1,2,3,4,5};
        // method1(tmparr);
        
    }

    public void method1(int [] arr){
        for(int i = 0;i<arr.length-1;i++){
            for(int j = 0;j<arr.length-1-i;j++){
                if(arr[j]< arr[j+1]){
                    int tmp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tmp;

                }
            }
        }
    }
    
}


