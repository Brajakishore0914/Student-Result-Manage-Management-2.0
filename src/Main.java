import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Main extends JFrame {
    StudentDAO dao = new StudentDAO();
    JTable table;
    DefaultTableModel model;
    JTextField searchField;

    public Main() {
        setTitle("Student Result Management System 2.0");
        setSize(850, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");
        JComboBox<String> sortBox = new JComboBox<>(new String[]{"", "roll_no", "name", "total", "percentage", "grade"});

        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(new JLabel("Sort By:"));
        topPanel.add(sortBox);
        topPanel.add(searchBtn);
        topPanel.add(addBtn);
        topPanel.add(delBtn);
        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Roll No", "S1", "S2", "S3", "Total", "Percent", "Grade"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadTable("", "");

        searchBtn.addActionListener(e -> loadTable(searchField.getText(), (String) sortBox.getSelectedItem()));

        addBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter Student Name:");
            String roll = JOptionPane.showInputDialog("Enter Roll No:");
            int s1 = Integer.parseInt(JOptionPane.showInputDialog("Enter Subject 1 Marks:"));
            int s2 = Integer.parseInt(JOptionPane.showInputDialog("Enter Subject 2 Marks:"));
            int s3 = Integer.parseInt(JOptionPane.showInputDialog("Enter Subject 3 Marks:"));
            dao.addStudent(name, roll, s1, s2, s3);
            loadTable("", "");
        });

        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) model.getValueAt(row, 0);
                dao.deleteStudent(id);
                loadTable("", "");
            }
        });

        setVisible(true);
    }

    void loadTable(String search, String sort) {
        model.setRowCount(0);
        List<Student> list = dao.getStudents(search, sort);
        for (Student s : list) {
            model.addRow(new Object[]{
                    s.getId(),
                    s.getName(),
                    s.getRoll_no(),
                    s.getSubject1(),
                    s.getSubject2(),
                    s.getSubject3(),
                    s.getTotal(),
                    s.getPercentage(),
                    s.getGrade()
            });
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
