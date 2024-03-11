import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    //中序遍历
    public static void inOrderRecur(TreeNode head){
        if (head == null) return;

        preOrderRecur(head.left);
        System.out.println(head.value +" ");
        preOrderRecur(head.right);
    }

    //后序遍历
    public static void posOrderRecur(TreeNode head){
        if (head == null) return;

        preOrderRecur(head.left);
        preOrderRecur(head.right);
        System.out.println(head.value +" ");
    }


    //还有先中后序遍历的非递归实现 就是用栈

    //先序遍历的栈实现： 先在栈中压入根节点
    //然后弹出一个节点处理一个节点 然后入栈先右节点再左节点（如有）循环起来

    public static void preOrderUnRecur(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value + "");
                if (head.right != null) stack.push(head.right);
                if (head.left != null) stack.push(head.left);
                //还有先序plus 就是 头左右 变成 头右左 两个if换一下
            }
        }
    }

    //后续遍历就是在先序plus的基础上 用一个栈
    //弹出的时候跟一条代码stack2入栈 就相当于给他逆序 变成头右左

    public static void postOrderUnRecur(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack1 = new Stack<TreeNode>();
            Stack<TreeNode> stack2 = new Stack<TreeNode>();
            stack1.push(head);
            while (!stack1.isEmpty()) {
                head = stack1.pop();
                stack2.push(head);
                if (head.left != null) stack1.push(head.left);
                if (head.right != null) stack1.push(head.right);
            }

        }
    }


    //中序遍历 每个树的左边界全进栈，在弹出过程中让右边界也进栈
    public static void inOrderUnRecur(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                }else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }

            }


        }
    }


    //二叉树的宽度遍历 就是层序遍历 用队列
    //基本思想就是用队列 先放根节点  弹出打印 然后放左节点 再右节点 循环
    public static void binaryTreeWidth(TreeNode head) {
        if (head == null) return;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(head);

        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);

        }

    }

    //二叉树的层序遍历 求深度等等
    //想要知道二叉树的宽度




}
