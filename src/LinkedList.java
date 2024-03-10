
import java.util.*;

public class LinkedList {
    public static class Node {
        public int value;
        public Node next;
        public Node rand;
        public Node(int data){
            this.value = value;
        }
        
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public static boolean huiwenLinkedList(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //快慢指针 这样比上一个栈的方法空间上更小 n/2
    //找到中点 然后右边入栈 循环弹出和左边对比
    public static boolean huiwenLinkedList2(Node head) {
        if (head == null ||head.next ==null) {
            return true;
        }

        return true;
    }

    //回文链表3 O1的空间复杂度  
    //快慢指针找中点 然后反转右半边链表 循环对比
    public static boolean huiwenLinkedList3(Node head) {
        if (head == null ||head.next ==null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        // 快慢指针找到中间节点
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next; //这个是n2现在到右半边的第一个节点
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {  //右边循环反转
            n3 = n2.next;
            n2.next = n1; //指向中间节点
            n1 = n2;
            n2 = n3;
        }

        return true;
    }
    //反转链表 就是需要两个指针 这种不是递归的方法
    public static Node convertLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        Node pre = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    //反转链表的递归方法
    public static Node convertLinkedList2(Node head) {
        Node pre = null;
        return pre;
    }



    public static Node listPartition(Node head ,int pivot) {
        Node sh = null;  //开始节点头尾 等于区头尾 大于区头尾
        Node st = null;
        Node eh = null;
        Node et = null;
        Node bh = null;
        Node bt = null;
        Node next = null;
        //循环对每个节点分类
        while (head != null){
            next = head.next;
            head.next = null;
            if(head.value < pivot){
                if (sh == null){
                    sh = head;
                    st = head;
                }else {
                    st.next = head;
                    st = head;
                }
            } else if (head.value == pivot) {
                if (eh == null){
                    eh = head;
                    et = head;
                }else {
                    et.next = head;
                    et = head;
                }
            } else if (head.value >pivot) {
                if (bh == null){
                    bh = head;
                    bt = head;
                }else {
                    bt.next = head;
                    bt = head;
                }
            }
            head = next;
        }
        if (st != null){
            st.next = eh;
            et = et == null ? st : et;
        }
        if (et != null){
            et.next = bh;
        }
        return sh != null ? sh: (eh != null ? eh : bh);
    }
    public Node partition(Node head, int x) {
        Node small = null;
        Node large = null;

        while(head != null){
            if(head.value <x){
                small = head;
                small.next = head;
            }else{
                large = head;
                large.next = head;
            }
            head = head.next;
        }
        small.next = large;
        large.next = null;

        return small;
    }

    //复制带有随机节点的链表1 用hashmap《新，老》
    public Node copyListWithRand1(Node head, int x) {
        HashMap<Node,Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null){
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }

        return  map.get(head);
    }

    //复制带有随机节点的链表2 老1-新1-老2-新2...这样排列然后给新的添加rand
    public Node copyListWithRand2(Node head, int x) {
        if(head == null){
            return null;
        }
        Node cur = head;
        Node next = null;

        while (cur != null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            next = cur;
        }
        cur = head;
        Node curCopy = null;
        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            //给新节点添加rand
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;

        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next: null;
            cur = next;
        }
        return res;
    }

    //找链表的入环节点就是快慢指针 相遇 然后快指针回到头节点 快慢每次都移动一步 再相遇
    //找到两个链表的相交节点 具体分情况讨论：
    public static Node getLoopNode(Node head){
        //如果是两个链表都没有环 则两个链表都遍历看最后节点是否相等 且得到两个长度
        //最后节点地址相等 则有相交 此时将较长的链表走 长度差值 步
        //可以直接用n++ n-- 来完成
        //然后两个链表并排走 找到等于的地址 就是相交节点

        //如果两个链表都有环（一个有一个没有是不成立的）
        //则有两种情况 如果相交节点在环外
        //分别找到两个链表的入环节点 如果相等 则相交节点在环外
        //此时情况等同于 无环相交节点 就把入环节点loop 作为节点尾
        //如果 入环节点不相等 即 相交点在环内 则有两个相交点
        //此时 loop1 继续走 如果在回到loop1之前 遇到了 loop2 则相交 否则不相交
        // 入环节点等于相交节点 是特殊情况 都满足

        return null;
    }


    public static void main(String[] args) {
        int q = 10;
        double p = 10.0;
        System.out.println(q == p); //true

    }

}
