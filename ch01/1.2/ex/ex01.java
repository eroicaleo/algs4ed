public class ex01 {
    public static void main(String[] args) {
        double xlo = 0.0;
        double xhi = 1.0;
        double ylo = 0.0;
        double yhi = 1.0;
        int N = 1000;

        Interval1D x = new Interval1D(xlo, xhi);
        Interval1D y = new Interval1D(ylo, yhi);
        Interval2D box = new Interval2D(x, y);

        box.draw();

        double minDistance = 10;
        int i0 = -1;
        int j0 = -1;
        Point2D[] pointArr = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x1 = Math.random();
            double y1 = Math.random();
            Point2D p = new Point2D(x1, y1);
            pointArr[i] = p;
            p.draw();
            for (int j = 0; j < i; j++) {
                if (p.distanceTo(pointArr[j]) < minDistance) {
                    minDistance = p.distanceTo(pointArr[j]);
                    i0 = i;
                    j0 = j;
                }
            }
        }
        StdOut.println("The minimum distance is " + minDistance);
        pointArr[i0].drawTo(pointArr[j0]);
    }
}
