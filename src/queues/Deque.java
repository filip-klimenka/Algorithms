import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    
    private Node first, last;
    private int count;
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    /**
     * construct an empty deque
     */    
    public Deque() {
        first = null;
        last = first;
        count = 0;
    }
    /**
     * is the deque empty?
     */       
    public boolean isEmpty() {
        return (first == null || last == null);
    }
    
    /**
     * return the number of items on the deque
     */      
    public int size() {
        return count; 
    }
     
    /**
     * add the item to the front
     */          
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Adding a null item"); 
        }
        
        
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) last = first;
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        count++;
    }
    
    /**
     * add the item to the end
     */      
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Adding a null item");
        }
        
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else {
            last.prev = oldlast;
            oldlast.next = last;
        }
        count++;
    }
          
    /**
     * remove and return the item from the front
     */     
    public Item removeFirst() {
        if (isEmpty()) { 
            throw new java.util.NoSuchElementException(
                                                "Removing from empty deque");
        }
         
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        else           first.prev = null;
        count--;
        return item;
    }
                  
    /**
     * remove and return the item from the end
     */          
    public Item removeLast() {
        if (isEmpty()) { 
            throw new java.util.NoSuchElementException(
                                               "Removing from empty deque");
        }
        Item item = last.item;
        last = last.prev;
        if (isEmpty()) first = last;
        else           last.next = null;
        count--;
        return item;
    }
     
     
    /**
     * return an iterator over items in order from front to end
     */    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
     
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
         
        public boolean hasNext() {
            return current != null;
        }
        public void remove() { 
            throw new java.lang.UnsupportedOperationException(
                                                 "remove is not supported");
        }
        public Item next() {
            if (!hasNext()) { 
                throw new java.util.NoSuchElementException(
                                                 "no more items to return");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
          
   /**
    * unit testing (optional)    
    */
    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        d.addFirst("a");
        d.addFirst("b");
        d.addFirst("c");
        //System.out.println(d.first.item);
        //System.out.println(d.last.item);
        //System.out.println(d.size());
        //String a = d.removeLast();
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        d.addFirst("c");
        //System.out.println(d.removeLast());
        //System.out.println(d.removeLast());
        System.out.println(d.size());
        //System.out.println(d.first);
        for (String s: d)
            System.out.println(s);
       
    }
}