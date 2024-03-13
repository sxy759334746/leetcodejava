import java.util.*;
import java.util.LinkedList;
import java.lang.Math;
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
                //还有先序plus 就是 头右左 变成 头左右 两个if换一下
                //这样 栈先进先出 在弹出的时候刚好
            }
        }
    }

    //后序遍历就是在先序plus的基础上 用一个栈
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
        //然后stack2 全部pop顺序就是后序
    }


    //中序遍历 每个树的左边界全进栈，在弹出过程中 打印 让右树做同样循环
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

    //层序遍历 leetcode102 关键在于在每一层加一个循环 次数就是queue的size
    //通过 List<List<Integer>> 这样可以得到包含每一层元素的二维数组
    public static List<List<Integer>> treeWidth1(TreeNode head) {
        if (head == null) return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(head);
        //设置二维数组来存放遍历的结果
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        while (!queue.isEmpty()){
            // 每个循环保存 队列的大小 这个就是当前层的宽度
            int size = queue.size();
            //再来一个arraylist用来存放每一层的数
            List<Integer> level = new ArrayList<Integer>();
            //for循环来处理每一层的遍历
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                System.out.println(cur.value);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);

            }
            res.add(level);

        }
        return res;

    }

    //左的方法维护用一个hashmap记录节点和他的level <node,level>
    //还有变量记录 curLevel 和curLevelNodes
    //在判断 弹出当前节点之前(或者之后) 查询hashmap得到当前的level
    //如果 当前的节点的level就是 curlevel 则curLevelNodes++
    //如果不是了 说明已经是下一层了 就记录 节点数量 curlevel++ 然后节点数量清零
    public static void treeWidth2(TreeNode head) {
        if (head == null) return;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(head);

        //记录变量
        HashMap<TreeNode,Integer> levelMap = new HashMap<TreeNode, Integer>();
        levelMap.put(head,1);
        int curLevel = 1;
        int curLevelNodes = 0; //当前层节点数
        int max = Integer.MIN_VALUE;// 用来记录最大宽度

        //开始循环
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();

            int curNodeLevel = levelMap.get(cur);
            if(curNodeLevel == curLevel){    //是当前层
                curLevelNodes++;
            }else {     //是下一层
                curLevel++;
                max = Math.max(max,curLevelNodes);
                curLevelNodes = 0;
            }

            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.add(cur.left);
                levelMap.put(cur.left,curNodeLevel+1);//在添加节点的时候map
            }
            if (cur.right != null) {
                queue.add(cur.right);
                levelMap.put(cur.right,curNodeLevel+1);
            }

        }

    }

    //左的方法还有一种用栈的方法 思想同样是在弹出打印的时候处理
    //需要记录 当前层最后一个节点 下一层最后节点 还有当前层节点数量 最大数量
    //代码暂无


    //搜索二叉树 BST 每个左子树都小于  右子树都大于
    //所以中序遍历 发现肯定是升序的
    //记录变量
    //int pre = Integer.MIN_VALUE; 每次打印时候比较 如果有不是的就false



    //判断是不是完全二叉树
    //完全二叉树 进行宽度遍历 任意节点 有右边无左边 直接false
    //满足上个条件之后 如果遇到了左右节点不全的 之后的节点必须全都是叶子节点
    public static boolean isCBT(TreeNode head) {
        if (head == null) return true;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(head);

        //变量 记录是否遇到过左右孩子不双全的
        boolean leaf = false;
        TreeNode l = null;
        TreeNode r = null;

        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            //System.out.print(cur.value + " ");

            l = cur.left;
            r = cur.right;

            if ( (leaf && (l != null || r != null) )
                    || (l == null && r != null)  ){

                return false;
            }

            if (l != null) queue.add(l);
            if (r != null) queue.add(r);
            if (l == null || r == null) leaf = true;

        }
        return true;
    }

    //判断是不是 满二叉树 平衡二叉树
    //满二叉树就是 最大深度

    //求一颗二叉树的最大深度  满二叉树的节点数量是（2的 最大深度次方）- 1
    public static int maxDepth(TreeNode head) {
        if (head == null) return 0;
//        return Math.max(maxDepth(head.left), maxDepth(head.right)) + 1;
        int left = maxDepth(head.left);
        int right = maxDepth(head.right);
        return  Math.max(left, right) + 1;
    }

    //代码暂时不全

    //平衡二叉树 任何一个子树 左子树和右子树高度差不超过1
    public static boolean isBalance(TreeNode head) {

        if (head == null) return true;
        return Math.abs(maxDepth(head.left) - maxDepth(head.right)) <= 1
                && isBalance(head.left)
                && isBalance(head.right);
    }















}
