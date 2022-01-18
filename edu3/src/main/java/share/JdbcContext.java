package share;

import java.sql.*;

public class JdbcContext {
    private Connection connect() throws ClassNotFoundException {
        Connection connection = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "mycalendar";
        String password = "mycalendar";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void commandTemplate(PrepareStatementStrategy strategy) {
        try (Connection c = connect();
             PreparedStatement ps = strategy.makePrepareStatement(c)) {
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet queryTemplate(PrepareStatementStrategy strategy) {
        ResultSet resultSet = null;
        try {
            Connection c = connect();
            PreparedStatement ps = strategy.makePrepareStatement(c);
            resultSet = ps.executeQuery();
        } catch (SQLException |
                ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void disConnect() {

    }
}
