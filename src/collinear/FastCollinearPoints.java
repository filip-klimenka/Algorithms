import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    
    private int count;
    private LineSegment[] seg;
    //private boolean isSegment = false;
    private int counter = 0;
    private boolean isContained = false;
    private Point[] lastPointSeg;
    private Point[] firstPointSeg;
    //private double currSlope;
    
    public FastCollinearPoints(Point[] points) {
        System.out.println("Hello");
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
        firstPointSeg = new Point[points.length];
        lastPointSeg = new Point[points.length];
        
        for (int i = 0; i < points.length; i++) {
            
            Point [] pointsCopy = Arrays.copyOf(points, points.length);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());
            
            counter = 2;
            //currSlope = pointsCopy[0].slopeTo(pointsCopy[1]);
            
            for (int j = 2; j < pointsCopy.length; j++) {
                //System.out.println(j);
                // if j is last point and slopes are equal
                if (Double.compare(pointsCopy[0].slopeTo(pointsCopy[j]), pointsCopy[0].slopeTo(pointsCopy[j - 1])) == 0 &&
                    j == pointsCopy.length - 1) {
                        counter++;
                        if (counter > 3) {
                        // construct & sort the slopePoints
                        int last   = pointsCopy.length - 1;
                        int first = last - counter + 1;
                        Point [] slopePoints = new Point[counter];
                        slopePoints[0] = pointsCopy[0]; 
                        for (int k = 1; k < slopePoints.length; k++)
                            slopePoints[k] = pointsCopy[k + first];
                        Arrays.sort(slopePoints);
                        for (int qqq=0; qqq<slopePoints.length; qqq++)
                            //System.out.println(slopePoints[qqq]);
                        // check if this new line segment (ls) is already in seg
                        if (count > 0) {
                            // check if this new line segment (ls) is already in seg
                            for (int n = 0; n < count; n++){
                                if ((firstPointSeg[n]).compareTo(slopePoints[0]) == 0 && 
                                    (lastPointSeg[n]).compareTo(slopePoints[counter-1]) == 0) {
                                    isContained = true;
                                }
                            }                       
                        }
                        
                        else {isContained = false; }

                        if (!isContained) {
                            seg[count] = new LineSegment(slopePoints[0], slopePoints[counter-1]);;
                            firstPointSeg[count] = slopePoints[0];
                            lastPointSeg[count] = slopePoints[counter-1];
                            count++;
                        }
                        
                       isContained = false;
                       counter = 2;
                    }
                    
                }
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                // if j is not the last point and slopes are equal
                else if (Double.compare(pointsCopy[0].slopeTo(pointsCopy[j]), pointsCopy[0].slopeTo(pointsCopy[j - 1])) == 0 &&
                    j != pointsCopy.length - 1) {
                    counter++;
                    //currSlope = pointsCopy[0].slopeTo(pointsCopy[j]);
                }
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////
                else {
                    if (counter > 3) {
                        // construct & sort the slopePoints
                        int last   = j - 1;
                        int first = last - counter + 1;
                        Point [] slopePoints = new Point[counter];
                        slopePoints[0] = pointsCopy[0]; 
                        for (int k = 1; k < slopePoints.length; k++)
                            slopePoints[k] = pointsCopy[k + first];
                        Arrays.sort(slopePoints);
                        
                        // check if this new line segment (ls) is already in seg
                        if (count > 0) {
                            // check if this new line segment (ls) is already in seg
                            for (int n = 0; n < count; n++){
                                if ((firstPointSeg[n]).compareTo(slopePoints[0]) == 0 && 
                                    (lastPointSeg[n]).compareTo(slopePoints[counter-1]) == 0) {
                                    isContained = true;
                                }
                            }                       
                        }
                        
                        else {isContained = false; }

                        if (!isContained) {
                            seg[count] = new LineSegment(slopePoints[0], slopePoints[counter-1]);;
                            firstPointSeg[count] = slopePoints[0];
                            lastPointSeg[count] = slopePoints[counter-1];
                            count++;
                        }
                        
                       isContained = false;
                       counter = 2;
                    }
                    else {counter = 2; }
                }
                //////////////////////////////////////////////////////////////////////////////////////////////////////////
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
//   }
        
        // draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();
        
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }      
}