public class RandomConnections {
    public static void DrawAndConnect(int N, double p) {
        StdDraw.setXscale(-2.0, 2.0);
        StdDraw.setYscale(-2.0, 2.0);
        StdDraw.circle(0.0, 0.0, 1.0);
        double[] xArray = new double[N];
        double[] yArray = new double[N];
        for (int i = 0; i < N; ++i) {
            xArray[i] = Math.cos(2.0*Math.PI/N*i);
            yArray[i] = Math.sin(2.0*Math.PI/N*i);
            StdDraw.circle(xArray[i], yArray[i], 0.05);
        }
        for (int i = 0; i < N; ++i) {
            for (int j = i; j < N; ++j) {
                if (StdRandom.uniform(0.0, 1.0) < p) {
                    StdDraw.line(xArray[i], yArray[i], xArray[j], yArray[j]);
                }
            }
        }
        return;
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);
        DrawAndConnect(N, p);
    }
}
