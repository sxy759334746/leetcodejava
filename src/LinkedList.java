
import java.util.*;

public class LinkedList {
    public static class Node {
        public int value;
        public Node next;
        
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

    public static void listPartition(Node head ,int pivot) {
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
    }
    public static void main(String[] args) {

    }

}
