package com.HashMap;

public class HashMap<K,V> implements Map<K,V> {

    private Entry<K,V>[]table=null;
    int size=0;

    public HashMap(){
        table=new Entry[16];
    }

    /** put 存储
     * 通过key 进行hash
     * 取模16下标
     * 放到数组取
     * 判断当前数组下标
     * 没有值：直接放到下标
     * 有值：用链表next指针引用
     *
     * @param k
     * @param v
     * @return
     */

    @Override
    public V put(K k, V v) {
       int index= hash(k);
       Entry<K,V> entry= table[index];
       if(entry==null){
           table[index]= new Entry<>(k,v,index,null);
           size++;
       }else {
           //链表怎么取存
           //1 2  3  4 个位置
           //4已经有A了 ，此时B下标也是4，B要占用A之前的数组位置，把A挤到一边 B的指针指向A 也即把entry付给next
           table[index]= new Entry<>(k,v,index,entry);
       }
        return table[index].getValue();
    }
    private int hash(K k){
        int i=k.hashCode()%16;//数组的下标 位置
       // return i>=0?i:-i;
        return Math.abs(i);
    }

    /**
     * 把KEY去hash index下标
     * 判断当前的下标是否有值
     *
     * 如果是有值：判断key是否相等》yes：直接返回
     * no：判断next是否为空
     *  不为空：判断key是否相等》yes：直接返回 （不相等继续找next 直到相等或 next为空）
     *  为空：直接返回null
     *
     * 没有值：直接返回为null
     * @param k
     * @return
     */
    @Override
    public V get(K k) {
        if(size==0){
            return null;
        }else{
            int index=hash(k);
            if(null==table[index]){
                return null;
            }
            Entry<K, V> entry=  findValue(k, table[index]);
            return entry.getValue();
        }
    }

    private Entry<K, V> findValue(K k, Entry<K, V> entry) {
        if(entry.getKey().equals(k)||entry.getKey()==k){
            return entry;
        }else{
            if(entry.next!=null){//递归调用，直到 找到 或者next为空
                findValue(k, entry);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    class Entry<K,V> implements Map.Entry<K,V>{
        K k;
        V v;
        int hash;
        Entry<K,V> next;

        public Entry(K k, V v, int hash, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
