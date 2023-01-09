import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InterQuery {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTable table;
    private JTable table_1;
    private JTable table_2;
    private JTable table_3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterQuery window = new InterQuery ();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InterQuery() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1512, 812);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(346, 10, 804, 219);
        frame.getContentPane().add(scrollPane);

        table_3 = new JTable();
        table_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane.setViewportView(table_3);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 16));
        textField.setBounds(116, 246, 785, 67);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        textField_1.setBounds(116, 412, 785, 67);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        textField_2.setBounds(116, 587, 785, 67);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JButton btnNewButton = new JButton("Execute");
        btnNewButton.setForeground(new Color(228, 242, 245));
        btnNewButton.setBackground(new Color(66, 80, 84));

        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBounds(239, 664, 298, 79);
        frame.getContentPane().add(btnNewButton);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(911, 246, 577, 155);
        frame.getContentPane().add(scrollPane_1);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane_1.setViewportView(table);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(911, 412, 577, 155);
        frame.getContentPane().add(scrollPane_2);

        table_1 = new JTable();
        table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane_2.setViewportView(table_1);

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(911, 577, 577, 142);
        frame.getContentPane().add(scrollPane_3);

        table_2 = new JTable();
        table_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane_3.setViewportView(table_2);

        JLabel lblNewLabel_1 = new JLabel("Query 1");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1.setBounds(34, 260, 163, 36);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Query 2");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(34, 426, 163, 36);
        frame.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Query 3");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_2.setBounds(34, 601, 163, 36);
        frame.getContentPane().add(lblNewLabel_1_2);

        JLabel lblNewLabel = new JLabel("Enter Queries :");
        lblNewLabel.setBackground(new Color(240, 248, 255));
        lblNewLabel.setForeground(new Color(31, 33, 32));
        lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 21));
        lblNewLabel.setBounds(61, 193, 196, 36);
        frame.getContentPane().add(lblNewLabel);



        JLabel lblNewLabel_2 = new JLabel("Table Name : employee ");
        lblNewLabel_2.setBackground(new Color(253, 245, 230));
        lblNewLabel_2.setForeground(new Color(31, 33, 32));
        lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.BOLD, 24));
        lblNewLabel_2.setBounds(34, -13, 333, 109);
        frame.getContentPane().add(lblNewLabel_2);



        try {

            String url = "jdbc:mysql://localhost:3306/sorting";
            String uname = "root";
            String pass = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection(url, uname, pass);
            String query = "select * from employee";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            table_3 = new JTable(model);
            model.addColumn("emp_id");
            model.addColumn("fname");
            model.addColumn("lname");
            model.addColumn("age");
            model.addColumn("salary");
            scrollPane.setViewportView(table_3);
            while(rs.next()) {
                model.insertRow(0,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)});
            }

        } catch (Exception E) {
            E.printStackTrace();
        }

        JLabel lblNewLabel_3 = new JLabel("Retrieved data from the table :");
        lblNewLabel_3.setForeground(new Color(31, 33, 32));
        lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.BOLD, 19));
        lblNewLabel_3.setBounds(1170, 151, 318, 78);
        frame.getContentPane().add(lblNewLabel_3);


        class Thread1 extends Thread {
            private String s;

            public Thread1(String s) {
                this.s = s;

            }

            @Override
            public void run() {
                // String str=textField.getText();
                String[] word = s.split("\\s");
                try {

                    String url = "jdbc:mysql://localhost:3306/sorting";
                    String uname = "root";
                    String pass = "root";

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection con = DriverManager.getConnection(url, uname, pass);

                    if (word[0].toLowerCase().equals("select")) {

                        PreparedStatement st = con.prepareStatement(s);
                        try {
                            ResultSet rs = st.executeQuery();
                            if (!rs.isBeforeFirst()) {

                                JOptionPane.showMessageDialog(null, "Query No.1 :Record does not exist.....", "Alert",
                                        JOptionPane.WARNING_MESSAGE);
                            } else {
                                DefaultTableModel model = new DefaultTableModel();
                                table = new JTable(model);
                                model.addColumn("emp_id");
                                model.addColumn("fname");
                                model.addColumn("lname");
                                model.addColumn("age");
                                model.addColumn("salary");
                                scrollPane_1.setViewportView(table);
                                while(rs.next()) {
                                    model.insertRow(0,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)});
                                }
                            }
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, "Query No.1 :Error in query....", "Alert",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    if (word[0].toLowerCase().equals("insert")) {
                        Statement st = con.createStatement();
                        try {
                            int m = st.executeUpdate(s);

                            if (m == 1) {
                                JOptionPane.showMessageDialog(null, "Query No.1 :Record inserted Successfully.....");
                            } else {
                                JOptionPane.showMessageDialog(null, "Query No.1 :insertion failed.....");
                            }
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, "Query No.1 :Error in query....", "Alert",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    if (word[0].toLowerCase().equals("delete")) {

                        Statement st = con.createStatement();
                        try {
                            int m = st.executeUpdate(s);
                            if (m == 1) {
                                JOptionPane.showMessageDialog(null, "Query No.1 :Record Deleted Successfully.....");
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Query No.1 :Deletion Failed or record does not exist.....");
                            }
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, "Query No.1 :Error in query....", "Alert",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    if (word[0].toLowerCase().equals("update")) {
                        Statement st = con.createStatement();
                        try {
                            int m = st.executeUpdate(s);

                            if (m == 1) {
                                JOptionPane.showMessageDialog(null, "Query No.1 :Record Updated Successfully!!");
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Query No.1 :updation failed or record does not exist.....");
                            }
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, "Query No.1 :Error in query....", "Alert",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }

            }
        }
        class Thread2 extends Thread {
            private String s;

            public Thread2(String s) {
                this.s = s;
            }

            @Override
            public void run() {

                String[] word = s.split("\\s");
                try {

                    String url = "jdbc:mysql://localhost:3306/sorting";
                    String uname = "root";
                    String pass = "root";

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection con = DriverManager.getConnection(url, uname, pass);

                    if (word[0].toLowerCase().equals("select")) {

                        PreparedStatement st = con.prepareStatement(s);
                        try {
                            ResultSet rs = st.executeQuery();
                            if (!rs.isBeforeFirst()) {
                                final JOptionPane pane = new JOptionPane("Query No.2 :Record does not exist.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Alert");
                                d.setLocation(630, 490);
                                d.setVisible(true);
                            } else {
                                DefaultTableModel model = new DefaultTableModel();
                                table_1 = new JTable(model);
                                model.addColumn("emp_id");
                                model.addColumn("fname");
                                model.addColumn("lname");
                                model.addColumn("age");
                                model.addColumn("salary");
                                scrollPane_2.setViewportView(table_1);
                                while(rs.next()) {
                                    model.insertRow(0,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)});
                                }
                            }
                        } catch (Exception e1) {
                            final JOptionPane pane = new JOptionPane("Query No.2:  Error in query....");
                            final JDialog d = pane.createDialog((JFrame) null, "Alert");
                            d.setLocation(630, 490);
                            d.setVisible(true);
                        }
                    }
                    if (word[0].toLowerCase().equals("insert")) {
                        Statement st = con.createStatement();
                        try {
                            int m = st.executeUpdate(s);

                            if (m == 1) {
                                final JOptionPane pane = new JOptionPane(
                                        "Query No.2: Record inserted Successfully.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 490);
                                d.setVisible(true);
                            } else {
                                final JOptionPane pane = new JOptionPane("Query No.2:  insertion failed.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 490);
                                d.setVisible(true);
                            }
                        } catch (Exception e1) {
                            final JOptionPane pane = new JOptionPane("Query No.2: Error in query....");
                            final JDialog d = pane.createDialog((JFrame) null, "Alert");
                            d.setLocation(630, 490);
                            d.setVisible(true);
                        }
                    }
                    if (word[0].toLowerCase().equals("delete")) {

                        Statement st = con.createStatement();
                        try {
                            int m = st.executeUpdate(s);
                            if (m == 1) {
                                final JOptionPane pane = new JOptionPane(
                                        "Query No.2: Record Deleted Successfully.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 490);
                                d.setVisible(true);
                            } else {

                                final JOptionPane pane = new JOptionPane(
                                        "Query No.2: Deletion Failed or record does not exist.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 490);
                                d.setVisible(true);
                            }
                        } catch (Exception e1) {
                            final JOptionPane pane = new JOptionPane("Query No.2:  Error in query....");
                            final JDialog d = pane.createDialog((JFrame) null, "Alert");
                            d.setLocation(630, 490);
                            d.setVisible(true);
                        }
                    }
                    if (word[0].toLowerCase().equals("update")) {
                        Statement st = con.createStatement();
                        try {
                            int m = st.executeUpdate(s);

                            if (m == 1) {
                                final JOptionPane pane = new JOptionPane("Query No.2:  Record Updated Successfully!!");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 490);
                                d.setVisible(true);
                            } else {
                                final JOptionPane pane = new JOptionPane(
                                        "Query No.2: updation failed or record does not exist.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 490);
                                d.setVisible(true);
                            }
                        } catch (Exception e1) {
                            final JOptionPane pane = new JOptionPane("Query No.2: Error in query....");
                            final JDialog d = pane.createDialog((JFrame) null, "Alert");
                            d.setLocation(630, 490);
                            d.setVisible(true);
                        }
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }

            }

        }
        class Thread3 extends Thread {
            String s;

            public Thread3(String s) {
                this.s = s;

            }

            @Override
            public void run() {
                // String str=textField.getText();
                String[] word = s.split("\\s");
                try {

                    String url = "jdbc:mysql://localhost:3306/sorting";
                    String uname = "root";
                    String pass = "root";

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection con = DriverManager.getConnection(url, uname, pass);

                    if (word[0].toLowerCase().equals("select")) {

                        PreparedStatement st = con.prepareStatement(s);
                        try {
                            ResultSet rs = st.executeQuery();
                            if (!rs.isBeforeFirst()) {
                                final JOptionPane pane = new JOptionPane("Query No.3  :Record does not exist.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Alert");
                                d.setLocation(630, 630);
                                d.setVisible(true);
                            } else {
                                DefaultTableModel model = new DefaultTableModel();
                                table_2 = new JTable(model);
                                model.addColumn("emp_id");
                                model.addColumn("fname");
                                model.addColumn("lname");
                                model.addColumn("age");
                                model.addColumn("salary");
                                scrollPane_3.setViewportView(table_2);
                                while(rs.next()) {
                                    model.insertRow(0,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)});
                                }
                            }
                        } catch (Exception e1) {
                            final JOptionPane pane = new JOptionPane("Query No.3 :Error in query....");
                            final JDialog d = pane.createDialog((JFrame) null, "Alert");
                            d.setLocation(630, 630);
                            d.setVisible(true);
                        }
                    }
                    if (word[0].toLowerCase().equals("insert")) {
                        Statement st = con.createStatement();
                        try {
                            int m = st.executeUpdate(s);

                            if (m == 1) {
                                final JOptionPane pane = new JOptionPane(
                                        "Query No.3 :Record inserted Successfully.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 630);
                                d.setVisible(true);
                            } else {
                                final JOptionPane pane = new JOptionPane("Query No.3 : insertion failed.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 630);
                                d.setVisible(true);
                            }
                        } catch (Exception e1) {
                            final JOptionPane pane = new JOptionPane("Query No.3 :Error in query....");
                            final JDialog d = pane.createDialog((JFrame) null, "Alert");
                            d.setLocation(630, 630);
                            d.setVisible(true);
                        }
                    }
                    if (word[0].toLowerCase().equals("delete")) {

                        Statement st = con.createStatement();
                        try {
                            int m = st.executeUpdate(s);
                            if (m == 1) {
                                final JOptionPane pane = new JOptionPane(
                                        "Query No.3 : Record Deleted Successfully.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 630);
                                d.setVisible(true);
                            } else {
                                final JOptionPane pane = new JOptionPane(
                                        "Query No.3 : Deletion Failed or record does not exist.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 630);
                                d.setVisible(true);
                            }
                        } catch (Exception e1) {
                            final JOptionPane pane = new JOptionPane("Query No.3 :Error in query....");
                            final JDialog d = pane.createDialog((JFrame) null, "Alert");
                            d.setLocation(630, 630);
                            d.setVisible(true);
                        }
                    }
                    if (word[0].toLowerCase().equals("update")) {
                        Statement st = con.createStatement();
                        try {
                            int m = st.executeUpdate(s);

                            if (m == 1) {
                                final JOptionPane pane = new JOptionPane("Query No.3 : Record Updated Successfully!!");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 630);
                                d.setVisible(true);
                            } else {
                                final JOptionPane pane = new JOptionPane(
                                        "Query No.3 : Updation failed or record does not exist.....");
                                final JDialog d = pane.createDialog((JFrame) null, "Message");
                                d.setLocation(630, 630);
                                d.setVisible(true);
                            }
                        } catch (Exception e1) {
                            final JOptionPane pane = new JOptionPane("Query No.3 :Error in query....");
                            final JDialog d = pane.createDialog((JFrame) null, "Alert");
                            d.setLocation(630, 630);
                            d.setVisible(true);
                        }
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        }
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str1 = textField.getText();
                String str2 = textField_1.getText();
                String str3 = textField_2.getText();

                Thread1 th1 = new Thread1(str1);
                Thread2 th2 = new Thread2(str2);
                Thread3 th3 = new Thread3(str3);

                th1.start();
                th2.start();
                th3.start();
            }
        });

    }
}


















// Database:-------------



// create database sorting;

// use sorting;

// create table employee(
// emp_id int,
// fname varchar(20),
// lname varchar(20),
// age int,
// salary int
// );

// create table disk1(
// emp_id int,
// fname varchar(20),
// lname varchar(20),
// age int,
// salary int
// );

// create table disk2(
// emp_id int,
// fname varchar(20),
// lname varchar(20),
// age int,
// salary int
// );

// create table disk3(
// emp_id int,
// fname varchar(20),
// lname varchar(20),
// age int,
// salary int
// );

// create table disk4(
// emp_id int,
// fname varchar(20),
// lname varchar(20),
// age int,
// salary int
// );


// insert into employee values
// (1,'Vaibhav','Bhus',22,25000),
// (2,'Vaibhav','Bhus',22,25000),
// (3,'Vaibhav','Bhus',22,25000),
// (4,'Vaibhav','Bhus',22,25000),
// (5,'Vaibhav','Bhus',22,25000),
// (6,'Vaibhav','Bhus',22,25000),
// (7,'Vaibhav','Bhus',22,25000),
// (8,'Vaibhav','Bhus',22,25000),
// (9,'Vaibhav','Bhus',22,25000),
// (12,'Vaibhav','Bhus',22,25000),
// (22,'Vaibhav','Bhus',22,25000),
// (33,'Vaibhav','Bhus',22,25000),
// (14,'Vaibhav','Bhus',22,25000),
// (15,'Vaibhav','Bhus',22,25000),
// (26,'Vaibhav','Bhus',22,25000),
// (37,'Vaibhav','Bhus',22,25000),
// (17,'Vaibhav','Bhus',22,25000),
// (30,'Vaibhav','Bhus',22,25000),
// (21,'Vaibhav','Bhus',22,25000);


// Enter Query--

// select fname, lname from employee where emp_id=22;

// select * from employee where emp_id=17;

// select age, salary from employee where emp_id=30;