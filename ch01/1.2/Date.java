public class Date {
    private final int year;
    private final int month;
    private final int day;
    public Date(int y, int m, int d) {
        year = y;
        month = m;
        day = d;
    }
    public int year() {
        return year;
    }
    public int month() {
        return month;
    }
    public int day() {
        return day;
    }
    public String toString() {
        return month + "/" + day + "/" + year;
    }
    static public void main(String[] args) {
        Date d = new Date(1984, 6, 23);
        StdOut.println(d);
    }
}
