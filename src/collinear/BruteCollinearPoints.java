import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    
    private int count;
    private LineSegment[] seg;
    
    public BruteCollinearPoints(Point[] points) {
        
        if (points == null) {
            throw new java.lang.IllegalArgumentException("argument is null") ;
        }
        
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new java.lang.IllegalArgumentException("point is null");
            }
        }
        
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException("constructor contains a repeated point");
                }
            }
        }
        
        
        
        seg = new LineSegment[points.length];
        
        Point [] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        
        for (int p = 0; p < points.length - 3; p++) {
            //System.out.println("p="+p);
            for (int q = p + 1; q < points.length - 2; q++) {
                //System.out.println("q="+q);
                for (int r = q + 1; r < points.length - 1; r++) {
                    //System.out.println("r="+r);
                    for (int s = r + 1; s < points.length - 0; s++) {
                        //System.out.println("s="+s);
                        
                        if (Double.compare(pointsCopy[p].slopeTo(pointsCopy[q]), pointsCopy[p].slopeTo(pointsCopy[r])) == 0 &&
                            Double.compare(pointsCopy[p].slopeTo(pointsCopy[q]), pointsCopy[p].slopeTo(pointsCopy[s])) == 0){
                              //System.out.println(p+","+q+","+r+","+s);
                              //System.out.println("p="+pointsCopy[p].toString()+"q="+pointsCopy[q].toString()+"r="+pointsCopy[r].toString()+"s="+pointsCopy[s].toString());
                              //System.out.println("pq="+ pointsCopy[p].slopeTo(pointsCopy[q]));
                              seg[count++] = new LineSegment(pointsCopy[p], pointsCopy[s]);
                        }
                    }
                }
            }
        }
    }
    public int numberOfSegments() {
        return count;
    }
    
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[count];
        for (int i=0; i < count; i++){
            result[i] = seg[i];
        }
        return result;
    }
 
    public static void main(String[] args) {
        
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}