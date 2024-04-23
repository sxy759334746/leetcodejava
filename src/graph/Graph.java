package graph;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.util.*;
import java.util.Map;

public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    public static Graph createGraph(Integer [][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++){
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];
            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from, new Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight,fromNode,toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);

        }

        return graph;
    }

    //图的宽度优先遍历
    //就是用hashset 那nexts放进去

    public static void bfs(Node node){
        if(node == null)return;
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();

            System.out.println(cur.value); //如果是别的题目就在这里处理value

            for(Node next : cur.nexts){
                if(!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

    //图的深度优先遍历
    public static void dfs (Node node){
        if(node == null) return;
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);

        System.out.println(node.value);

        while (!stack.empty()){
            Node cur = (stack.pop());
            for(Node next : cur.nexts){
                if (!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);

                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

    //拓扑排序 无环有向图
    public static List<Node> sortedTopology(Graph graph){
        //key:某一个node
        //value:剩余的度
        HashMap<Node,Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()){
            inMap.put(node,node.in);
            if (node.in == 0)zeroInQueue.add(node);//添加第一个入度为零的点
        }

        //拓扑排序的结果依次加入result
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts){
                inMap.put(next, inMap.get(next)-1);
                if(inMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }

        return result;
    }


    //生成最小生成树算法 克鲁斯卡尔 只能用于无向图
    //从边出发，将边排序从最小边开始 如果加上当前没有形成环 就加

    //需要有并查集  以下是一个简化版的并查集mysets 提供 issameset 和union方法

//    public static class MySets{
//        public HashMap<Node, List<Node>> setMap;
//        public MySets(List<Node> nodes){
//            for(Node cur : nodes){
//                List<Node> set = new ArrayList<>();
//                set.add(cur);
//                setMap.put(cur,set);
//            }
//        }
//    }
//    public boolean isSameSet(Node from, Node to){
//        List<Node> fromSet = setMap.get(from);
//        List<Node> toSet = setMap.get(to);
//        return fromSet == toSet;
//
//    }
//    public void union(Node from, Node to){
//        List<Node> fromSet = setMap.get(from);
//        List<Node> toSet = setMap.get(to);
//        for(Node toNode : toSet){
//            fromSet.add(toNode);
//            setMap.put(toNode,fromSet);
//        }
//    }


    //边比较器
    public static class EdgeComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2){
            return o1.weight - o2.weight;
        }
    }

    //kruskalMST 克鲁斯卡尔算法 使用并查集  解除注释之后有错是因为并查集的错误

//    public static class UnionFind{
//        //并查集类
//        public static void makeSets(){}
//        public static void isSameSet(){}
//        public static void union(){}
//        public UnionFind(){}
//    }
//    public static Set<Edge> kruskalMST(Graph graph){
//        UnionFind unionFind = new UnionFind();
//        unionFind.makeSets(graph.nodes.values());
//        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator());
//        for (Edge edge : graph.edges)pq.add(edge);
//        Set<Edge> result = new HashSet<>();
//        while (!pq.isEmpty()){
//            Edge edge = pq.poll();
//            if(!unionFind.isSameSet(edge.from, edge.to){
//                result.add(edge);
//                unionFind.union(edge.from, edge.to);
//            }
//        }
//
//        return result;
//    }


    //pirm算法 从点出发 一个点他连接的其他点中边权值最小的纳入
    public static Set<Edge> primMST(Graph graph){
        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> set = new HashSet<>();//存放node 确保已经连接过了
        Set<Edge> result = new HashSet<>();

        for (Node node : graph.nodes.values()){ //for循环为了处理 森林 问题

            //从node点开始
            if (!set.contains(node)){
                set.add(node);
                for(Edge edge : node.edges){ //所有当前点相连的边
                    pq.add(edge);

                }
                while (!pq.isEmpty()){
                    Edge edge = pq.poll();//最小边
                    Node toNode = edge.to;
                    if(!set.contains(toNode)){
                        set.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges)pq.add(nextEdge);
                    }
                }
            }

        }

        return result;
    }

    //dijkstra算法 找最小路径 适用没有负值的边
    public static HashMap<Node, Integer> dijkstra(Node head){
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head,0);

        //表中是从head出发到达的节点和最小距离  默认不在表中意味着是正无穷
        HashSet<Node> selectedNoeds = new HashSet<>();

        Node minNode = getMin(distanceMap, selectedNoeds);
        //从dismap中选一个最小的距离 但不能是在select中的

        while (minNode != null){
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges){
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode,distance + edge.weight);
                }
                distanceMap.put(edge.to,Math.min(distanceMap.get(toNode),distance + edge.weight));

            }
            selectedNoeds.add(minNode);
            minNode = getMin(distanceMap, selectedNoeds);
        }

        return distanceMap;
    }
    public static Node getMin(HashMap<Node,Integer>distanceMap, HashSet<Node>touchedNodes){
        Node min = null;
        int minDis = Integer.MAX_VALUE;
        //ENTRY 将map转化为set
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()){
            Node node = entry.getKey();
            int dis = entry.getValue();
            if (!touchedNodes.contains(node) && dis < minDis){
                min = node;
                minDis = dis;
            }
        }
        return min;
    }
    //这个dij是遍历来选最小距离的minnode  这个可以通过改写堆来优化
    //改写堆就是 当值发生变化的时候可以自己在堆里调整





















}
