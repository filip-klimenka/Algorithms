import java.lang.Math;
import java.util.Random;


public class Circle
{
    // for simplicity we consider a circle with center (0,0) and radius r = 1.
    static int trials = 1000;
    static double twoPi = 2 * Math.PI;
    static double radius = 1.0;
    static double side = Math.sqrt(3.0);
    static Random randomGenerator = new Random();

    public static void main(String[] args) 
    {
        // (a) To generate a chord of a random length we fix one point at a coordinate (1, 0)
        // and generate a point by simulating an angle theta from uniform [0, 2 * Pi).
           
        
        int countChordLargerThanSide = 0;
        
        for (int i = 0; i < trials; i++)
        {   
            
            // 1st point
            double theta1 = twoPi * randomGenerator.nextDouble();
            double x1 = Math.cos(theta1);
            double y1 = Math.sin(theta1);
            // 2nd point
            double theta2 = twoPi * randomGenerator.nextDouble();
            double x2 = Math.cos(theta2);
            double y2 = Math.sin(theta2);
            // distance between two points
            double chord1 = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
            
            if (chord1 > side) countChordLargerThanSide++;
        }
        
        double p1 = countChordLargerThanSide / (double) trials;
        System.out.println("p1 = " + p1);
    
        
        
        
        // (b) To answer this question, we need to generate a point inside the circle.
        // We do this using polar coordinates additionally ensuring the uniformity
        // using the square root transformation of the radius

        int countChordFromMidpointLargerThanSide = 0;

        for (int i = 0; i < trials; i++)
        {
            double theta = twoPi * randomGenerator.nextDouble();
            double r = randomGenerator.nextDouble();
           
            double x = Math.sqrt(r) * Math.cos(theta);
            double y = Math.sqrt(r) * Math.sin(theta);
           
            double distFromCentre = Math.sqrt((x-0.0)*(x-0.0) + (y-0.0)*(y-0.0));
            double halfChord = Math.sqrt(radius*radius - distFromCentre*distFromCentre);
            double chord2 = halfChord * 2.0;
           
            if (chord2 > side) countChordFromMidpointLargerThanSide++;
        }


        double p2 = countChordFromMidpointLargerThanSide / (double) trials; 
        System.out.println("p2 = " + p2);
        
        
        // (c) We simulate two points, compute the length of the line from the centre
        // orthogonal to the line passing through these two points.
        // Using this line we then compute half of the chord passing through simulated 2 points
        // and then obtain the length of the whole chord.
        
        int countChordFrom2PointsLargerThanSide = 0;
        for (int i = 0; i < trials; i++)
        {
            // 1st point
            double theta1 = twoPi * randomGenerator.nextDouble();
            double r1 = randomGenerator.nextDouble();
            double x1 = Math.sqrt(r1) * Math.cos(theta1);
            double y1 = Math.sqrt(r1) * Math.sin(theta1);
            // 2nd point
            double theta2 = twoPi * randomGenerator.nextDouble();
            double r2 = randomGenerator.nextDouble();
            double x2 = Math.sqrt(r2) * Math.cos(theta2);
            double y2 = Math.sqrt(r2) * Math.sin(theta2);    
            // compute distance between 2 points
            double d12 = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
            // compute distance to the centre
            double d01 = Math.sqrt((x1-0.0)*(x1-0.0) + (y1-0.0)*(y1-0.0));
            double d02 = Math.sqrt((x2-0.0)*(x2-0.0) + (y2-0.0)*(y2-0.0));
            // compute half perimeter
            double S = (d01 + d02 + d12) / 2.0;
            // find area of the triangle composed of d01, d02, d12 using Heron's formula
            double A = Math.sqrt(S * (S - d01) * (S - d02) * (S - d12));
            // get the height of the triangle: line from the centre perpedicular to d12
            double height = 2 * A / d12;
            // use height to find half of the chord that lies on d12
            double halfChord = Math.sqrt(radius*radius - height*height);
            double chord3 = halfChord * 2.0;
                
            if (chord3 > side) countChordFrom2PointsLargerThanSide++;    
        }
        double p3 = countChordFrom2PointsLargerThanSide / (double) trials;
        System.out.println("p3 = " + p3);
    }
}