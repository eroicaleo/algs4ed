public class Transaction implements Comparable<Transaction> {
    private String user;
    private String date;
    private double cash;

    public int compareTo(Transaction that) {
        double diff = this.cash - that.cash;
        if (diff < 0)      return -1;
        else if (diff > 0) return 1;
        else               return 0;
    }

    public Transaction(String line) {
        String[] sa = line.split(" +");
        this.user = sa[0];
        this.date = sa[1];
        this.cash = Double.parseDouble(sa[2]);
    }

    public void print() {
        System.out.format("User: %s, date: %s, cash: %.2f\n", user, date, cash);
    }

    public static void main(String[] args) {
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction t = new Transaction(line);
            t.print();
        }
        return;
    }
}
