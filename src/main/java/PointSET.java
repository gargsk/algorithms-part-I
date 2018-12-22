import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {
    private Set<Point2D> point2DSet;

    // construct an empty set of points
    public PointSET() {
        this.point2DSet = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return this.point2DSet.isEmpty();
    }

    // number of points in the set
    public int size() {
        return this.point2DSet.size();
    }

    public void insert(Point2D p) {
        this.point2DSet.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return this.point2DSet.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        //StdDraw.setPenColor(StdDraw.BLACK);
        //StdDraw.setPenRadius(0.01);
        for (Point2D point : this.point2DSet) {
            point.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> pointsList = new ArrayList<>();
        for (Point2D point : this.point2DSet) {
            if (rect.contains(point))
                pointsList.add(point);
        }
        return pointsList;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (this.point2DSet.isEmpty())
            return null;
        Point2D nearestNeighbour = null;
        double dis, minDis = Double.MAX_VALUE;
        for (Point2D point : this.point2DSet) {
            dis = p.distanceSquaredTo(point);
            if (dis < minDis) {
                minDis = dis;
                nearestNeighbour = point;
            }
        }
        return nearestNeighbour;
    }

    // unit testing of the methods (optional)

}
