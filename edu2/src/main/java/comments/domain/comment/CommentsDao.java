package comments.domain.comment;

import comments.domain.comment.dto.CommentSaveRequestDto;
import share.JdbcContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentsDao {
    private final JdbcContext jdbcContext = new JdbcContext();

    public Comments save(CommentSaveRequestDto dto) {
        Integer id = jdbcContext.addTemplate((c) -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("Insert into comments values (HIBERNATE_SEQUENCE.nextval, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, dto.getName());
                ps.setString(2, dto.getContent());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        });
        return dto.toEntity(id);
    }

    public void deleteById(Integer id) {
        jdbcContext.commandTemplate((c) -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("delete from comments where id = ?");
                ps.setInt(1, id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        });
    }

    public void deleteAll() {
        jdbcContext.commandTemplate((c) -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("delete from comments");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        });
}

    public Comments findById(Integer id) {
        Comments comments = new Comments();
        try(ResultSet rs = jdbcContext.queryTemplate(c -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("select * from comments where id = ?");
                ps.setInt(1, id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        });) {
            while (rs.next()) {
                comments.setId(rs.getInt(1));
                comments.setName(rs.getString(2));
                comments.setContent(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public List<Comments> findAll() {
        List<Comments> commentsList = new ArrayList<>();
        try(ResultSet rs = jdbcContext.queryTemplate(c -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("select * from comments");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        });) {
            while (rs.next()) {
                Comments comments = new Comments();
                comments.setId(rs.getInt(1));
                comments.setName(rs.getString(2));
                comments.setContent(rs.getString(3));
                commentsList.add(comments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentsList;
    }
}
