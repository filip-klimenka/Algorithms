import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int n;
    private Item[] s;
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[2]; }
    
    public boolean isEmpty()
    {  return n == 0; }
    
    public int size()
    {  return n; }
    
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Adding null item");
        }
        
        if (n == s.length) resize(2 * s.length);
        s[n] = item;
        n++;
    }
    
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++){
            copy[i] = s[i];
        }
        s = copy;
    }

    public Item dequeue() {
        if (n == 0){
            throw new java.util.NoSuchElementException("RQ is empty");
        }
        
        int id = StdRandom.uniform(n);
        Item returnItem = s[id];
        exchange(id);
        n--;
        if ((n > 0) && (n == (s.length) / 4)) {resize(s.length / 2); }
        return returnItem;
    }
    
    private void exchange(int randomIndex) {
        Item temp  = s[n-1];
        s[n-1] = s[randomIndex];
        s[randomIndex] = temp;
    }
    
    public Item sample() {
        if (n == 0){
            throw new java.util.NoSuchElementException("RQ is empty");
        }        
        int id = StdRandom.uniform(n);
        Item returnItem = s[id];
        return returnItem;
        
    }
    public Iterator<Item> iterator() {
        return new ListIterator();
    }    

    private class ListIterator implements Iterator<Item> {
        private int numberUnwatchedItems;
        private final int[] indexSequence;
        
        private ListIterator() {
            numberUnwatchedItems = n;
            indexSequence = new int[n];
            for (int i = 0; i < n; i++)
            { indexSequence[i] = i; }
            
            
            StdRandom.shuffle(indexSequence);
        }
        public boolean hasNext() {
            return numberUnwatchedItems > 0;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Unsupported");
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("Nothing left");
            }
            numberUnwatchedItems--;
            return s[indexSequence[numberUnwatchedItems]];
        }
    }
    
    public static void main(String[] args)
    {
        int[] a = {1, 2, 3};
        int retItem = a[0];
        int temp = a[2];
        a[2] = a[0];
        a[0] = temp;
        System.out.println(retItem);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        for (String s: rq)
            System.out.println(s);        
    }
}