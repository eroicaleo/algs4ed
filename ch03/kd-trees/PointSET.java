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
        Point2D bl = new Point2D(rect.xmin(), rect.ymin());
        Point2D tr = new Point2D(rect.xmax(), rect.ymax());
        Iterable<Point2D> points = rbTree.keys(bl, tr);
        Queue<Point2D> pointsInRange = new Queue<Point2D>();
        for (Point2D p : points) {
            if (rect.contains(p))
                pointsInRange.enqueue(p);
        }
        return pointsInRange;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (rbTree.isEmpty())
            return null;

        double minDistance = 10.0;
        Point2D nearestPoint = p;
        Iterable<Point2D> points = rbTree.keys();
        for (Point2D q : points) {
            if (p.distanceTo(q) < minDistance) {
                minDistance = p.distanceTo(q);
                nearestPoint = q;
            }
        }

        return nearestPoint;
    }
    public static void main(String[] args) {
        return;
    }
}
