
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.awt.event.ActionEvent;
import java.awt.Color;

public class RoundRobin {

    public static JFrame frame;
    private JTextField textField;
    private JTable table;
    private JTable table_1;
    private JTable table_2;
    private JTable table_3;
    private JTable table_4;
    public JTable table_5;
//    DefaultTableModel model;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RoundRobin window = new RoundRobin ();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RoundRobin() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1493, 829);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);




        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(14, 293, 336, 107);
        frame.getContentPane().add(scrollPane_1);

        DefaultTableModel model1 = new DefaultTableModel();
        table_1 = new JTable(model1);
        model1.addColumn("emp_id");
        model1.addColumn("fname");
        model1.addColumn("lname");
        model1.addColumn("age");
        model1.addColumn("salary");
        scrollPane_1.setViewportView(table_1);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(360, 293, 352, 104);
        frame.getContentPane().add(scrollPane_2);

        DefaultTableModel model2 = new DefaultTableModel();

        table_2 = new JTable(model2);
        model2.addColumn("emp_id");
        model2.addColumn("fname");
        model2.addColumn("lname");
        model2.addColumn("age");
        model2.addColumn("salary");
        scrollPane_2.setViewportView(table_2);

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(722, 293, 362, 107);
        frame.getContentPane().add(scrollPane_3);

        DefaultTableModel model3 = new DefaultTableModel();
        table_3 = new JTable(model3);
        model3.addColumn("emp_id");
        model3.addColumn("fname");
        model3.addColumn("lname");
        model3.addColumn("age");
        model3.addColumn("salary");
        scrollPane_3.setViewportView(table_3);

        JScrollPane scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(1094, 293, 375, 107);
        frame.getContentPane().add(scrollPane_4);

        DefaultTableModel model4 = new DefaultTableModel();
        table_4 = new JTable(model4);
        model4.addColumn("emp_id");
        model4.addColumn("fname");
        model4.addColumn("lname");
        model4.addColumn("age");
        model4.addColumn("salary");
        scrollPane_4.setViewportView(table_4);

        try {

            String url = "jdbc:mysql://localhost:3306/sorting";
            String uname = "root";
            String pass = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection(url, uname, pass);
            String query = "select * from employee";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

        } catch (Exception E) {
            E.printStackTrace();
        }

        JButton btnNewButton = new JButton("Round Robin Partitioning");
        btnNewButton.setForeground(new Color(0, 0, 128));
        btnNewButton.setBackground(new Color(255, 215, 0));
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    String url = "jdbc:mysql://localhost:3306/sorting";
                    String uname = "root";
                    String pass = "root";

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection con = DriverManager.getConnection(url, uname, pass);
                    String query = "select * from employee";
                    PreparedStatement st = con.prepareStatement(query);
                    ResultSet rs = st.executeQuery();
                    PreparedStatement s1 = con.prepareStatement("TRUNCATE TABLE disk1");
                    s1.execute();
                    PreparedStatement s2 = con.prepareStatement("TRUNCATE TABLE disk2");
                    s2.execute();
                    PreparedStatement s3 = con.prepareStatement("TRUNCATE TABLE disk3");
                    s3.execute();
                    PreparedStatement s4 = con.prepareStatement("TRUNCATE TABLE disk4");
                    s4.execute();
                    model1.setRowCount(0);
                    model2.setRowCount(0);
                    model3.setRowCount(0);
                    model4.setRowCount(0);
                    int pixel = 0;
                    while (rs.next()) {

                        if (pixel%4 == 0) {

                            model1.insertRow(0, new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3),
                                    rs.getString(4), rs.getInt(5) });
                            java.sql.Connection c = DriverManager.getConnection(url, uname, pass);

                            String q = "insert into disk1 values (?,?,?,?,?)";
                            PreparedStatement s = c.prepareStatement(q);
                            s.setInt(1, rs.getInt(1));
                            s.setString(2, rs.getString(2));
                            s.setString(3, rs.getString(3));
                            s.setString(4, rs.getString(4));
                            s.setInt(5, rs.getInt(5));
                            s.execute();
                        } else if (pixel%4 == 1) {

                            model2.insertRow(0, new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3),
                                    rs.getString(4), rs.getInt(5) });
                            java.sql.Connection c = DriverManager.getConnection(url, uname, pass);

                            String q = "insert into disk2 values (?,?,?,?,?)";
                            PreparedStatement s = c.prepareStatement(q);
                            s.setInt(1, rs.getInt(1));
                            s.setString(2, rs.getString(2));
                            s.setString(3, rs.getString(3));
                            s.setString(4, rs.getString(4));
                            s.setInt(5, rs.getInt(5));
                            s.execute();
                        } else if (pixel%4 == 2) {

                            model3.insertRow(0, new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3),
                                    rs.getString(4), rs.getInt(5) });
                            java.sql.Connection c = DriverManager.getConnection(url, uname, pass);

                            String q = "insert into disk3 values (?,?,?,?,?)";
                            PreparedStatement s = c.prepareStatement(q);
                            s.setInt(1, rs.getInt(1));
                            s.setString(2, rs.getString(2));
                            s.setString(3, rs.getString(3));
                            s.setString(4, rs.getString(4));
                            s.setInt(5, rs.getInt(5));
                            s.execute();
                        } else {

                            model4.insertRow(0, new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3),
                                    rs.getString(4), rs.getInt(5) });
                            java.sql.Connection c = DriverManager.getConnection(url, uname, pass);

                            String q = "insert into disk4 values (?,?,?,?,?)";
                            PreparedStatement s = c.prepareStatement(q);
                            s.setInt(1, rs.getInt(1));
                            s.setString(2, rs.getString(2));
                            s.setString(3, rs.getString(3));
                            s.setString(4, rs.getString(4));
                            s.setInt(5, rs.getInt(5));
                            s.execute();
                        }
                        pixel ++;
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }

            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.setBounds(517, 230, 351, 53);
        frame.getContentPane().add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Enter Query");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(26, 480, 155, 20);
        frame.getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 16));
        textField.setBounds(176, 463, 868, 55);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButton_1 = new JButton("Execute");
        btnNewButton_1.setForeground(new Color(0, 0, 128));
        btnNewButton_1.setBackground(new Color(255, 215, 0));

        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_1.setBounds(336, 528, 189, 47);
        frame.getContentPane().add(btnNewButton_1);

        JLabel lblNewLabel_1 = new JLabel("Disk 1");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(123, 410, 44, 13);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Disk 2");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(460, 407, 44, 13);
        frame.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Disk 3");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1_1.setBounds(824, 410, 44, 13);
        frame.getContentPane().add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_2 = new JLabel("Disk 4");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1_2.setBounds(1143, 410, 44, 13);
        frame.getContentPane().add(lblNewLabel_1_1_2);

        JScrollPane scrollPane_5 = new JScrollPane();
        scrollPane_5.setBounds(139, 585, 877, 197);
        frame.getContentPane().add(scrollPane_5);

        DefaultTableModel model = new DefaultTableModel();
        table_5 = new JTable(model);
        model.addColumn("emp_id");
        model.addColumn("fname");
        model.addColumn("lname");
        model.addColumn("age");
        model.addColumn("salary");
        scrollPane_5.setViewportView(table_5);

        class Thread1 extends Thread {
            private String s;

            public Thread1(String s) {
                this.s = s;

            }

            @Override
            public void run() {
                try {

                    String url = "jdbc:mysql://localhost:3306/sorting";
                    String uname = "root";
                    String pass = "root";
                    String[] word = s.split("\\s");

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection con = DriverManager.getConnection(url, uname, pass);
                    PreparedStatement st = con.prepareStatement(s.replace("employee", "disk1"));
                    ResultSet rs = st.executeQuery();
                    while(rs.next()) {
                        model.insertRow(0,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)});
                    }
//					
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
                try {

                    String url = "jdbc:mysql://localhost:3306/sorting";
                    String uname = "root";
                    String pass = "root";

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection con = DriverManager.getConnection(url, uname, pass);
                    PreparedStatement st = con.prepareStatement(s.replace("employee", "disk2"));
                    ResultSet rs = st.executeQuery();
                    while(rs.next()) {
                        model.insertRow(0,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)});
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
                try {

                    String url = "jdbc:mysql://localhost:3306/sorting";
                    String uname = "root";
                    String pass = "root";

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection con = DriverManager.getConnection(url, uname, pass);
                    PreparedStatement st = con.prepareStatement(s.replace("employee", "disk3"));
                    ResultSet rs = st.executeQuery();

                    while(rs.next()) {
                        model.insertRow(0,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)});
                    }

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        }
        class Thread4 extends Thread {
            String s;

            public Thread4(String s) {
                this.s = s;

            }

            @Override
            public void run() {
                try {

                    String url = "jdbc:mysql://localhost:3306/sorting";
                    String uname = "root";
                    String pass = "root";

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection con = DriverManager.getConnection(url, uname, pass);
                    PreparedStatement st = con.prepareStatement(s.replace("employee", "disk4"));
                    ResultSet rs = st.executeQuery();
                    while(rs.next()) {
                        model.insertRow(0,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)});
                    }

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        }

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = textField.getText();

                Thread1 th1 = new Thread1(str);
                Thread2 th2 = new Thread2(str);
                Thread3 th3 = new Thread3(str);
                Thread4 th4 = new Thread4(str);
                th1.start();
                th2.start();
                th3.start();
                th4.start();

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
// (10,'Vaibhav','Bhus',22,25000);


// Enter Query--
// select * from employee where emp_id=1;
