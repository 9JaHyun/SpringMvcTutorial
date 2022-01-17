package common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    public Connection connect() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String id = "hr";
            String password = "hr";
            connection = DriverManager.getConnection(url, id, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public int add(CommentInsertDto dto) {
        int id = 0;
        try (Connection conn = connect();
             PreparedStatement ps1 = conn.prepareStatement("insert into comments values ((select value from id_repository where name = 'COMMENT'), ?, ?)");
             PreparedStatement ps2 = conn.prepareStatement("select value from ID_REPOSITORY where name = 'COMMENT'");
             PreparedStatement ps3 = conn.prepareStatement("update ID_REPOSITORY id set VALUE = id.VALUE+1 where NAME = 'COMMENT'")) {
            ps1.setString(1, dto.getName());
            ps1.setString(2, dto.getContent());
            ps1.executeUpdate();
            try (ResultSet rs = ps2.executeQuery();) {
                rs.next();
                id = rs.getInt(1);
            }
            ps3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void update(Comment comment) {
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement("update comments set name = ?, content = ? where id = ?")) {
            ps.setString(1, comment.getName());
            ps.setString(2, comment.getContent());
            ps.setInt(3, comment.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement("select * from comments order by id")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comment comment = new Comment();
                    comment.setId(rs.getInt(1));
                    comment.setName(rs.getString(2));
                    comment.setContent(rs.getString(3));

                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public void deleteById(int id) {
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement("delete from comments where id = ?")){

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
