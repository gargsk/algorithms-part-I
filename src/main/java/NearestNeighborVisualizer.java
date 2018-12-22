import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class NearestNeighborVisualizer {


    public static void main(String[] args) {

        // initialize the two data structures with point from file
        // String filename = args[0];
        In in = new In("src/input10.txt");
        //PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            //brute.insert(p);
        }

        // process nearest neighbor queries
        StdDraw.enableDoubleBuffering();

        while (true) {
            // the location (x, y) of the mouse
            double x = StdDraw.mouseX();

            double y = StdDraw.mouseY();

            Point2D query = new Point2D(x, y);

            // draw all of the points
            StdDraw.clear();
            //StdDraw.setPenColor(StdDraw.YELLOW);
            //StdDraw.setPenRadius(0.01);
            //brute.draw();

            // draw in red the nearest neighbor (using brute-force algorithm)
            //StdDraw.setPenRadius(0.05);
            //StdDraw.setPenColor(StdDraw.RED);
            //Point2D  p = brute.nearest(query);
            //System.out.println(brute.contains(p));
            //p.draw();
            //StdDraw.show();
            //StdDraw.setPenRadius(0.05);

            // draw in blue the nearest neighbor (using kd-tree algorithm)
            StdDraw.setPenColor(StdDraw.BLUE);
            kdtree.draw();
            StdDraw.setPenRadius(0.05);
            StdDraw.setPenColor(StdDraw.RED);
            Point2D tt  = kdtree.nearest(query);
            tt.draw();
            StdDraw.show();
            StdDraw.pause(1000);
        }
    }
}
