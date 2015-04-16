public final class Date implements Comparable<Date> {
    private final int mm;
    private final int dd;
    private final int yy;

    public Date(int m, int d, int y) {
        mm = m;
        dd = d;
        yy = y;
    }

    public boolean equals(Object y) {
        if (this == y) return true;

        if (y == null) return false;

        if (this.getClass() != y.getClass()) return false;

        Date that = (Date) y;

        if (this.mm != that.mm) return false;
        if (this.dd != that.dd) return false;
        if (this.yy != that.yy) return false;

        return true;
    }
    public int compareTo(Date that) {
        if (this.equals(that)) return 0;

        if (this.yy < that.yy) {
            return -1;
        } else if (this.yy == that.yy) {
            if (this.mm > that.mm) {
                return -1;
            } else if (this.mm == that.mm) {
               if (this.dd < that.dd) {
                   return -1;
               } else {
                   return 1;
               }
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}
