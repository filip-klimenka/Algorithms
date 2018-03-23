import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;


public class KdTree {
    
    private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL = false;
    
    Node root;
    int count;
    
    private static class Node {
        private Point2D p;
        private RectHV rect;
        boolean orient;
        private Node left;
        private Node right;
        
        public Node(Point2D point, boolean orientation, RectHV rectangle) {
            p = point;
            orient = orientation;
            rect = rectangle;
        }
        
        public int compareTo(Point2D point) {
            if (point == null) throw new java.lang.IllegalArgumentException("argument is null") ;
            
            // if both equal, return 0;
            if (this.p.y() == point.y() && this.p.x() == point.x()) return 0;
            // if vertical, compare y
            if (this.orient == VERTICAL && this.p.x() < point.x()) return -1;
            // if horizontal, compare x
            if (this.orient == HORIZONTAL && this.p.y() < point.y()) return -1;
            
            return +1;
        }
        
        private RectHV getRectangle(Point2D point) {
            if (point == null) throw new java.lang.IllegalArgumentException("argument is null");
            
            int cmp = this.compareTo(point);
            
            if (this.orient == VERTICAL && cmp < 0) return new RectHV(this.rect.xmin(), this.rect.ymin(), this.p.x(), this.rect.ymax());
            else if (this.orient == VERTICAL && cmp >= 0) return new RectHV(this.p.x(), this.rect.ymin(), this.rect.xmax(), this.rect.ymax());
            else if (this.orient == HORIZONTAL && cmp < 0) return new RectHV(this.rect.xmin(), this.rect.ymin(), this.rect.xmax(), this.p.y());
            else return new RectHV(this.rect.xmin(), this.p.y(), this.rect.xmax(), this.rect.ymax());        
        }
    }
    
    //public KdTree() {
    public KdTree() {
        root = null;
        count = 0;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public int size() {
        return count;
    }
    
    
//    public void insert(Point2D p) {
//        // check for uniquiness
//        
//        // if Empty make orientation Vertical
//        // else 
//    }
    
    
    
    public void insert(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException("argument is null") ;
        RectHV r = new RectHV(0.0, 0.0, 1.0, 1.0);
        root = insert(root, p, VERTICAL, r);
    }
    
    
    
    private Node insert(Node x, Point2D p, boolean orientation, RectHV rectangle) {
        if (x == null) {
            count++;
            return new Node(p, orientation, rectangle); // add rectangle here
        }  
        int cmp = x.compareTo(p);
        RectHV rect = x.getRectangle(p);
        if      (cmp < 0) x.left = insert(x.left, p, !x.orient, rect);
        else if (cmp > 0) x.right = insert(x.right, p, !x.orient, rect);
        else x.p = p;
        return x;
    }    
    
    
    

    
    
    
    public boolean contains(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException("argument is null") ;
        
        Node x = root;
        
        while (x != null)
        {
            int cmp = x.compareTo(p);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return true;
        }
        
        return false;
    }
    
    
//    public void draw()
//    public Iterable<Point2D> range(RectHV rect)
//    public Point2D nearest(Point2D p)
    public static void main(String[] args) {
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        
        KdTree kdtree = new KdTree();
        
    }
}