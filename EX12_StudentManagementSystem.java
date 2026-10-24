import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class StudentManagementSystem extends JFrame {
    private final JTextField rollField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JTextField attendanceField = new JTextField();
    private final JTextField javaField = new JTextField();
    private final JTextField pythonField = new JTextField();
    private final JTextField databaseField = new JTextField();
    private final JTextField searchField = new JTextField();

    private final JComboBox<String> departmentBox = new JComboBox<>(new String[]{
        "CSE", "IT", "ECE", "EEE", "Mechanical", "Civil", "AI & DS"
    });
    private final JComboBox<String> yearBox = new JComboBox<>(new String[]{
        "1st Year", "2nd Year", "3rd Year", "4th Year"
    });

    private final DefaultTableModel tableModel = new DefaultTableModel(
        new String[]{"Roll No", "Name", "Department", "Year", "Attendance",
                     "Java", "Python", "Database", "Total", "Average", "Grade", "Result"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable studentTable = new JTable(tableModel);
    private final JLabel totalStudentsLabel = dashboardLabel("Total Students: 0");
    private final JLabel averageAttendanceLabel = dashboardLabel("Average Attendance: 0.0%");
    private final JLabel passedStudentsLabel = dashboardLabel("Passed: 0");
    private final JLabel failedStudentsLabel = dashboardLabel("Failed: 0");

    public StudentManagementSystem() {
        setTitle("Smart Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1050, 620));
        setSize(1250, 720);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel title = new JLabel("SMART STUDENT MANAGEMENT SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        root.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        addField(formPanel, "Roll Number:", rollField);
        addField(formPanel, "Student Name:", nameField);
        addField(formPanel, "Department:", departmentBox);
        addField(formPanel, "Year:", yearBox);
        addField(formPanel, "Attendance (%):", attendanceField);
        addField(formPanel, "Java Marks:", javaField);
        addField(formPanel, "Python Marks:", pythonField);
        addField(formPanel, "Database Marks:", databaseField);

        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton clearButton = new JButton("Clear");
        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show All");

        JPanel buttons = new JPanel(new GridLayout(3, 2, 7, 7));
        buttons.add(addButton);
        buttons.add(updateButton);
        buttons.add(deleteButton);
        buttons.add(clearButton);
        buttons.add(searchButton);
        buttons.add(showAllButton);

        JPanel searchPanel = new JPanel(new BorderLayout(6, 6));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search by Roll Number"));
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(formPanel);
        left.add(Box.createVerticalStrut(10));
        left.add(buttons);
        left.add(Box.createVerticalStrut(10));
        left.add(searchPanel);
        left.setPreferredSize(new Dimension(330, 0));
        root.add(left, BorderLayout.WEST);

        studentTable.setRowHeight(24);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.setAutoCreateRowSorter(true);
        JScrollPane tableScroll = new JScrollPane(studentTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Student Records"));
        root.add(tableScroll, BorderLayout.CENTER);

        JPanel dashboard = new JPanel(new GridLayout(1, 4, 8, 8));
        dashboard.setBorder(BorderFactory.createTitledBorder("Student Dashboard"));
        dashboard.add(totalStudentsLabel);
        dashboard.add(averageAttendanceLabel);
        dashboard.add(passedStudentsLabel);
        dashboard.add(failedStudentsLabel);
        root.add(dashboard, BorderLayout.SOUTH);

        setContentPane(root);

        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearFields());
        searchButton.addActionListener(e -> searchStudent());
        showAllButton.addActionListener(e -> {
            searchField.setText("");
            studentTable.clearSelection();
            JOptionPane.showMessageDialog(this, "All student records are displayed.");
        });

        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int viewRow = studentTable.getSelectedRow();
                if (viewRow >= 0) {
                    int row = studentTable.convertRowIndexToModel(viewRow);
                    rollField.setText(valueAt(row, 0));
                    nameField.setText(valueAt(row, 1));
                    departmentBox.setSelectedItem(valueAt(row, 2));
                    yearBox.setSelectedItem(valueAt(row, 3));
                    attendanceField.setText(valueAt(row, 4).replace("%", ""));
                    javaField.setText(valueAt(row, 5));
                    pythonField.setText(valueAt(row, 6));
                    databaseField.setText(valueAt(row, 7));
                }
            }
        });

        setVisible(true);
    }

    private static void addField(JPanel panel, String label, Component field) {
        panel.add(new JLabel(label));
        panel.add(field);
    }

    private static JLabel dashboardLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEtchedBorder());
        return label;
    }

    private String valueAt(int row, int column) {
        return String.valueOf(tableModel.getValueAt(row, column));
    }

    private boolean validateFields() {
        if (rollField.getText().trim().isEmpty()
                || nameField.getText().trim().isEmpty()
                || attendanceField.getText().trim().isEmpty()
                || javaField.getText().trim().isEmpty()
                || pythonField.getText().trim().isEmpty()
                || databaseField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return false;
        }

        try {
            double attendance = Double.parseDouble(attendanceField.getText().trim());
            int java = Integer.parseInt(javaField.getText().trim());
            int python = Integer.parseInt(pythonField.getText().trim());
            int database = Integer.parseInt(databaseField.getText().trim());

            if (attendance < 0 || attendance > 100) {
                JOptionPane.showMessageDialog(this, "Attendance must be between 0 and 100.");
                return false;
            }
            if (!validMark(java) || !validMark(python) || !validMark(database)) {
                JOptionPane.showMessageDialog(this, "Each subject mark must be between 0 and 100.");
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Attendance must be a number and marks must be whole numbers.");
            return false;
        }
        return true;
    }

    private boolean validMark(int mark) {
        return mark >= 0 && mark <= 100;
    }

    private String calculateGrade(double average) {
        if (average >= 90) return "A+";
        if (average >= 80) return "A";
        if (average >= 70) return "B";
        if (average >= 60) return "C";
        if (average >= 50) return "D";
        if (average >= 40) return "E";
        return "F";
    }

    private Object[] buildStudentRow() {
        String roll = rollField.getText().trim();
        String name = nameField.getText().trim();
        String department = String.valueOf(departmentBox.getSelectedItem());
        String year = String.valueOf(yearBox.getSelectedItem());
        double attendance = Double.parseDouble(attendanceField.getText().trim());
        int java = Integer.parseInt(javaField.getText().trim());
        int python = Integer.parseInt(pythonField.getText().trim());
        int database = Integer.parseInt(databaseField.getText().trim());

        int total = java + python + database;
        double average = total / 3.0;
        String grade = calculateGrade(average);
        String result = (java >= 40 && python >= 40 && database >= 40) ? "PASS" : "FAIL";

        return new Object[]{
            roll, name, department, year,
            String.format(Locale.US, "%.1f%%", attendance),
            java, python, database, total,
            String.format(Locale.US, "%.2f", average), grade, result
        };
    }

    private void addStudent() {
        if (!validateFields()) return;
        String roll = rollField.getText().trim();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (valueAt(i, 0).equalsIgnoreCase(roll)) {
                JOptionPane.showMessageDialog(this, "That roll number already exists.");
                return;
            }
        }
        tableModel.addRow(buildStudentRow());
        updateDashboard();
        clearFields();
        JOptionPane.showMessageDialog(this, "Student added successfully.");
    }

    private void updateStudent() {
        int viewRow = studentTable.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this, "Select a student row in the table first.");
            return;
        }
        if (!validateFields()) return;

        String newRoll = rollField.getText().trim();
        int row = studentTable.convertRowIndexToModel(viewRow);
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (i != row && valueAt(i, 0).equalsIgnoreCase(newRoll)) {
                JOptionPane.showMessageDialog(this, "Another student already uses that roll number.");
                return;
            }
        }

        Object[] updated = buildStudentRow();
        for (int column = 0; column < updated.length; column++) {
            tableModel.setValueAt(updated[column], row, column);
        }
        updateDashboard();
        clearFields();
        JOptionPane.showMessageDialog(this, "Student updated successfully.");
    }

    private void deleteStudent() {
        int viewRow = studentTable.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this, "Select a student row in the table first.");
            return;
        }
        int choice = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this student?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            tableModel.removeRow(studentTable.convertRowIndexToModel(viewRow));
            updateDashboard();
            clearFields();
            JOptionPane.showMessageDialog(this, "Student deleted successfully.");
        }
    }

    private void searchStudent() {
        String roll = searchField.getText().trim();
        if (roll.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a roll number in the search box.");
            return;
        }
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (valueAt(i, 0).equalsIgnoreCase(roll)) {
                int viewRow = studentTable.convertRowIndexToView(i);
                studentTable.setRowSelectionInterval(viewRow, viewRow);
                studentTable.scrollRectToVisible(studentTable.getCellRect(viewRow, 0, true));
                JOptionPane.showMessageDialog(this,
                    "Student Found\n\n"
                    + "Name: " + valueAt(i, 1) + "\n"
                    + "Department: " + valueAt(i, 2) + "\n"
                    + "Year: " + valueAt(i, 3) + "\n"
                    + "Attendance: " + valueAt(i, 4) + "\n"
                    + "Average: " + valueAt(i, 9) + "\n"
                    + "Grade: " + valueAt(i, 10) + "\n"
                    + "Result: " + valueAt(i, 11));
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "No student found with that roll number.");
    }

    private void updateDashboard() {
        int count = tableModel.getRowCount();
        int passed = 0;
        double attendanceTotal = 0;

        for (int i = 0; i < count; i++) {
            attendanceTotal += Double.parseDouble(valueAt(i, 4).replace("%", ""));
            if ("PASS".equals(valueAt(i, 11))) passed++;
        }

        double averageAttendance = count == 0 ? 0 : attendanceTotal / count;
        totalStudentsLabel.setText("Total Students: " + count);
        averageAttendanceLabel.setText(String.format(
            Locale.US, "Average Attendance: %.1f%%", averageAttendance));
        passedStudentsLabel.setText("Passed: " + passed);
        failedStudentsLabel.setText("Failed: " + (count - passed));
    }

    private void clearFields() {
        rollField.setText("");
        nameField.setText("");
        attendanceField.setText("");
        javaField.setText("");
        pythonField.setText("");
        databaseField.setText("");
        departmentBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
        studentTable.clearSelection();
        rollField.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementSystem::new);
    }
}
