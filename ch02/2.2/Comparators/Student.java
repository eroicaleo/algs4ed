import java.util.Comparator;
import java.util.ArrayList;

public class Student {
    public static final BySection BY_SECTION = new BySection();
    public static final ByName BY_NAME = new ByName();

    private String name;
    private int section;
    private String phone;
    private String address;

    public Student(String n, int s, String p, String a) {
        name = n;
        section = s;
    }
    public void printStudent() {
        System.out.format("%-10s %d\n", name, section);
    }
    private static class BySection implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.section - w.section;
        }
    }
    private static class ByName implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.name.compareTo(w.name);
        }
    }
    public static void main(String[] args) {

        ArrayList<Student> als = new ArrayList<>();
        als.add(new Student("Battle", 4, "874-088-1212", "121 Whitman"));
        als.add(new Student("Furia", 1, "766-093-9873", "101 Brown"));
        als.add(new Student("Gazsi", 2, "766-093-9873", "101 Brown"));
        als.add(new Student("Rohde", 2, "232-343-5555", "343 Forbes"));
        als.add(new Student("Andrews", 3, "664-480-0023", "097 Little"));
        als.add(new Student("Chen", 3, "991-878-4944", "308 Blair"));
        als.add(new Student("Fox", 3, "884-232-5341", "11 Dickinson"));
        als.add(new Student("Kanaga", 3, "898-122-9643", "22 Brown"));
            
        // Student[] sa = new Student[4];
        Student[] sa = als.toArray(new Student[als.size()]);

        System.out.format("## Sort by section\n");
        MergeSortComparator.sort(sa, Student.BY_SECTION);
        for (int i = 0; i < sa.length; i++) sa[i].printStudent();
        System.out.format("## Sort by name\n");
        MergeSortComparator.sort(sa, Student.BY_NAME);
        for (int i = 0; i < sa.length; i++) sa[i].printStudent();

        System.out.format("## Sort by section, BU\n");
        MergeSortBUComparator.sort(sa, Student.BY_SECTION);
        for (int i = 0; i < sa.length; i++) sa[i].printStudent();
        System.out.format("## Sort by name, BU\n");
        MergeSortBUComparator.sort(sa, Student.BY_NAME);
        for (int i = 0; i < sa.length; i++) sa[i].printStudent();

        System.out.format("## Sort by section, ShellSort\n");
        ShellSortComparator.sort(sa, Student.BY_SECTION);
        for (int i = 0; i < sa.length; i++) sa[i].printStudent();
        System.out.format("## Sort by name, ShellSort\n");
        ShellSortComparator.sort(sa, Student.BY_NAME);
        for (int i = 0; i < sa.length; i++) sa[i].printStudent();

        return;
    }
}
