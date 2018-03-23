import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.Stack;


public class PointSET {
 
   //private int size;
   private SET<Point2D> set;
   
   public PointSET() {
       set = new SET<Point2D>();
   }
   
   public boolean isEmpty() {
       return set.isEmpty();
   }
   
   public int size() {
       return set.size();
   }
   
   public void insert(Point2D p) {
       if (p == null) throw new java.lang.IllegalArgumentException("argument is null") ;
       set.add(p);
   }
   
   public boolean contains(Point2D p) {
       if (p == null) throw new java.lang.IllegalArgumentException("argument is null") ;
       return set.contains(p);
   }
   
   public void draw() {
       for (Point2D point : set)
           StdDraw.point(point.x(), point.y());
   }
   public Iterable<Point2D> range(RectHV rect) {
       if (rect == null) throw new java.lang.IllegalArgumentException("argument is null") ;
       Stack<Point2D> points = new Stack<Point2D>();
       for (Point2D point : set) {
           if (rect.contains(point)) {
               points.push(point);
           }
       }
       return points;
   }
   
   // a nearest neighbor in the set to point p; null if the set is empty 
   public Point2D nearest(Point2D p) {
       if (p == null) throw new java.lang.IllegalArgumentException("argument is null") ;
       if (set.isEmpty()) return null;
       
       double minD =  Double.POSITIVE_INFINITY;;
       Point2D minP = null;
       
       for (Point2D point : set) {
           double distance = point.distanceTo(p);
           if (distance < minD) {
               minD = distance;
               minP = point;
           }
       }
       return minP;
   }
       
       
   public static void main(String[] args) {
       StdOut.println("Hi");
   }
}