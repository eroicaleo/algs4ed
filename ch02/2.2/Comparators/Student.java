import java.util.Comparator;

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
        Student a0 = new Student("Andrews", 4, "664-480-0023", "097 Little");
        Student a1 = new Student("Battle", 3, "874-088-1212", "121 Whitman");
        Student a2 = new Student("Furia", 1, "766-093-9873", "101 Brown");
        Student a3 = new Student("Gazsi", 2, "766-093-9873", "101 Brown");
        Student[] sa = new Student[4];
        sa[0] = a0;
        sa[1] = a1;
        sa[2] = a2;
        sa[3] = a3;

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

        return;
    }
}
