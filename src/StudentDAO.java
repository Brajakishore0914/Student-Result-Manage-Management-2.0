// ⚠️ Use your actual package name if you have one, or remove this line if none

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // Establish database connection
    Connection conn = DB.getConnection();

    // Add a new student record
    public void addStudent(String name, String roll, int s1, int s2, int s3) {
        try {
            int total = s1 + s2 + s3;
            float percentage = total / 3.0f;
            String grade;

            // Grade calculation
            if (percentage >= 90) {
                grade = "O";
            } else if (percentage >= 80) {
                grade = "E";
            } else {
                grade = "A";
            }

            // Insert record into the database
            String sql = "INSERT INTO students (name, roll_no, subject1, subject2, subject3, total, percentage, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, roll);
            ps.setInt(3, s1);
            ps.setInt(4, s2);
            ps.setInt(5, s3);
            ps.setInt(6, total);
            ps.setFloat(7, percentage);
            ps.setString(8, grade);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve list of students (with optional search and sorting)
    public List<Student> getStudents(String search, String sort) {
        List<Student> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM students";

            // Add search filter
            if (search != null && !search.trim().isEmpty()) {
                sql += " WHERE name LIKE '%" + search + "%' OR roll_no LIKE '%" + search + "%'";
            }

            // Add sorting
            if (sort != null && !sort.trim().isEmpty()) {
                sql += " ORDER BY " + sort + " ASC";
            }

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getInt("subject1"),
                        rs.getInt("subject2"),
                        rs.getInt("subject3"),
                        rs.getInt("total"),
                        rs.getFloat("percentage"),
                        rs.getString("grade")
                ));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Delete a student by ID
    public void deleteStudent(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
