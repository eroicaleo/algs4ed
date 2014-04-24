public class Interval2DTest {
    public static void main(String[] args) {
        double xlo = 0.2;
        double xhi = 0.5;
        double ylo = 0.5;
        double yhi = 0.6;
        int T = 100000;

        Interval1D x = new Interval1D(xlo, xhi);
        Interval1D y = new Interval1D(ylo, yhi);
        Interval2D box = new Interval2D(x, y);

        box.draw();
        Counter c = new Counter("hits");
        for (int t = 0; t < T; t++) {
            double x1 = 2.0*Math.random();
            double y1 = 2.0*Math.random();
            Point2D p = new Point2D(x1, y1);
            if (box.contains(p)) c.increment();
            else                 p.draw();
        }

        StdOut.println(c);
        StdOut.println(box.area());
    }
}
