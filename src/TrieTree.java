public class TrieTree {
    public static class TrieNode{
        public int pass;//表示多少个路径经过该节点
        public int end;//表示有几个路径在这个节点停下
        public TrieNode[] nexts;
        //也可以用hashmap 或者treemap来表示这个字符是否有路
        //尤其是遇到字符种类太多的时候 HashMap/TreeMap<char, Node>

        public TrieNode(){
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public static class Trie{
        private TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        //插入word
        public void insert(String word){//asdfg[a,s,d,f,g]
            if (word == null)return;
            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chars.length; i++){
                index = chars[i] - 'a';
                if (node.nexts[index] == null){
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }

            node.end++;

        }
        //查询word之前加入过几次
        public int search(String word){
            if (word ==null) return 0;
            char[] chars = word.toCharArray();
            TrieNode node = root;

            int index = 0;
            for (int i = 0; i < chars.length; i++){
                index = chars[i] - 'a';
                if (node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }

            return node.end;
        }
        //所有加入的字符中有几个是以pre这个字符串作为前缀的
         public int prefixNumber(String pre){
             if (pre ==null) return 0;
             char[] chars = pre.toCharArray();
             TrieNode node = root;

             int index = 0;
             for (int i = 0; i < chars.length; i++){
                 index = chars[i] - 'a';
                 if (node.nexts[index] == null){
                     return 0;
                 }
                 node = node.nexts[index];
             }

             return node.pass;
         }
        //删除word 沿途p值-- 最后的e--
        public void delete(String word){
            if (search(word) != 0){//如果有这个路径
                char[] chars = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int index = 0;
                for (int i = 0;i < chars.length; i++){
                    index = chars[i] - 'a';
                    if (--node.nexts[index].pass == 0){
                        // java可以直接将这节点null 后续也没有
                        //c++的话就需要遍历到底然后回头全部null掉
                        node.nexts[index] = null;
                        //
                        return;
                    }
                    node = node.nexts[index];
                }

                node.end--;

            }

        }














    }














}
