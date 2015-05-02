public class FrequencyCounter {
    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (st.contains(word))
                st.put(word, st.get(word)+1);
            else
                st.put(word, 1);
        }

        for (String w : st.keys()) {
            System.out.format("%s : %d\n", w, st.get(w));
        }
    }
}
