import javax.swing.plaf.PanelUI;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {

    //岛屿问题 查找01矩阵中表示的岛屿 递归infect一个点的上下左右
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    infect(grid, i, j);
                }
            }
        }
        return count;
    }

    public static void infect(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;}

        if (grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '2';
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);
    }


    //并查集用到的数据结构 就是把原始值包了一层
    public static class Element<V>{
        public V value;
        public Element(V v){
            value = v;
        }
    }

    public static class UnionFindSet<V>{
        public HashMap<V, Element<V>> elementMap;
        //给一个值 和它对应的包装类 相当于外部调用只有v element是内部包装的
        public HashMap<Element<V>,Element<V>> fatherMap;
        //每个元素和他的father

        public HashMap<Element<V>, Integer> sizeMap;
        //代表元素 和 代表元素所在集合的数量

        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();

            for (V v : list) {
                Element<V> element = new Element<>(v);
                elementMap.put(v, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        public Element<V> findHead(Element<V> element){
            Stack<Element<V>> path = new Stack<>();
            while (element != fatherMap.get(element)){
                path.push(element);
                element = fatherMap.get(element);
            }

            while (!path.isEmpty()){
                fatherMap.put(path.pop(), element);
            }
            return element;
        }

        public boolean isSameSet(V a,V b){
            if(elementMap.containsKey(a) &&elementMap.containsKey(b)){
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }


        public void union(V a,V b){
            if(elementMap.containsKey(a) && elementMap.containsKey(b)){
                Element<V> aFather = findHead(elementMap.get(a));
                Element<V> bFather = findHead(elementMap.get(b));
                if (aFather != bFather){
                    Element<V> big = sizeMap.get(aFather)>= sizeMap.get(bFather) ? aFather: bFather;
                    Element<V> small = big == aFather ? bFather: aFather;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aFather)+ sizeMap.get(bFather));
                    sizeMap.remove(small);

                }



            }
        }




    }
























}
