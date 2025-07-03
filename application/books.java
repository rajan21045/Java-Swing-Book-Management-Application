import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class books implements ActionListener {

    // Declare UI components (labels, text fields, button)
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JTextField t1, t2, t3, t4, t5, t6;
    JButton b1;

    // Constructor: Setup JFrame and components
    books() {

        JFrame f = new JFrame();

        f.setTitle("Book Management System");
        f.setSize(600, 500);
        f.setLayout(null);  // Disable layout manager for absolute positioning

        // Heading label
        l7 = new JLabel("Add Books");
        l7.setBounds(270, 10, 200, 30);
        l7.setFont(new Font("Poppins", Font.BOLD, 20));

        // Book Name label and text field
        l1 = new JLabel("Book Name:");
        l1.setBounds(50, 60, 100, 30);
        t1 = new JTextField();
        t1.setBounds(200, 60, 300, 30);
        l1.setFont(new Font("Roboto", Font.BOLD, 14));

        // Author label and text field
        l2 = new JLabel("Author:");
        l2.setBounds(50, 100, 100, 30);
        t2 = new JTextField();
        t2.setBounds(200, 100, 300, 30);
        l2.setFont(new Font("Roboto", Font.BOLD, 14));
 
        // Genre label and text field
        l3 = new JLabel("Genre:");
        l3.setBounds(50, 140, 100, 30);
        t3 = new JTextField();
        t3.setBounds(200, 140, 300, 30);
        l3.setFont(new Font("Roboto", Font.BOLD, 14));

        // Publisher label and text field
        l4 = new JLabel("Publisher:");
        l4.setBounds(50, 180, 100, 30);
        t4 = new JTextField();
        t4.setBounds(200, 180, 300, 30);
        l4.setFont(new Font("Roboto", Font.BOLD, 14));

        // Language label and text field
        l5 = new JLabel("Language:");
        l5.setBounds(50, 220, 100, 30);
        t5 = new JTextField();
        t5.setBounds(200, 220, 300, 30);
        l5.setFont(new Font("Roboto", Font.BOLD, 14));

        // Price label and text field
        l6 = new JLabel("Price:");
        l6.setBounds(50, 260, 100, 30);
        t6 = new JTextField();
        t6.setBounds(200, 260, 300, 30);
        l6.setFont(new Font("Roboto", Font.BOLD, 14));

        // Submit button
        b1 = new JButton("Submit");
        b1.setBounds(250, 320, 100, 40);
        b1.setFont(new Font("Arial", Font.BOLD, 16));

        // Add all components to the JFrame
        f.add(l7);
        f.add(l1); f.add(t1);
        f.add(l2); f.add(t2);
        f.add(l3); f.add(t3);
        f.add(l4); f.add(t4);
        f.add(l5); f.add(t5);
        f.add(l6); f.add(t6);
        f.add(b1);

        // JFrame settings
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);

        // Add action listener to submit button
        b1.addActionListener(this);
    }

    // Handle button click event
    public void actionPerformed(ActionEvent ae) {
        // Get input values from text fields
        String name = t1.getText();
        String author = t2.getText();
        String genre = t3.getText();
        String publisher = t4.getText();
        String language = t5.getText();
        float price = 0;

        // Check if any field is empty
        if (name.isEmpty() || author.isEmpty() || genre.isEmpty() || publisher.isEmpty() || language.isEmpty() || t6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate price input
        try {
            price = Float.parseFloat(t6.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price entered. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Connect to MySQL database and insert book data
        try {

            String url = "jdbc:mysql://localhost:3306/books_db";  // Database URL
            String username = "root";                             // DB username
            String password = "*rajan12345#";                     // DB password

            Class.forName("com.mysql.cj.jdbc.Driver");           // Load JDBC driver
            Connection con = DriverManager.getConnection(url, username, password);
            
            String query = "INSERT INTO books (name, author, genre, publisher, language, price) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, author);
            ps.setString(3, genre);
            ps.setString(4, publisher);
            ps.setString(5, language);
            ps.setFloat(6, price);

            ps.executeUpdate();  // Execute insertion

            // Show success message
            JOptionPane.showMessageDialog(null, "Book successfully added to the database!");

            // Clear input fields after successful insert
            t1.setText(""); t2.setText(""); t3.setText("");
            t4.setText(""); t5.setText(""); t6.setText("");

            // Close resources
            ps.close();
            con.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();  // Print detailed error stack trace
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method: Entry point of the program
    public static void main(String[] args) {
        new books();  // Create instance of the class, which displays the GUI
    }
}
