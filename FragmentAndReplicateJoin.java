import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FragmentAndReplicateJoin extends Thread {
    private String rln1;
    static Connection con = null;
    static Statement stmt = null;
    DefaultTableModel n1, n2, n3;
    JFrame frame;
    private JTable table_r;
    private JTable table_s;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_1;
    private JTable table_r1;
    private JTable table_r2;
    private JTable table_r3;
    private JScrollPane scrollPane_2;
    private JScrollPane scrollPane_3;
    private JScrollPane scrollPane_4;
    private JTable r1s;
    private JTable r2s;
    private JTable r3s;
    private JLabel lblRI;
    private JLabel lblRI_1;
    private JLabel lblRI_2;
    private JButton btnShowResults;
    private JTable result_t;
    private JScrollPane scrollPane_5;

    public static void main(String[] args) throws SQLException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FragmentAndReplicateJoin window = new FragmentAndReplicateJoin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FragmentAndReplicateJoin() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1246, 1110);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        JLabel lblRelationR = new JLabel("Relation r :");
        lblRelationR.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
        lblRelationR.setBounds(15, 16, 123, 36);
        frame.getContentPane().add(lblRelationR);
        JLabel lblRelationS = new JLabel("Relation s :");
        lblRelationS.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
        lblRelationS.setBounds(622, 22, 123, 25);
        frame.getContentPane().add(lblRelationS);
        scrollPane = new JScrollPane();
        scrollPane.setBounds(131, 18, 459, 196);
        frame.getContentPane().add(scrollPane);
        table_r = new JTable(new DefaultTableModel(new Object[] { "EN_NO", "SNAME", "BRANCH", "CLASS", "ROLLNO" }, 0));
        scrollPane.setViewportView(table_r);
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(735, 18, 331, 196);
        frame.getContentPane().add(scrollPane_1);
        table_s = new JTable(new DefaultTableModel(new Object[] { "EN_NO", "SNAME", "PER" }, 0));
        scrollPane_1.setViewportView(table_s);
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FragmentAndReplicate", "root", "root");

            Statement st1 = con.createStatement();
            Statement st2 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("select * from stud_info");
            ResultSet rs2 = st2.executeQuery("select * from stud_per");
            DefaultTableModel r_tab = (DefaultTableModel) table_r.getModel();
            DefaultTableModel s_tab = (DefaultTableModel) table_s.getModel();
            while (rs1.next()) {
                r_tab.addRow(new Object[] { rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getString(4),
                        rs1.getInt(5) });
            }
            while (rs2.next()) {
                s_tab.addRow(new Object[] { rs2.getInt(1), rs2.getString(2), rs2.getDouble(3) });
            }
        } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        JButton btnFragmentRelationR = new JButton("Fragment Relation r");
        btnFragmentRelationR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JLabel lblR = new JLabel("r1 : ");
                    lblR.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
                    lblR.setBounds(28, 362, 34, 20);
                    frame.getContentPane().add(lblR);
                    JLabel lblR_1 = new JLabel("r2 : ");
                    lblR_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
                    lblR_1.setBounds(439, 362, 45, 20);
                    frame.getContentPane().add(lblR_1);
                    JLabel lblR_2 = new JLabel("r3 :");
                    lblR_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
                    lblR_2.setBounds(838, 362, 34, 20);
                    frame.getContentPane().add(lblR_2);
                    scrollPane_2 = new JScrollPane();
                    scrollPane_2.setBounds(67, 290, 346, 190);
                    frame.getContentPane().add(scrollPane_2);
                    table_r1 = new JTable(
                            new DefaultTableModel(new Object[] { "EN_NO", "SNAME", "BRANCH", "CLASS", "ROLLNO" }, 0));
                    scrollPane_2.setViewportView(table_r1);
                    scrollPane_3 = new JScrollPane();
                    scrollPane_3.setBounds(474, 290, 336, 190);
                    frame.getContentPane().add(scrollPane_3);
                    table_r2 = new JTable(
                            new DefaultTableModel(new Object[] { "EN_NO", "SNAME", "BRANCH", "CLASS", "ROLLNO" }, 0));
                    scrollPane_3.setViewportView(table_r2);
                    scrollPane_4 = new JScrollPane();
                    scrollPane_4.setBounds(869, 290, 331, 190);
                    frame.getContentPane().add(scrollPane_4);
                    table_r3 = new JTable(
                            new DefaultTableModel(new Object[] { "EN_NO", "SNAME", "BRANCH", "CLASS", "ROLLNO" }, 0));
                    scrollPane_4.setViewportView(table_r3);
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from stud_info");
                    stmt = con.createStatement();
                    DefaultTableModel m1 = (DefaultTableModel) table_r1.getModel();
                    DefaultTableModel m2 = (DefaultTableModel) table_r2.getModel();
                    DefaultTableModel m3 = (DefaultTableModel) table_r3.getModel();
                    while (rs.next()) {
                        int en = rs.getInt(1);
                        if (en >= 1001 && en <= 1010) {
                            stmt.executeUpdate("insert into r1 values(" + en + ",'" + rs.getString(2) + "','"
                                    + rs.getString(3) + "','" + rs.getString(4) + "'," + rs.getInt(5) + ")");
                            m1.addRow(new Object[] { en, rs.getString(2), rs.getString(3), rs.getString(4),
                                    rs.getInt(5) });
                        } else if (en >= 1011 && en <= 1020) {
                            stmt.executeUpdate("insert into r2 values(" + en + ",'" + rs.getString(2) + "','"
                                    + rs.getString(3) + "','" + rs.getString(4) + "'," + rs.getInt(5) + ")");
                            m2.addRow(new Object[] { en, rs.getString(2), rs.getString(3), rs.getString(4),
                                    rs.getInt(5) });
                        } else if (en >= 1020) {
                            stmt.executeUpdate("insert into r3 values(" + en + ",'" + rs.getString(2) + "','"
                                    + rs.getString(3) + "','" + rs.getString(4) + "'," + rs.getInt(5) + ")");
                            m3.addRow(new Object[] { en, rs.getString(2), rs.getString(3), rs.getString(4),
                                    rs.getInt(5) });
                        }
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnFragmentRelationR.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
        btnFragmentRelationR.setBounds(112, 230, 290, 41);
        frame.getContentPane().add(btnFragmentRelationR);
        JButton btnJoinWithReplications = new JButton("Join With Replications of relation s");
        btnJoinWithReplications.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                r1s = new JTable(
                        new DefaultTableModel(new Object[] { "EN_NO", "SNAME", "BRANCH", "CLASS", "ROLLNO" }, 0));
                r1s.setBounds(67, 595, 346, 123);
                frame.getContentPane().add(r1s);
                r2s = new JTable(
                        new DefaultTableModel(new Object[] { "EN_NO", "SNAME", "BRANCH", "CLASS", "ROLLNO" }, 0));
                r2s.setBounds(474, 595, 336, 123);
                frame.getContentPane().add(r2s);
                r3s = new JTable(
                        new DefaultTableModel(new Object[] { "EN_NO", "SNAME", "BRANCH", "CLASS", "ROLLNO" }, 0));
                r3s.setBounds(869, 595, 331, 123);
                frame.getContentPane().add(r3s);
                n1 = (DefaultTableModel) r1s.getModel();
                n2 = (DefaultTableModel) r2s.getModel();
                n3 = (DefaultTableModel) r3s.getModel();
                String q = "select * from r1 inner join stud_per on r1.en_no=stud_per.en_no";
                PreparedStatement ps;
                try {
                    Statement st1 = con.createStatement();
                    ps = con.prepareStatement(q);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        st1.executeUpdate("insert into result values(" + rs.getInt(1) + ",'" + rs.getString(2) + "','"
                                + rs.getString(3) + "','" + rs.getString(4) + "'," + rs.getInt(5) + ","
                                + rs.getDouble(8) + ")");
                        n1.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getInt(5), rs.getDouble(8) });
                    }
                    q = "select * from r2 inner join stud_per on r2.en_no=stud_per.en_no";
                    ps = con.prepareStatement(q);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        st1.executeUpdate("insert into result values(" + rs.getInt(1) + ",'" + rs.getString(2) + "','"
                                + rs.getString(3) + "','" + rs.getString(4) + "'," + rs.getInt(5) + ","
                                + rs.getDouble(8) + ")");
                        n2.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getInt(5), rs.getDouble(8) });
                    }
                    q = "select * from r3 inner join stud_per on r3.en_no=stud_per.en_no";
                    ps = con.prepareStatement(q);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        st1.executeUpdate("insert into result values(" + rs.getInt(1) + ",'" + rs.getString(2) + "','"
                                + rs.getString(3) + "','" + rs.getString(4) + "'," + rs.getInt(5) + ","
                                + rs.getDouble(8) + ")");
                        n3.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getInt(5), rs.getDouble(8) });
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnJoinWithReplications.setFont(new Font("Yu Gothic UI", Font.BOLD,24));
        btnJoinWithReplications.setBounds(200, 500, 589, 41);
        frame.getContentPane().add(btnJoinWithReplications);
        JButton btnDropTables = new JButton("drop tables");
        btnDropTables.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    stmt.execute("drop table r1");
                    stmt.execute("drop table r2");
                    stmt.execute("drop table r3");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnDropTables.setBounds(1073, 89, 115, 29);
        frame.getContentPane().add(btnDropTables);
        lblRI = new JLabel("r1 I><I s");
        lblRI.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
        lblRI.setBounds(131, 741, 85, 20);
        frame.getContentPane().add(lblRI);
        lblRI_1 = new JLabel("r2 I><I s");
        lblRI_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
        lblRI_1.setBounds(555, 741, 85, 20);
        frame.getContentPane().add(lblRI_1);
        lblRI_2 = new JLabel("r3 I><I s");
        lblRI_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
        lblRI_2.setBounds(981, 741, 85, 20);
        frame.getContentPane().add(lblRI_2);
        btnShowResults = new JButton("Show Results :");
        btnShowResults.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane_5 = new JScrollPane();
                scrollPane_5.setBounds(272, 797, 790, 237);
                frame.getContentPane().add(scrollPane_5);
                result_t = new JTable(new DefaultTableModel(
                        new Object[] { "EN_NO", "SNAME", "BRANCH", "CLASS", "ROLLNO", "PER" }, 0));
                scrollPane_5.setViewportView(result_t);
                DefaultTableModel res = (DefaultTableModel) result_t.getModel();
                Statement st2;
                try {
                    st2 = con.createStatement();
                    ResultSet rs1 = st2.executeQuery("select * from result");
                    while (rs1.next()) {
                        res.addRow(new Object[] { rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getString(4),
                                rs1.getInt(5), rs1.getDouble(6) });
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnShowResults.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
        btnShowResults.setBounds(27, 829, 189, 36);
        frame.getContentPane().add(btnShowResults);

    }
}




















// DATABASE---------------



// create database  FragmentAndReplicate;


// use FragmentAndReplicate;


// create table stud_info(
// en_no int primary key,
// Sname varchar(20),
// branch varchar(20),
// class varchar(10),
// rollNo int
// );



// create table r1(
// en_no int,
// Sname varchar(20),
// branch varchar(20),
// class varchar(10),
// rollNo int
// );


// create table r2(
// en_no int ,
// Sname varchar(20),
// branch varchar(20),
// class varchar(10),
// rollNo int
// );


// create table r3(
// en_no int ,
// Sname varchar(20),
// branch varchar(20),
// class varchar(10),
// rollNo int
// );


// create table stud_per(
// en_no int,
// sname varchar(20),
// per int,
// CONSTRAINT FKersonOrder FOREIGN KEY (en_no)
//     REFERENCES stud_info(en_no)
// );


// create table result(
// en_no int,
// sname varchar(20),
// branch varchar(10),
// class varchar(3),
// rollno int,
// per int
// );



// insert into stud_info values
// (1001,'Bhus','CSE','FE',47),
// (1002,'Bhus','CSE','FE',47),
// (1003,'Bhus','CSE','FE',47),
// (1004,'Bhus','CSE','FE',47),
// (1005,'Bhus','CSE','FE',47),
// (1011,'Bhus','CSE','FE',47),
// (1012,'Bhus','CSE','FE',47),
// (1013,'Bhus','CSE','FE',47),
// (1014,'Bhus','CSE','FE',47),
// (1015,'Bhus','CSE','FE',47),
// (1021,'Bhus','CSE','FE',47),
// (1022,'Bhus','CSE','FE',47),
// (1023,'Bhus','CSE','FE',47),
// (1024,'Bhus','CSE','FE',47),
// (1025,'Bhus','CSE','FE',47);



// insert into stud_per values
// (1001,'Bhus',98),
// (1003,'Bhus',98),
// (1005,'Bhus',98),
// (1012,'Bhus',98),
// (1014,'Bhus',98),
// (1015,'Bhus',98),
// (1021,'Bhus',98),
// (1023,'Bhus',98),
// (1024,'Bhus',98),
// (1002,'Bhus',98);