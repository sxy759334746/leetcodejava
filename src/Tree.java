import javax.imageio.metadata.IIOMetadataNode;
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

    //判断是不是二叉搜索树 最主要的是用中序遍历  把打印动作替换为比较pre和当前
    //或者用list存起来再判断
    //对于递归还是非递归其实是一样的 都可以写list存 或者直接比较

    //checkBST
    public static int perValueForCheckBST = Integer.MIN_VALUE;
    public static boolean checkBST(TreeNode head){
        if(head == null) return true;

        boolean isLeftBst = checkBST(head.left);
        if (!isLeftBst) return false;
        if (head.value <= perValueForCheckBST){
            return false;
        }else {
            perValueForCheckBST = head.value;
        }

        return checkBST(head.right);
    }

    //这种是使用递归的 list方法
    public static boolean checkBST2(TreeNode head){
        List<TreeNode> inOrderList = new ArrayList<>();
        processForBST(head,inOrderList);

        for (int i = 1; i < inOrderList.size(); i++) {

            if (inOrderList.get(i).value <= inOrderList.get(i-1).value){
                return false;
            }
        }
        return true;
    }
    public static void processForBST(TreeNode head,List<TreeNode> inOrderList){
        if (head == null) return;
        processForBST(head.left,inOrderList);
        inOrderList.add(head);
        processForBST(head.right,inOrderList);
    }

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

    //判断是不是 满二叉树
    //求出一颗二叉树的最大深度  满二叉树的节点数量是（2的 最大深度次方）- 1
    public static int maxDepth(TreeNode head) {
        if (head == null) return 0;
//        return Math.max(maxDepth(head.left), maxDepth(head.right)) + 1;
        int left = maxDepth(head.left);
        int right = maxDepth(head.right);
        return  Math.max(left, right) + 1;
    }
    //代码暂时不全



    //平衡二叉树 任何一个子树 左平衡 右平衡  左子树和右子树高度差不超过1

    public static boolean isBalance(TreeNode head) {

        if (head == null) return true;
        return Math.abs(maxDepth(head.left) - maxDepth(head.right)) <= 1
                && isBalance(head.left)
                && isBalance(head.right);
    }


    //从结构体开始学递归
    public static class ReturnTypeForBalanceTree {
        public boolean isBalanced;
        public int height;
        public ReturnTypeForBalanceTree(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    //然后写basecase就是最基础的return的时候的例子
    public static ReturnTypeForBalanceTree processForBalance(TreeNode x) {
        if (x == null) return new ReturnTypeForBalanceTree(true, 0);
        ReturnTypeForBalanceTree leftData = processForBalance(x.left);
        ReturnTypeForBalanceTree rightData = processForBalance(x.right);

        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) <= 1;

        return  new ReturnTypeForBalanceTree(isBalanced, height);

    }

    //然后是递归函数
    public static boolean isBalance2(TreeNode head) {
        return processForBalance(head).isBalanced;
    }

    //同样的开始写二叉搜索树的递归判断
    //左搜索 右搜索 然后左的max 小于x  右的min 大于x
    //还是 信息体 basecase 然后递归

    //信息体
    public static class ReturnDataForBST {
        public int min;
        public int max;
        public boolean isBST;
        public ReturnDataForBST(int min,int max, boolean isBST){
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    public static ReturnDataForBST processBST(TreeNode x){
        if(x == null) return null;

        ReturnDataForBST leftData = processBST(x.left);

        ReturnDataForBST rightData = processBST(x.right);

        int min = x.value;
        int max = x.value;

        if(leftData != null){
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }

        if(rightData != null){
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }

        boolean isBST = true;
        if(leftData != null && !leftData.isBST) isBST = false;
        if(rightData != null && !rightData.isBST) isBST = false;
        if(leftData != null && x.value <= leftData.max) isBST = false;
        if(rightData != null && x.value >= rightData.min) isBST = false;

        return new ReturnDataForBST(min,max,isBST);

    }

    //递归判断是不是满二叉树
    public static class ReturnDataForFullTree {
        public int size;
        public int height;
        public ReturnDataForFullTree(int size, int height) {
            this.size = size;
            this.height = height;
        }
    }
    public static ReturnDataForFullTree processFullTree(TreeNode x){
        if (x== null) return new ReturnDataForFullTree(0,0);
        ReturnDataForFullTree isFullLeft = processFullTree(x.left);
        ReturnDataForFullTree isFullRight = processFullTree(x.right);

        int height = Math.max(isFullLeft.height, isFullRight.height) + 1;
        int size = isFullLeft.size + isFullRight.size + 1;

        return new ReturnDataForFullTree(size, height);
    }
    public static boolean isFullTree(TreeNode x){
        if (x== null) return true;

        ReturnDataForFullTree data = processFullTree(x);

//        return data.size == (1<< data.height - 1);
        if (data.size != (Math.pow(2, data.height) - 1))return false;

        return true;

    }

    //返回两个二叉树的最低公共祖先 两种解法 麻烦的 需要用hashmap记录节点和父节点
    //并递归记录所有节点和其父节点
    //然后从节点a开始 把所有祖先节点放入hashset 然后b的祖先与set里的节点比较
    //遇到的第一个就是最小公共祖先
    public static TreeNode lCA(TreeNode root, TreeNode A, TreeNode B) {
        HashMap<TreeNode,TreeNode> fatherMap = new HashMap<>();
        fatherMap.put(root,root);

        findFather(root,fatherMap);
        HashSet<TreeNode> set = new HashSet<>();
        TreeNode cur = A;
        while (cur != fatherMap.get(cur)){
            set.add(cur);
            cur = fatherMap.get(cur);
        }

        cur = B;
        while (!set.contains(cur)){
            cur = fatherMap.get(cur);
        }
        return cur;
    }
    public static void findFather(TreeNode root,HashMap<TreeNode,TreeNode> fatherMap){
        if (root == null)return;

        if (root.left != null)fatherMap.put(root.left,root);
        if (root.right != null)fatherMap.put(root.right,root);
        findFather(root.left,fatherMap);
        findFather(root.right,fatherMap);

    }

    //第二种解法 还是递归
    public static TreeNode lCA2(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null || root == A || root == B)return root;//basecase
        TreeNode left = lCA2(root.left,A,B);
        TreeNode right = lCA2(root.right,A,B);
        if(left != null && right != null){
            return root;
        }

        return left != null ? left : right;

//        //或者这样写 更好理解

//        if (root == null || root == A || root == B)return root;//basecase
//        TreeNode left = lCA2(root.left,A,B);
//        TreeNode right = lCA2(root.right,A,B);

//        if(left != null && right != null)return root;
//        if(left != null) return left;
//        if(right != null) return right;

    }

    // 在二叉树中找到后继节点 后继节点就是中序遍历中的下一个节点 左子树的最右节点
    //如果常规情况直接中序遍历 但是要优化
    //分两种情况 1我有右子树，右子树的最左节点就是我的后继节点
    //2 x没有右树 我是不是我父节点的左节点
    //没有就返回父节点 直到找到父节点的左节点y就是x的后继
    //但是考虑二叉树上总有一个节点没有父节点 所以他没右子树 也没有祖先结点是左节点

    //这个需要TreeNode类里有个指针指向父节点
    //因为treenode类没有 parent指针 所以注释了

//    public static TreeNode findSuccessor(TreeNode node){
//        if (node == null) return null;
//        if (node.right != null){
//            return findLeftMost(node.right);
//        }else { //没有右子树
//            TreeNode parent = node.parent;
//            while (parent != null && node != parent.left){
//                //找到一个节点是我父亲的左孩子 这个节点的父节点就是后继节点
//                node = parent;
//                parent = node.parent;
//            }
//            return parent;
//        }
//
//    }
//
//  //node == node.parent.left ? return node.parent : node = node.parent;

    public static TreeNode findLeftMost(TreeNode node){
        if (node == null) return null;
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    //二叉树的序列化和反序列化


    //折痕凹凸问题 中序遍历
    public static void printAllFolds( int N) {
        printProcess(1,N,true);

    }

    //true 就是凹痕 false就是凸痕
    public static void printProcess(int i, int N, boolean down) {
        if (i > N)  return;
        printProcess(i+1,N,true);
        System.out.println(down ? "ao" : "tu");
        printProcess(i+1,N,false);

    }


    //树形dp

    //二叉树递归
    //获得二叉树种两个节点之间的最大距离
    //向左数要信息，向右树要信息

    //思考 按照头节点是否参与来分

    public static int getMaxDistance(TreeNode head){
        return processMaxDistance(head).maxDistance;
    }
    public static class InfoForMaxDistance{
        public int maxDistance;
        public int height;
        public InfoForMaxDistance(int dis, int h){
            maxDistance = dis;
            height = h;
        }
    }
    public static InfoForMaxDistance processMaxDistance(TreeNode x){
        if (x == null) return new InfoForMaxDistance(0,0);
        InfoForMaxDistance leftInfo = processMaxDistance(x.left);
        InfoForMaxDistance rightInfo = processMaxDistance(x.right);

        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + 1 + rightInfo.height;

        int max = Math.max(p3, Math.max(p1,p2));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new InfoForMaxDistance(max,height);

    }











}


















