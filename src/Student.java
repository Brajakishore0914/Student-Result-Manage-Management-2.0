public class Student {
    private int id;
    private String name;
    private String roll_no;
    private int subject1;
    private int subject2;
    private int subject3;
    private int total;
    private float percentage;
    private String grade;

    public Student(int id, String name, String roll_no, int subject1, int subject2, int subject3, int total, float percentage, String grade) {
        this.id = id;
        this.name = name;
        this.roll_no = roll_no;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.total = total;
        this.percentage = percentage;
        this.grade = grade;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getRoll_no() { return roll_no; }
    public int getSubject1() { return subject1; }
    public int getSubject2() { return subject2; }
    public int getSubject3() { return subject3; }
    public int getTotal() { return total; }
    public float getPercentage() { return percentage; }
    public String getGrade() { return grade; }
}
