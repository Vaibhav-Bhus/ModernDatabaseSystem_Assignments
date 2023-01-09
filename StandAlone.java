import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

class GUI extends JPanel implements ActionListener
{
    JLabel queryLabel, outputLabel;
    JTextArea query;
    JTable table;
    JScrollPane tableScrollPane;
    JButton executeButton;

    GUI()
    {
        queryLabel = new JLabel("Query:");
        queryLabel.setPreferredSize(new Dimension(100, 20));
        add(queryLabel);

        query = new JTextArea(3, 60);
        add(query);

        outputLabel = new JLabel("Output:");
        outputLabel.setPreferredSize(new Dimension(100, 20));
        add(outputLabel);

        tableScrollPane = new JScrollPane();
        tableScrollPane.setPreferredSize(new Dimension(650, 400));
        add(tableScrollPane);

        executeButton = new JButton("Execute");
        executeButton.addActionListener(this);
        add(executeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            ArrayList<String[]> result = StandAlone.execute(query.getText());

            if (result != null)
            {
                int rows = result.size() - 1;
                int columns = result.get(0).length;

                String[] columnNames = result.get(rows);
                String[][] rowData = new String[rows][columns];

                for (int i = 0; i < rows; i++)
                    System.arraycopy(result.get(i), 0, rowData[i], 0, columns);

                table = new JTable(rowData, columnNames);
                tableScrollPane.setViewportView(table);
            }
        }
        catch (SQLException sqlException)
        {
//            sqlException.printStackTrace();
            System.out.println("SQLException: " + sqlException.getMessage());
            System.out.println("SQLState: " + sqlException.getSQLState());
            System.out.println("VendorError: " + sqlException.getErrorCode());
            String[][] rows = new String[][]{{sqlException.getSQLState(), sqlException.getMessage()}};
            String[] columns = new String[]{"SQL State", "Error Message"};

            table = new JTable(rows, columns);
            tableScrollPane.setViewportView(table);
        }
    }
}

public class StandAlone
{
    static Connection connection;

    static ArrayList<String[]> execute(String query) throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();

        if (resultSet != null)
        {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columns = resultSetMetaData.getColumnCount();

            ArrayList<String[]> result = new ArrayList<>();

            while (resultSet.next())
            {
                String[] row = new String[columns];

                for (int i = 0; i < columns; i++)
                    row[i] = resultSet.getString(i + 1);

                result.add(row);
//            System.out.println(row);
            }

            String[] columnNames = new String[columns];

            for (int i = 0; i < columns; i++)
                columnNames[i] = resultSetMetaData.getColumnLabel(i + 1);

            result.add(columnNames);
            return result;
        }

        return null;
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Database");
        frame.setContentPane(new GUI());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);

        String url = "jdbc:mysql://localhost:3306/samiksha";
        String user = "root";
        String password = "root";

        try
        {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.out.println("SQLException: " + sqlException.getMessage());
            System.out.println("SQLState: " + sqlException.getSQLState());
            System.out.println("VendorError: " + sqlException.getErrorCode());
        }
    }
}











// DATABASE-----


// create database samiksha;

// use samiksha;




// create table vaibhav(id int,name varchar(20), age int);

// insert into vaibhav values(2,'Samiksha',21);

// update vaibhav set name = 'Yash' where id = 1;

// delete from vaibhav  where id = 2;

