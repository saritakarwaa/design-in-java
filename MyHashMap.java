public class MyHashMap<K,V> {
    private static final int INITIAL_SIZE=1<<4;
    private static final int MAX_SIZE=1<<30;
    
    Entry<K,V>[] hashTable;
    class Entry<K,V>{
        K key;
        V value;
        Entry next;
        Entry(K key,V v){
            this.key=key;
            this.value=v;
        }
        public K getKey(){
            return this.key;
        }
        public V getValue(){
            return this.value;
        }
        public void setValue(V value){
            this.value=value;
        }
        public void setKey(K key){
            this.key=key;
        }
    }

    MyHashMap(){
        hashTable=new Entry[INITIAL_SIZE];
    }
    MyHashMap(int capacity){
        int tableSize=tableSizeFor(capacity);
        hashTable=new Entry[tableSize];
    }
    final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAX_SIZE) ? MAX_SIZE : n + 1;
    }
    public void put(K key, V value){
        int hashCode=key.hashCode()%hashTable.length;
        Entry node=hashTable[hashCode];
        if(node==null){
            Entry newNode=new Entry(key,value);
            hashTable[hashCode]=newNode;
        }
        else{
            Entry previousNode=node;
            while(node!=null){
                if(node.key==key){
                    node.value=value;
                    return;
                }
                previousNode=node;
                node=node.next;
            }
            Entry newNode=new Entry(key, value);
            previousNode.next=newNode;
        }
    }
    public V get(K key){
        int hashCode=key.hashCode()%hashTable.length;
        Entry node=hashTable[hashCode];
        while(node!=null){
            if(node.key==key){
                return (V)node.value;
            }
            node=node.next;
        }
        return null;
    }

    public static void main(String[] args) {
        MyHashMap<Integer,String> map=new MyHashMap<>(7);
        map.put(1,"One");
        map.put(2,"Two");
        map.put(3,"Three");
        System.out.println(map.get(2));
        System.out.println(map.get(5));
    }
}
