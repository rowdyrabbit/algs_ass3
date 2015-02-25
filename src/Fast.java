import java.awt.*;
import java.util.*;
import java.util.List;

public class Fast {

    private void solve(Point[] points) {

        for (int i = 0; i < points.length; i++) {
            List<Point> temp = new ArrayList<Point>();
            temp.addAll(Arrays.asList(points));
            temp.remove(points[i]);

            //calculate slope for all other points.
            Collections.sort(temp, points[i].SLOPE_ORDER);

            int first = 0;
            while (first < temp.size()) {
                double slopeToFirst = points[i].slopeTo(temp.get(first));
                int last = first + 1;
                while (last < temp.size() && slopeToFirst == points[i].slopeTo(temp.get(last))) {
                    last++;
                }
                connectPoints(temp, first, last, points[i]);
                first = last;
            }
        }
    }

    private void connectPoints(List<Point> points, int firstIndex, int lastIndex, Point currPoint) {
        if (lastIndex - firstIndex < 3) {
            return;
        }

        Point[] sub = new Point[lastIndex - firstIndex + 1];
        int count = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            sub[count] = points.get(i);
            count++;
        }
        sub[sub.length-1] = currPoint;

        Arrays.sort(sub);
        if (sub[0].compareTo(currPoint) == 0) { //stupid because the API won't let me override equals!
            String output = new String();
            for (int i = 0; i < sub.length; i++) {
                output += sub[i].toString();
                if (i < (sub.length-1) ) {
                    output += " -> ";
                }
            }
            StdOut.println(output);
            StdDraw.setPenRadius(0.001);
            StdDraw.setPenColor(Color.blue);
            sub[0].drawTo(sub[sub.length - 1]);
        }
    }


    public static void main(String[] args) {
        Fast fast = new Fast();

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            p.draw();
        }
        fast.solve(points);

        // display to screen all at once
        StdDraw.show(0);
        // reset the pen radius
        StdDraw.setPenRadius();
    }
}
