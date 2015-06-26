public class PointSET {
    private RedBlackBST rbTree;

    // construct an empty set of points
    public PointSET() {
        rbTree = new RedBlackBST<Point2D, Integer>();
    }
    // is the set empty?
    public boolean isEmpty() {
        return rbTree.isEmpty();
    }
    // number of points in the set
    public int size() {
        return rbTree.size();
    }
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        rbTree.put(p, 0);
        return;
    }
    // does the set contain point p?
    public boolean contains(Point2D p) {
        return rbTree.contains(p);
    }
    // draw all points to standard draw
    public void draw() {
        Iterable<Point2D> points = rbTree.keys();
        for (Point2D p : points) {
            StdDraw.point(p.x(), p.y());
        }
    }
    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return rbTree.keys();
    }

    // // a nearest neighbor in the set to point p; null if the set is empty
    // public           Point2D nearest(Point2D p) {
    // }
    public static void main(String[] args) {
        return;
    }
}
