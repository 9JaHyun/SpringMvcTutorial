package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import share.JdbcContext;
import vo.BulletinVO;
import vo.NoticeVO;

public class NoticeDao implements DAO<NoticeVO>{
    private JdbcContext jdbcContext = new JdbcContext();
    private static NoticeDao INSTANCE = null;
    private NoticeDao() {
        jdbcContext = new JdbcContext();
    }
    public static NoticeDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NoticeDao();
        }
        return INSTANCE;
    }

    @Override
    public List<NoticeVO> selectAll() {
        List<NoticeVO> noticeList = new ArrayList<>();
        try (ResultSet rs = jdbcContext.queryTemplate(c -> {
            try {
                return c.prepareStatement("select * from NOTICE order by 1");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("Fail To Load");
            }
        });) {
            while (rs.next()) {
                NoticeVO vo = rsToVO(rs);
                noticeList.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noticeList;
    }

    private NoticeVO rsToVO(ResultSet rs) throws SQLException {
        NoticeVO vo = new NoticeVO();
        vo.setId(rs.getInt("notice_id"));
        vo.setTitle(rs.getString("notice_title"));
        vo.setContent(rs.getString("notice_content"));
        vo.setDate(rs.getTimestamp("notice_wdate").toLocalDateTime());
        vo.setHit(rs.getInt("notice_hit"));
        return vo;
    }

    @Override
    public NoticeVO selectOne(int id) {
        NoticeVO vo = null;
        try(ResultSet rs = jdbcContext.queryTemplate(c -> {
            try {
                PreparedStatement ps = c.prepareStatement("select * from NOTICE where NOTICE_ID = ?");
                ps.setInt(1, id);
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("Fail To Load");
            }
        });){
            while (rs.next()) {
                vo = rsToVO(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public List<NoticeVO> selectByTitle(String title) {
        List<NoticeVO> noticeList = new ArrayList<>();

        try(ResultSet rs = jdbcContext.queryTemplate(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(
                    "select * from NOTICE where NOTICE_TITLE like '%' ||? || '%' order by NOTICE_WDATE desc");
                ps.setString(1, title);
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("Fail To Load");
            }
        });){
            while (rs.next()) {
                noticeList.add(rsToVO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noticeList;
    }

    @Override
    public void insert(NoticeVO vo) {

        jdbcContext.commandTemplate(c -> {
            try {
                PreparedStatement ps1 = c.prepareStatement(
                    "insert into NOTICE (NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_WDATE, NOTICE_HIT) values (NOTICE_SEQ.nextval, ?, ?, sysdate, 0)");
                ps1.setString(1, vo.getTitle());
                ps1.setString(2, vo.getContent());
                return ps1;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("Fail To Load");
            }
        });
    }

    @Override
    public NoticeVO update(NoticeVO vo) {
        jdbcContext.commandTemplate(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(
                    "update NOTICE set NOTICE_TITLE = ?, NOTICE_CONTENT = ? where NOTICE_ID = ?");
                ps.setString(1, vo.getTitle());
                ps.setString(2, vo.getContent());
                ps.setInt(3, vo.getId());
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("FAIL TO UPDATE :(");
            }
        });
        return vo;
    }

    @Override
    public void deleteById(int bbsId) {
        jdbcContext.commandTemplate(c -> {
            try {
                PreparedStatement ps = c.prepareStatement("delete from NOTICE where NOTICE_ID = ?");
                ps.setInt(1, bbsId);
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("FAIL TO UPDATE :(");
            }
        });
    }

    public void deleteAll() {
        jdbcContext.commandTemplate(c -> {
            try {
                return c.prepareStatement("delete from NOTICE");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("CANT NOT DELETE :(");
            }
        });
    }
    // 조회수 증가
    public void updateCount(Integer id) {
        jdbcContext.commandTemplate(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(
                    "update NOTICE set NOTICE_HIT = NOTICE_HIT+1 where NOTICE_ID = ?");
                ps.setInt(1, id);
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("FAIL TO UPDATE HIT :(");
            }
        });
    }
}
