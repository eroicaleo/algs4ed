public class KdTree {

    private Node root;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
            this.lb = null;
            this.rt = null;
        }
    }

    // construct an empty set of points
    public KdTree() {
        root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return (size() == 0);
    }

    // number of points in the set
    private int size(Node x) {
        if (x == null) return 0;
        return 1 + size(x.lb) + size(x.rt);
    }
    public int size() {
        return size(root);
    }

    // add the point to the set (if it is not already in the set)
    public Node insert(Node x, boolean isH, RectHV rect, Point2D p) {
        if (x == null) return new Node(p, rect);

        if (p.compareTo(x.p) == 0) return x;

        if (isH) {
            // Horizontal split
            if (p.x() < x.p.x()) x.lb = insert(x.lb, !isH, new RectHV(x.rect.xmin(), x.rect.ymin(), x.p.x(), x.rect.ymax()), p);
            else                 x.rt = insert(x.rt, !isH, new RectHV(x.p.x(), x.rect.ymin(), x.rect.xmax(), x.rect.ymax()), p);
        } else {
            // Vertical split
            if (p.y() < x.p.y()) x.lb = insert(x.lb, !isH, new RectHV(x.rect.xmin(), x.rect.ymin(), x.rect.xmax(), x.p.y()), p);
            else                 x.rt = insert(x.rt, !isH, new RectHV(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.rect.ymax()), p);
        }

        return x;
    }

    public void insert(Point2D p) {
        if (p == null) throw new java.lang.NullPointerException("Trying to insert a null point in kdtree insert method");

        root = insert(root, true, new RectHV(0.0, 0.0, 1.0, 1.0), p);
    }

    // does the set contain point p?
    public boolean contains(Node x, boolean isH, Point2D p) {
        if (x == null) return false;

        if (p.compareTo(x.p) == 0) return true;

        if (isH) {
            // Horizontal split
            if (p.x() < x.p.x()) return contains(x.lb, !isH, p);
            else                 return contains(x.rt, !isH, p);
        } else {
            // Vertical split
            if (p.y() < x.p.y()) return contains(x.lb, !isH, p);
            else                 return contains(x.rt, !isH, p);
        }

    }

    public boolean contains(Point2D p) {
        return contains(root, true, p);
    }

    // draw all points to standard draw
    public void draw(Node x, boolean isH) {
        if (x == null) return;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        StdDraw.point(x.p.x(), x.p.y());

        StdDraw.setPenRadius();
        if (isH) {
            // Draw vertical line through x
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        } else {
            // Draw horizontal line through x
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }
        draw(x.lb, !isH);
        draw(x.rt, !isH);
        return;
    }
    public void draw() {
        draw(root, true);
    }

    // all points that are inside the rectangle
    private void range(Node x, boolean isH, Queue<Point2D> q, RectHV rect) {
        if (x == null) return;

        // Check left subtree if necessary
        if ((isH && rect.xmin() <= x.p.x()) || (!isH && rect.ymin() <= x.p.y())) {
            range(x.lb, !isH, q, rect);
        }
        // Check if point lies in the rectangle
        if (rect.contains(x.p)) {
            q.enqueue(x.p);
        }
        // Check right subtree if necessary
        if ((isH && rect.xmax() >= x.p.x()) || (!isH && rect.ymax() >= x.p.y())) {
            range(x.rt, !isH, q, rect);
        }
    }
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> q = new Queue<Point2D>();
        range(root, true, q, rect);
        return q;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Node x, boolean isH, Point2D p, double min) {
        if (x == null) return null;

        // Step 1: check the distance between current point to query point
        Point2D nearestPoint = null;
        double dist = p.distanceTo(x.p);
        if (dist < min) {
            nearestPoint = x.p;
            min = dist;
        }

        // Step 2: Determine we search left or right first?
        Node firstNode = null;
        Node secondNode = null;
        if ((isH  && x.lb != null && p.x() < x.lb.p.x()) ||
            (!isH && x.lb != null && p.y() < x.lb.p.y())) {
                firstNode = x.lb;
                secondNode = x.rt;
        } else {
                firstNode = x.rt;
                secondNode = x.lb;
        }

        // Step 3: Search first subtree
        if (firstNode != null && (firstNode.rect.contains(p) || firstNode.rect.distanceTo(p) < min)) {
            Point2D p1 = nearest(firstNode, !isH, p, min);
            if (p1 != null) {
                nearestPoint = p1;
                min = p.distanceTo(p1);
            }
        }

        // Step 4: Search second subtree
        if (secondNode != null && (secondNode.rect.contains(p) || secondNode.rect.distanceTo(p) < min)) {
            Point2D p1 = nearest(secondNode, !isH, p, min);
            if (p1 != null) {
                nearestPoint = p1;
                min = p.distanceTo(p1);
            }
        }
        return nearestPoint;
    }
    public Point2D nearest(Point2D p) {
        return nearest(root, true, p, 10);
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);

        KdTree kdtree = new KdTree();

        // Test isEmpty()
        StdOut.println("The kdtree is empty or not: " + kdtree.isEmpty());

        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }

        // Test size()
        StdOut.println("The kdtree size is: " + kdtree.size());

        // Test contains()
        StdOut.println("The kdtree contains (0.206107 0.095492): " + kdtree.contains(new Point2D(0.206107, 0.095492)));
        StdOut.println("The kdtree contains (0.5 0.1): " + kdtree.contains(new Point2D(0.5, 0.1)));
        StdOut.println("The kdtree contains (0.5 1.0): " + kdtree.contains(new Point2D(0.5, 1.0)));
    }

}
