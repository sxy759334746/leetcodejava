import java.util.HashMap;

public class Hash {

    //哈希函数和哈希表
    //哈希函数 均匀散列且相同值的哈希一定相同
    //不同值的哈希可能相同（哈希冲突）

    //哈希表中的

    //将40亿条可能相同或不同的数 在1g内存大小下找到出现次数最多的
    //将40亿个数求哈希并取模100 如果等于2 就把他放2号小文件
    //这100个小文件内 含有不同种数的数量几乎相同 （均匀散列的）
    //哈希表处理每个文件中出现次数最大的数 100个数比大小
    //保证内存可以处理 同时相同的数目肯定出现在同一文件里

    //实现随机池randompool 要点就是 两个hashmap
    //存 node，index和index， node 还有size 就是index
    //insert就是 两个都插入 随机一个就random 在size范围内随机
    //delete就 把 node1，index1 替换为node2，index1 删掉n2 i2
    //然后改另一个map为 index1，node2 删掉 i2，n2  size--


    //布隆过滤器    黑名单问题

    //100亿个url 每个64字节
    //查某个url 在不在集合内hashset  有加入 查询  不用删除
    //使用小内存 但是允许小错误

    public static class RandomPool< K >{
        private HashMap<K, Integer> keyIndexMap;
        private HashMap<Integer,K> indexKeyMap;
        private int size;
        public RandomPool(){
            this.keyIndexMap = new HashMap<K, Integer>();
            this.indexKeyMap = new HashMap<Integer,K>();
            this.size = 0;

        }

        public void insert(K key){
            if(!this.keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key, this.size);
                this.indexKeyMap.put(this.size++, key);
            }
        }

        public void delete(K key){
            if(this.keyIndexMap.containsKey(key)){
                int deleteindex = this.keyIndexMap.get(key);
                int lastIndex = --this.size;
                K lastKey = this.indexKeyMap.get(lastIndex);
                this.keyIndexMap.put(lastKey, deleteindex);
                this.indexKeyMap.put(deleteindex, lastKey);
                this.keyIndexMap.remove(lastKey);
                this.indexKeyMap.remove(lastIndex);
            }
        }

        public K geyRandom(){
            if (this.size == 0)return null;
            int randIndex = (int) (Math.random() * this.size);
            return this.indexKeyMap.get(randIndex);
        }

    }


    //n样本量  p失误率
    //可以用 布隆过滤器  允许失误率  类似黑名单的查询  和url的大小没关系
    // m = -1 * (n * ln p)/ (ln 2) ^2 向上取整 100亿url 万分之一失误率 大概26G内存
    // k = ln 2 * (m/n)
    // p真 = ( 1 -  e ^ [-1*(n * k真 / m真)] )  k = 13 m = 28G 失误率 十万分之6

    public static void main(String[] args){
        //位图

        int a = 0;
        //a 32bit
        int [] arr = new int[10]; //32bit *10 320bit
        int i = 178; //想取得第178位的值（0/1）
        int numIndex = i/32;
        int bitIndex = i%32;

        //拿到第178位的状态
        int s = (arr[numIndex] >> (bitIndex) & 1);

        //将第i位状态改为1

        arr[numIndex] = arr[numIndex] | (1<< (bitIndex));

        //将第i位状态改为0

        arr[numIndex] = arr[numIndex] & (~ (1<< bitIndex));
    }


    //一致性哈希
    //一致性哈希 环状存储哈希值 将机器哈希后在环中按段划分区域
    //增删机器都通过顺时针映射规则来迁移数据

    //存在问题：机器数量少 导致负载不均衡
    //解决办法：虚拟节点
    //每个机器分配若干虚拟节点
    //虚拟节点哈希后在环上映射到真实节点
    //增删机器 只需要再 分配/删除 若干数量的虚拟节点给真实机器

    //还可以管理负载 将不同机器按照性能 分配不同的虚拟节点数量 强的多分














}
