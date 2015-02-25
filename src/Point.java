import java.util.Comparator;

/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
            double p1Slope = slopeTo(p1);
            double p2Slope = slopeTo(p2);
            return Double.compare(p1Slope, p2Slope);
        }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        } else if (this.y == that.y) {
            return +0.0;
        } else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        } else {
            double slope = Double.valueOf(that.y - this.y) / Double.valueOf(that.x - this.x);
            return slope;
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y == that.y && this.x < that.x) {
            return -1;
        } else if (this.y == that.y && this.x == that.x) {
            return 0;
        } else {
            return 1;
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point point1, point2;

        /* Vertical line segments should be +Infinity */
        point1 = new Point(5, 10);
        point2 = new Point(5, 7);
        System.out.println(point1.slopeTo(point2) == Double.POSITIVE_INFINITY);

        /* Horizontal line segments should be +0.0 */
        point1 = new Point(12, 3);
        point2 = new Point(3, 3);
        System.out.println(point1.slopeTo(point2) == +0.0);

        /* The slope of a point with himself should be -Infinity */
        Point p = new Point(1, 5);
        System.out.println(p.slopeTo(p) == Double.NEGATIVE_INFINITY);
    }

}