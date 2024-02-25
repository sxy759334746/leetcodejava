public class hellojava {

/*
 * leetcode75 荷兰国旗问题  双指针大于小于区域 快速排序的先验
 * 快速排序 随机选left到right中某值 然后partition 获得 <  = 和 > 区域 然后在小于大于区递归获得整体有序
 * 荷兰国旗问题就是partition的过程  在partition后返回等于区域的边界指针
 */
    public static void main(String[] args) {
        
        int[] nums = new int[]{2,0,2,1,1,0};
        int len = nums.length;
        int p1 = 0;
        int p2 = len-1;
        // for(int i = 0;i < len; i++){
        int i = 0;
        // int n = 0;
        while(i<=p2){
            if (nums[i] == 0){
                int tmp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = tmp;
                p1++;
                i++;
                // n++;
            }else if(nums[i] == 1){
                i++;
                continue;
            }else if(nums[i] == 2){
                int tmp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = tmp;
                p2--;
            }
        }
        for(int ii : nums){
            System.out.println(ii);
        }
    }

    /*
     * 
     */





}
