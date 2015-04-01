import java.util.Comparator;

public class Student {
    public static final BySection BY_SECTION = new BySection();
    public static final ByName BY_NAME = new ByName();
    private String name;
    private int section;

    public Student(String n, int s) {
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
        Student a0 = new Student("Andrews", 4);
        Student a1 = new Student("Battle", 3);
        Student[] sa = new Student[2];
        sa[0] = a0;
        sa[1] = a1;

        MergeSortComparator.sort(sa, Student.BY_SECTION);
        for (int i = 0; i < sa.length; i++) sa[i].printStudent();
        MergeSortComparator.sort(sa, Student.BY_NAME);
        for (int i = 0; i < sa.length; i++) sa[i].printStudent();

        return;
    }
}
