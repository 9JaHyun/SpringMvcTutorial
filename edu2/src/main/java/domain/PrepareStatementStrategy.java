package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface PrepareStatementStrategy {
    public PreparedStatement makePrepareStatement(Connection connection);
}
