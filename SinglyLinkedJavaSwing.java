package SinglyLinkedJava;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Student {
    String studNo;
    String studName;
    String courseYear;
    float gwa;
    Student next;

    public Student(String studNo, String studName, String courseYear, float gwa) {
        this.studNo = studNo;
        this.studName = studName;
        this.courseYear = courseYear;
        this.gwa = gwa;
        this.next = null;
    }
}

public class SinglyLinkedJavaSwing {
    private Student START = null;
    private DefaultTableModel model;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SinglyLinkedJavaSwing srm = new SinglyLinkedJavaSwing();
            srm.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Student Record Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        Color backgroundColor = new Color(0x18090E);
        frame.getContentPane().setBackground(backgroundColor);

        ImageIcon imageIcon = new ImageIcon("C:/Users/Administrator/Documents/NetBeansProjects/Activities/src/main/java/SinglyLinkedJava/manifestingpasado.png");

        JLabel imageLabel = new JLabel(imageIcon);
        frame.add(imageLabel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.addColumn("Student No.");
        model.addColumn("Student Name");
        model.addColumn("Course & Year");
        model.addColumn("GWA");

        JTable table = new JTable(model);
        table.setRowHeight(30);  
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(""));

        JPanel buttonPanel = new JPanel(new GridLayout(14, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(101, 10, 10, 10));
        buttonPanel.setBackground(backgroundColor);

        JButton createButton = new JButton("Create Record");
        JButton addButton = new JButton("Add at Beginning");
        JButton addEndButton = new JButton("Add at End");
        JButton displayButton = new JButton("Display Records");
        JButton insertBeforeButton = new JButton("Insert Before");
        JButton insertAfterButton = new JButton("Insert After");
        JButton deleteStartButton = new JButton("Delete First");
        JButton deleteEndButton = new JButton("Delete Last");
        JButton deleteByValueButton = new JButton("Delete by Number");
        JButton exitButton = new JButton("Exit");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createList();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAtBeginning();
            }
        });
        addEndButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAtEnd();
            }
        });
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRecords();
            }
        });
        insertBeforeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studNo = JOptionPane.showInputDialog("Enter student number to insert before:");
                insertBeforeNode(studNo);
            }
        });
        insertAfterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studNo = JOptionPane.showInputDialog("Enter student number to insert after:");
                insertAfterNode(studNo);
            }
        });
        deleteStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNodeAtStart();
            }
        });
        deleteEndButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNodeAtEnd();
            }
        });
        deleteByValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studNo = JOptionPane.showInputDialog("Enter student number to delete:");
                deleteByValue(studNo);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JButton[] buttons = {
            createButton, addButton, addEndButton, displayButton, insertBeforeButton,
            insertAfterButton, deleteStartButton, deleteEndButton, deleteByValueButton, exitButton
        };

        for (JButton button : buttons) {
            button.setBackground(new Color(0x471EC2));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            buttonPanel.add(button);
            addHoverEffect(button);
        }

        scrollPane.setBackground(backgroundColor);
        scrollPane.setForeground(Color.WHITE);
        scrollPane.getViewport().setBackground(backgroundColor);
        table.setForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(backgroundColor);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void addHoverEffect(JButton button) {
        Color originalColor = button.getBackground();
        Color hoverColor = originalColor.darker();
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }

    private void setCustomOptionPaneColors() {
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.foreground", Color.WHITE);
        UIManager.put("Button.background", new Color(0x471EC2));
        UIManager.put("Button.foreground", Color.WHITE);
    }

    private void createList() {
        setCustomOptionPaneColors();

        boolean addMore = true;

        while (addMore) {
            JTextField studNoField = new JTextField();
            JTextField studNameField = new JTextField();
            JTextField courseYearField = new JTextField();
            JTextField gwaField = new JTextField();

            JLabel studNoLabel = new JLabel("Student No:");
            studNoLabel.setForeground(Color.WHITE);
            JLabel studNameLabel = new JLabel("Student Name:");
            studNameLabel.setForeground(Color.WHITE);
            JLabel courseYearLabel = new JLabel("Course & Year:");
            courseYearLabel.setForeground(Color.WHITE);
            JLabel gwaLabel = new JLabel("GWA:");
            gwaLabel.setForeground(Color.WHITE);

            JPanel inputPanel = new JPanel(new GridLayout(0, 1));
            inputPanel.setBackground(Color.BLACK);
            inputPanel.add(studNoLabel);
            inputPanel.add(studNoField);
            inputPanel.add(studNameLabel);
            inputPanel.add(studNameField);
            inputPanel.add(courseYearLabel);
            inputPanel.add(courseYearField);
            inputPanel.add(gwaLabel);
            inputPanel.add(gwaField);

            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Create New Student Record", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String studNo = studNoField.getText();
                String studName = studNameField.getText();
                String courseYear = courseYearField.getText();
                float gwa = Float.parseFloat(gwaField.getText());

                Student newNode = new Student(studNo, studName, courseYear, gwa);
                if (START == null) {
                    START = newNode;
                } else {
                    Student temp = START;
                    while (temp.next != null) {
                        temp = temp.next;
                    }
                    temp.next = newNode;
                }
                JOptionPane.showMessageDialog(null, "Student record created successfully!");
            }

            int moreResult = JOptionPane.showConfirmDialog(null, "Would you like to add more student records?", "Add More?", JOptionPane.YES_NO_OPTION);
            if (moreResult != JOptionPane.YES_OPTION) {
                addMore = false;
            }
        }
    }

    private void addAtBeginning() {
        JTextField studNoField = new JTextField();
        JTextField studNameField = new JTextField();
        JTextField courseYearField = new JTextField();
        JTextField gwaField = new JTextField();

        JLabel studNoLabel = new JLabel("Student No:");
        studNoLabel.setForeground(Color.WHITE);
        JLabel studNameLabel = new JLabel("Student Name:");
        studNameLabel.setForeground(Color.WHITE);
        JLabel courseYearLabel = new JLabel("Course & Year:");
        courseYearLabel.setForeground(Color.WHITE);
        JLabel gwaLabel = new JLabel("GWA:");
        gwaLabel.setForeground(Color.WHITE);

        JPanel inputPanel = new JPanel(new GridLayout(0, 1));
        inputPanel.setBackground(Color.BLACK);
        inputPanel.add(studNoLabel);
        inputPanel.add(studNoField);
        inputPanel.add(studNameLabel);
        inputPanel.add(studNameField);
        inputPanel.add(courseYearLabel);
        inputPanel.add(courseYearField);
        inputPanel.add(gwaLabel);
        inputPanel.add(gwaField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Add Student Record at Beginning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String studNo = studNoField.getText();
            String studName = studNameField.getText();
            String courseYear = courseYearField.getText();
            float gwa = Float.parseFloat(gwaField.getText());

            Student newNode = new Student(studNo, studName, courseYear, gwa);
            if (START == null) {
                START = newNode;
            } else {
                newNode.next = START;
                START = newNode;
            }
            JOptionPane.showMessageDialog(null, "Student record added at the beginning successfully!");
        }
    }

    private void addAtEnd() {
        JTextField studNoField = new JTextField();
        JTextField studNameField = new JTextField();
        JTextField courseYearField = new JTextField();
        JTextField gwaField = new JTextField();

        JLabel studNoLabel = new JLabel("Student No:");
        studNoLabel.setForeground(Color.WHITE);
        JLabel studNameLabel = new JLabel("Student Name:");
        studNameLabel.setForeground(Color.WHITE);
        JLabel courseYearLabel = new JLabel("Course & Year:");
        courseYearLabel.setForeground(Color.WHITE);
        JLabel gwaLabel = new JLabel("GWA:");
        gwaLabel.setForeground(Color.WHITE);

        JPanel inputPanel = new JPanel(new GridLayout(0, 1));
        inputPanel.setBackground(Color.BLACK);
        inputPanel.add(studNoLabel);
        inputPanel.add(studNoField);
        inputPanel.add(studNameLabel);
        inputPanel.add(studNameField);
        inputPanel.add(courseYearLabel);
        inputPanel.add(courseYearField);
        inputPanel.add(gwaLabel);
        inputPanel.add(gwaField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Add Student Record at End", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String studNo = studNoField.getText();
            String studName = studNameField.getText();
            String courseYear = courseYearField.getText();
            float gwa = Float.parseFloat(gwaField.getText());

            Student newNode = new Student(studNo, studName, courseYear, gwa);
            if (START == null) {
                START = newNode;
            } else {
                Student temp = START;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newNode;
            }
            JOptionPane.showMessageDialog(null, "Student record added at the end successfully!");
        }
    }

    private void insertBeforeNode(String studNo) {
    if (START == null) {
        JOptionPane.showMessageDialog(null, "No records found.");
        return;
    }

    Student current = START;
    boolean found = false;

    while (current != null) {
        if (current.studNo.equals(studNo)) {
            found = true;
            break;
        }
        current = current.next;
    }

    if (!found) {
        JOptionPane.showMessageDialog(null, "Student record not found.");
        return;
    }

    JTextField studNoField = new JTextField();
    JTextField studNameField = new JTextField();
    JTextField courseYearField = new JTextField();
    JTextField gwaField = new JTextField();

    JLabel studNoLabel = new JLabel("Student No:");
    studNoLabel.setForeground(Color.WHITE);
    JLabel studNameLabel = new JLabel("Student Name:");
    studNameLabel.setForeground(Color.WHITE);
    JLabel courseYearLabel = new JLabel("Course & Year:");
    courseYearLabel.setForeground(Color.WHITE);
    JLabel gwaLabel = new JLabel("GWA:");
    gwaLabel.setForeground(Color.WHITE);

    JPanel inputPanel = new JPanel(new GridLayout(0, 1));
    inputPanel.setBackground(Color.BLACK);
    inputPanel.add(studNoLabel);
    inputPanel.add(studNoField);
    inputPanel.add(studNameLabel);
    inputPanel.add(studNameField);
    inputPanel.add(courseYearLabel);
    inputPanel.add(courseYearField);
    inputPanel.add(gwaLabel);
    inputPanel.add(gwaField);

    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Insert Student Record Before Node", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        String newStudNo = studNoField.getText();
        String newStudName = studNameField.getText();
        String newCourseYear = courseYearField.getText();
        float newGwa = Float.parseFloat(gwaField.getText());

        Student newNode = new Student(newStudNo, newStudName, newCourseYear, newGwa);

        if (START.studNo.equals(studNo)) {
            newNode.next = START;
            START = newNode;
        } else {
            Student previous = null;
            current = START;
            while (current != null && !current.studNo.equals(studNo)) {
                previous = current;
                current = current.next;
            }
            if (current != null) {
                newNode.next = current;
                previous.next = newNode;
            }
        }
        JOptionPane.showMessageDialog(null, "Student record inserted before the node successfully!");
    }
}

private void insertAfterNode(String studNo) {
    if (START == null) {
        JOptionPane.showMessageDialog(null, "No records found.");
        return;
    }

    Student current = START;
    boolean found = false;

    while (current != null) {
        if (current.studNo.equals(studNo)) {
            found = true;
            break;
        }
        current = current.next;
    }

    if (!found) {
        JOptionPane.showMessageDialog(null, "Student record not found.");
        return;
    }

    JTextField studNoField = new JTextField();
    JTextField studNameField = new JTextField();
    JTextField courseYearField = new JTextField();
    JTextField gwaField = new JTextField();

    JLabel studNoLabel = new JLabel("Student No:");
    studNoLabel.setForeground(Color.WHITE);
    JLabel studNameLabel = new JLabel("Student Name:");
    studNameLabel.setForeground(Color.WHITE);
    JLabel courseYearLabel = new JLabel("Course & Year:");
    courseYearLabel.setForeground(Color.WHITE);
    JLabel gwaLabel = new JLabel("GWA:");
    gwaLabel.setForeground(Color.WHITE);

    JPanel inputPanel = new JPanel(new GridLayout(0, 1));
    inputPanel.setBackground(Color.BLACK);
    inputPanel.add(studNoLabel);
    inputPanel.add(studNoField);
    inputPanel.add(studNameLabel);
    inputPanel.add(studNameField);
    inputPanel.add(courseYearLabel);
    inputPanel.add(courseYearField);
    inputPanel.add(gwaLabel);
    inputPanel.add(gwaField);

    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Insert Student Record After Node", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        String newStudNo = studNoField.getText();
        String newStudName = studNameField.getText();
        String newCourseYear = courseYearField.getText();
        float newGwa = Float.parseFloat(gwaField.getText());

        Student newNode = new Student(newStudNo, newStudName, newCourseYear, newGwa);

        current = START;
        while (current != null && !current.studNo.equals(studNo)) {
            current = current.next;
        }
        if (current != null) {
            newNode.next = current.next;
            current.next = newNode;
            JOptionPane.showMessageDialog(null, "Student record inserted after the node successfully!");
        }
    }
}


    private void deleteNodeAtStart() {
        if (START == null) {
            JOptionPane.showMessageDialog(null, "No records found.");
            return;
        }
        START = START.next;
        JOptionPane.showMessageDialog(null, "Student record deleted from the start successfully!");
    }

    private void deleteNodeAtEnd() {
        if (START == null) {
            JOptionPane.showMessageDialog(null, "No records found.");
            return;
        }

        if (START.next == null) {
            START = null;
        } else {
            Student current = START;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        JOptionPane.showMessageDialog(null, "Student record deleted from the end successfully!");
    }

    private void deleteByValue(String studNo) {
        if (START == null) {
            JOptionPane.showMessageDialog(null, "No records found.");
            return;
        }

        if (START.studNo.equals(studNo)) {
            START = START.next;
        } else {
            Student current = START;
            Student previous = null;
            while (current != null && !current.studNo.equals(studNo)) {
                previous = current;
                current = current.next;
            }
            if (current != null) {
                previous.next = current.next;
            } else {
                JOptionPane.showMessageDialog(null, "Student record not found.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Student record deleted successfully!");
    }

    private void displayRecords() {
        model.setRowCount(0); 

        if (START == null) {
            JOptionPane.showMessageDialog(null, "No records found.");
            return;
        }

        Student current = START;
        while (current != null) {
            model.addRow(new Object[]{current.studNo, current.studName, current.courseYear, current.gwa});
            current = current.next;
        }
    }


}
