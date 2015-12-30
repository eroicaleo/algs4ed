/**
 * Created by yangge on 12/29/2015.
 */
public class LinearProbingHashST<Key, Value> {
    private int M = 30000;
    private Key[]   keys = (Key[])   new Object[M];
    private Value[] vals = (Value[]) new Object[M];

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (key.equals(keys[i]))
                break;
        }
        keys[i] = key;
        vals[i] = val;
    }

    public Value get(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (key.equals(keys[i]))
                return vals[i];
        }
        return null;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, String> minions = new LinearProbingHashST<String, String>();

        minions.put("Kelvin", "Banana");
        minions.put("Stuart", "Potato");
        minions.put("Bob", "Teddy");

        System.out.println(minions.get("Kelvin"));
        System.out.println(minions.get("Stuart"));
        System.out.println(minions.get("Bob"));
        System.out.println(minions.get("Banana"));

    }
}
