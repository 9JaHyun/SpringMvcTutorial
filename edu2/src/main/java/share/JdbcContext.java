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

    public Integer addTemplate(PrepareStatementStrategy strategy) {
        Integer id = null;
        try (Connection c = connect();
             PreparedStatement ps = strategy.makePrepareStatement(c)) {
            int resultRows = ps.executeUpdate();
            if (resultRows == 0) {
                throw new SQLException("Create Fail!");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Create Fail!");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return id;
    }

    // Template 내에서 자원반납을 해버리면 ResultSet도 닫히게 된다.
    // 사실 권장하지는 않는 방법 (단, Spring에서 제공하는 방식은 이 문제점을 극복)
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
