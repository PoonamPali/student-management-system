import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Student Class
class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

// Student Management System Class
class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String rollNumber) {
        students.removeIf(student -> student.getRollNumber().equals(rollNumber));
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }
}

// GUI Implementation Class
public class StudentManagementGUI extends JFrame implements ActionListener {
    private StudentManagementSystem sms;
    private JTextField nameField, rollNumberField, gradeField;
    private JTextArea displayArea;
    private JButton addButton, removeButton, searchButton, displayButton;

    public StudentManagementGUI() {
        sms = new StudentManagementSystem();

        setTitle("Student Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Roll Number:"));
        rollNumberField = new JTextField();
        add(rollNumberField);

        add(new JLabel("Grade:"));
        gradeField = new JTextField();
        add(gradeField);

        addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        add(addButton);

        removeButton = new JButton("Remove Student");
        removeButton.addActionListener(this);
        add(removeButton);

        searchButton = new JButton("Search Student");
        searchButton.addActionListener(this);
        add(searchButton);

        displayButton = new JButton("Display All Students");
        displayButton.addActionListener(this);
        add(displayButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String rollNumber = rollNumberField.getText();
        String grade = gradeField.getText();

        if (e.getSource() == addButton) {
            if (!name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty()) {
                Student student = new Student(name, rollNumber, grade);
                sms.addStudent(student);
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                nameField.setText("");
                rollNumberField.setText("");
                gradeField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        } else if (e.getSource() == removeButton) {
            if (!rollNumber.isEmpty()) {
                sms.removeStudent(rollNumber);
                JOptionPane.showMessageDialog(this, "Student removed successfully!");
                rollNumberField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a roll number.");
            }
        } else if (e.getSource() == searchButton) {
            if (!rollNumber.isEmpty()) {
                Student student = sms.searchStudent(rollNumber);
                if (student != null) {
                    displayArea.setText(student.toString());
                } else {
                    displayArea.setText("Student not found.");
                }
                rollNumberField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a roll number.");
            }
        } else if (e.getSource() == displayButton) {
            StringBuilder sb = new StringBuilder();
            for (Student student : sms.getAllStudents()) {
                sb.append(student.toString()).append("\n");
            }
            displayArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        new StudentManagementGUI();
    }
}
