/**
 * Created by yangge on 1/27/2016.
 */
public class JavaStringHash {
    String s;

    public JavaStringHash(String s) {
        this.s = s;
    }

    public int hashCode() {
        int hash = 0;
        int skip = Math.max(1, s.length()/8);
        for (int i = 0; i < s.length(); i += skip) {
            hash +=  s.charAt(i) + (hash * 37);
        }
        return hash;
    }

    public static void main(String[] args) {
        String name = "ShannonMelon";
        JavaStringHash hash = new JavaStringHash(name);

        System.out.println(name + "'s hash code = " + hash.hashCode());

        String s = "Aa";
        System.out.println("Aa.hashCode() = " + s.hashCode());
        s = "BB";
        System.out.println("BB.hashCode() = " + s.hashCode());
        s = "AaAaAaAa";
        System.out.println("AaAaAaAa.hashCode() = " + s.hashCode());
    }
}
