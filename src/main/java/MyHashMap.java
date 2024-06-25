public class MyHashMap <K, V>{

    private static final int INITIAL_SIZE = 1<<4; //16
    private static final int MAXIMUM_SIZE = 1<<30;// 2^30
    class Entry<K, V>{
        public K key;
        public V value;
        public Entry next;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    Entry[] hashTable;

    MyHashMap(){
        hashTable = new Entry[INITIAL_SIZE];
    }

    MyHashMap(int size){
        int capacity = calculateCapacity(size);
        hashTable = new Entry[capacity];
    }

    private static int calculateCapacity(int n) {
        n = n-1;  // To handle corner case like 16, 32 etc
        n|= n>>1;
        n|= n>>2;
        n|= n>>4;
        n|= n>>8;
        n|= n>>16;
        return (n<0) ? 1 : (n>MAXIMUM_SIZE) ? MAXIMUM_SIZE : n+1;
    }

    public void put(K key, V value){
        int hashCode = key.hashCode() % hashTable.length;
        Entry node = hashTable[hashCode];
        if(node == null){
            Entry newNode = new Entry(key, value);
            hashTable[hashCode] = newNode;
        }else{
            Entry previousNode = hashTable[hashCode];
            while(node != null){
                if(node.key.equals(key)){
                    node.value = value;
                }
                previousNode = node;
                node = node.next;
            }
            Entry newNode = new Entry(key,value);
            previousNode.next = newNode;
        }
    }

    public V get(K key){
        int hasCode = key.hashCode()%hashTable.length;
        Entry node = hashTable[hasCode];
        while(node!=null){
            if(key.equals(node.key)){
                return (V) node.value;
            }
            node = node.next;
        }
        return null;
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(0,"Hi");
        myHashMap.put(2,"My");
        myHashMap.put(3,"Name");
        myHashMap.put(4,"Nishant");
        System.out.println(myHashMap.get(0));
        System.out.println(myHashMap.get(2));
        System.out.println(myHashMap.get(3));
        System.out.println(myHashMap.get(4));

        System.out.println(calculateCapacity(14));
        System.out.println(calculateCapacity(7));
        System.out.println(calculateCapacity(17));
        System.out.println(calculateCapacity(16));
        System.out.println(calculateCapacity(1));


    }


}
