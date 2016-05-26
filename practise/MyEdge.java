/**
 * Created by yangge on 5/25/2016.
 */
public class MyEdge implements Comparable<MyEdge> {

    private final int v;
    private final int w;
    private final double weight;

    public MyEdge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("Vertex number must be a nonnegative integer");
        if (w < 0) throw new IllegalArgumentException("Vertex number must be a nonnegative integer");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    @Override
    public int compareTo(MyEdge that) {
        if      (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return +1;
        else                                    return  0;
    }

    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    public static void main(String[] args) {
        MyEdge e = new MyEdge(12, 34, 6.23);
        MyEdge f = new MyEdge(12, 28, 7.29);
        System.out.println("Edge is: " + e);
        System.out.println("Weight is " + e.weight());
        int v = e.either();
        System.out.println("One node is " + v);
        System.out.println("The other node is " + e.other(v));
        if (e.compareTo(f) < 0) {
            System.out.println("Edge " + e + " is less that edge " + f);
        }

    }
}
