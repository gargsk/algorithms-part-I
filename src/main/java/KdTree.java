import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private static final RectHV TILE = new RectHV(0, 0, 1, 1);

    private Node root;

    private int rootSize;

    public KdTree() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        if (isEmpty())
            return 0;
        return rootSize;
    }

    public boolean contains(Point2D queryPoint) {
        return getSplitDirection(root, queryPoint) != null;
    }

    private  SplitDirection getSplitDirection(Node x, Point2D queryPoint) {
        if (x == null)
            return null;
        int cmp = (x.compareTo(queryPoint));
        if (cmp < 0)
            return getSplitDirection(x.left, queryPoint);
        else if (cmp > 0)
            return getSplitDirection(x.right, queryPoint);
        else
            return x.splitDirection;
    }

    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("insert is call with null point");
        root = insert(root, p, SplitDirection.VERTICAL);
    }

    private Node insert(Node x, Point2D p, SplitDirection splitDirection) {
        // write return condition first
        if (x == null) {
            rootSize++;
            return new Node(p, splitDirection);
        }
        if (x.p.equals(p))
            return x;
        int cmp = x.compareTo(p);
        if (cmp < 0)
            x.left = insert(x.left, p, splitDirection.opposite());
        else
            x.right = insert(x.right, p, splitDirection.opposite());
        return x;
    }

    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> range = new Queue<>();
        inRect(root, TILE, rect, range);
        return range;
    }

    private void inRect(Node x, RectHV nodeRect, RectHV rect, Queue<Point2D> range) {
        // if the rectangle containing node intersect with range rectangle then check whether poont in range rectangle or not
        if (x != null && rect.intersects(nodeRect)) {
            if (rect.contains(x.p))
                range.enqueue(x.p);
            // check same condition for left anf right child nodes
            inRect(x.left, getChildRect(x, nodeRect, true), rect, range);
            inRect(x.right, getChildRect(x, nodeRect, false), rect, range);
        }
    }

    private RectHV getChildRect(Node childNode, RectHV parentNodeRect, boolean leftChild) {
        if (childNode.splitDirection.equals(SplitDirection.VERTICAL)) {
            // compare x order for both left and right child nodes
            if (leftChild)
                return new RectHV(parentNodeRect.xmin(), parentNodeRect.ymin(), childNode.p.x(), parentNodeRect.ymax());
            else
                return new RectHV(childNode.p.x(), parentNodeRect.ymin(), parentNodeRect.xmax(), parentNodeRect.ymax());
        } else {
            // compare y order
            if (leftChild)
                return new RectHV(parentNodeRect.xmin(), parentNodeRect.ymin(), parentNodeRect.xmax(), childNode.p.y());
            else
                return new RectHV(parentNodeRect.xmin(), childNode.p.y(), parentNodeRect.xmax(), parentNodeRect.ymax());
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("neares call will null value");
        return nearest(root, TILE, p, null, Double.MAX_VALUE);
    }

    private Point2D nearest(Node x, RectHV xRect, Point2D p, Point2D nearest, double minDistance) {
        if (x != null && xRect.distanceSquaredTo(p) < minDistance) {
            double tempMinDistance = x.p.distanceSquaredTo(p);
            if (tempMinDistance < minDistance) {
                minDistance = tempMinDistance;
                nearest = x.p;
            }
            int cmp = x.compareTo(p);
            if (cmp < 0) {
                nearest = nearest(x.left, getChildRect(x, xRect, true), p, nearest, minDistance);
                nearest = nearest(x.right, getChildRect(x, xRect, false), p, nearest, nearest.distanceSquaredTo(p));
            } else if (cmp > 0) {
                nearest = nearest(x.right, getChildRect(x, xRect, false), p, nearest, minDistance);
                nearest = nearest(x.left, getChildRect(x, xRect, true), p, nearest, nearest.distanceSquaredTo(p));
            }

        }
        return nearest;
    }


    public void draw() {
        if (isEmpty()) return;
        draw(root, TILE);
    }

    private void draw(Node x, RectHV rect) {
        if (x != null) {
            if (x.splitDirection.equals(SplitDirection.VERTICAL)) {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.01);
                x.p.draw();
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(0.001);
                StdDraw.line(x.p.x(), rect.ymax(), x.p.x(), rect.ymin());
            }
            if (!x.splitDirection.equals(SplitDirection.VERTICAL)) {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.01);
                x.p.draw();
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(0.001);
                StdDraw.line(rect.xmax(), x.p.y(), rect.xmin(), x.p.y());
            }
            draw(x.left, getChildRect(x, rect, true));
            draw(x.right, getChildRect(x, rect, false));
        }

    }


    private enum SplitDirection {
        VERTICAL {
            public SplitDirection opposite() {
                return HORIZONTAL;
            }
        },
        HORIZONTAL {
            public SplitDirection opposite() {
                return VERTICAL;
            }
        };

        public SplitDirection opposite(){ return null;}
    }

    private class Node {
        Point2D p;
        Node left, right;
        SplitDirection splitDirection;

        public Node(Point2D p, SplitDirection splitDirection) {
            this.p = p;
            this.splitDirection = splitDirection;
            this.left = null;
            this.right = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            if (p != null ? !p.equals(node.p) : node.p != null) return false;
            if (left != null ? !left.equals(node.left) : node.left != null) return false;
            if (right != null ? !right.equals(node.right) : node.right != null) return false;
            return splitDirection == node.splitDirection;
        }

        @Override
        public int hashCode() {
            int result = p != null ? p.hashCode() : 0;
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            result = 31 * result + (splitDirection != null ? splitDirection.hashCode() : 0);
            return result;
        }

        public int compareTo(Point2D p) {
            // based on the SplitDirection of the node we will compare x order and y order of the points
            if (this.p.equals(p))
                return 0;
            if (this.splitDirection.equals(SplitDirection.VERTICAL)) {
                if (p.x() < this.p.x())
                    return -1;
            }
            if (this.splitDirection.equals(SplitDirection.HORIZONTAL)) {
                if (p.y() < this.p.y())
                    return -1;
            }
            return 1;
        }
    }
}
