public class Tree {

    public static class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data){
            this.value = data;
        }
    }

    //先序遍历
    public static void preOrderRecur(TreeNode head){
        if (head == null) return;

        System.out.println(head.value +" ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(TreeNode head){
        if (head == null) return;

        preOrderRecur(head.left);
        System.out.println(head.value +" ");
        preOrderRecur(head.right);
    }

    public static void posOrderRecur(TreeNode head){
        if (head == null) return;

        preOrderRecur(head.left);
        preOrderRecur(head.right);
        System.out.println(head.value +" ");
    }





}
