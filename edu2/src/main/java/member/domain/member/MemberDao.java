package member.domain.member;

import share.JdbcContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private JdbcContext jdbcContext = new JdbcContext();

    public Member save(Member member) {
        jdbcContext.commandTemplate((c) -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("Insert into MEMBER values (?, ?, ?, ?)");
                ps.setString(1, member.getId());
                ps.setString(2, member.getName());
                ps.setString(3, member.getPassword());
                ps.setString(4, member.getEmail());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        });
        return member;
    }

    public Member findById(String id) {
        Member member = null;
        try(ResultSet rs = jdbcContext.queryTemplate(c -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("select * from MEMBER where id = ?");
                ps.setString(1, id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        })){
            while (rs.next()) {
                member = convertToMember(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public List<Member> findAll() {
        List<Member> memberList = new ArrayList<>();
        try(ResultSet rs = jdbcContext.queryTemplate(c -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("select * from MEMBER");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        })){
            while (rs.next()) {
                Member member = convertToMember(rs);
                memberList.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberList;
    }

    public Member update(Member member) {
        jdbcContext.addTemplate((c) -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("UPDATE MEMBER set name = ?, PASSWORD = ?, EMAIL = ? where id = ?");
                ps.setString(1, member.getName());
                ps.setString(2, member.getPassword());
                ps.setString(3, member.getPassword());
                ps.setString(4, member.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        });
        return member;
    }

    public void deleteById(String id) {
        jdbcContext.addTemplate((c) -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("Delete from MEMBER where id = ?");
                ps.setString(1, id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        });
    }

    public void deleteAll() {
        jdbcContext.addTemplate((c) -> {
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("Delete from MEMBER");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        });
    }

    private Member convertToMember(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setId(rs.getString(1));
        member.setName(rs.getString(2));
        member.setPassword(rs.getString(3));
        member.setEmail(rs.getString(4));
        return member;
    }
}
