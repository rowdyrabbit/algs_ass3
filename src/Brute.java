import java.awt.*;
import java.util.Arrays;

public class Brute {

    private void solve(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                for (int k = j+1; k < points.length; k++) {
                    double ijSlope = points[i].slopeTo(points[j]);
                    double ikSlope = points[i].slopeTo(points[k]);
                    if (ijSlope == ikSlope) {
                        for (int l = k+1; l < points.length; l++) {
                            double ilSlope = points[i].slopeTo(points[l]);
                            if (ijSlope == ilSlope) {
                                System.out.println("ijslope: " + ijSlope + " ikSlope: " + ikSlope + " ilSlope: " + ilSlope);
                                Point[] sub = new Point[]{points[i], points[j], points[k], points[l]};
                                Arrays.sort(sub);
                                StdOut.printf("%s -> %s -> %s -> %s\n", sub[0], sub[1], sub[2], sub[3]);
                                StdDraw.setPenRadius(0.001);
                                StdDraw.setPenColor(Color.blue);
                                sub[0].drawTo(sub[3]);
                            }
                        }
                    }
                }
            }
        }
    }

//    private void solveUnoptimised(Point[] points) {
//        for (int i = 0; i < points.length; i++) {
//            for (int j = i+1; j < points.length; j++) {
//                for (int k = j+1; k < points.length; k++) {
//                    for (int l = k+1; l < points.length; l++) {
//                        double ijSlope = points[i].slopeTo(points[j]);
//                        double ikSlope = points[i].slopeTo(points[k]);
//                        double ilSlope = points[i].slopeTo(points[l]);
//                        if (ijSlope == ikSlope && ijSlope == ilSlope) {
//                            Point[] sub = new Point[]{points[i], points[j], points[k], points[l]};
//                            Arrays.sort(sub);
//                            StdOut.printf("%s -> %s -> %s -> %s\n", sub[0], sub[1], sub[2], sub[3]);
//                            StdDraw.setPenRadius(0.001);
//                            StdDraw.setPenColor(Color.blue);
//                            sub[0].drawTo(sub[3]);
//                        }
//                    }
//                }
//            }
//        }
//    }


    public static void main(String[] args) {
        Brute brute = new Brute();

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
        brute.solve(points);

        // display to screen all at once
        StdDraw.show(0);
        // reset the pen radius
        StdDraw.setPenRadius();
    }
}
