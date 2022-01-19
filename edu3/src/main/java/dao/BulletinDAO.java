package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import share.JdbcContext;
import vo.BulletinVO;

public class BulletinDAO implements DAO<BulletinVO> {
    private JdbcContext jdbcContext = new JdbcContext();
    private static BulletinDAO INSTANCE = null;
    private BulletinDAO() {
        jdbcContext = new JdbcContext();
    }
    public static BulletinDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BulletinDAO();
        }
        return INSTANCE;
    }

    @Override
    public List<BulletinVO> selectAll() {
        List<BulletinVO> bulletinVOList = new ArrayList<>();
        try (ResultSet rs = jdbcContext.queryTemplate(c -> {
            try {
                return c.prepareStatement("select * from BULLETIN order by 1");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("Fail To Load");
            }
        });) {
            while (rs.next()) {
                BulletinVO vo = rsToVo(rs);
                bulletinVOList.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bulletinVOList;
    }

    private BulletinVO rsToVo(ResultSet rs) throws SQLException {
        BulletinVO vo = new BulletinVO();
        vo.setBbsId(rs.getInt("id"));
        vo.setBbsTitle(rs.getString("title"));
        vo.setBbsContent(rs.getString("content"));
        vo.setBbsWriter(rs.getString("writer"));
        vo.setBbsImage(rs.getString("image"));
        vo.setBbsCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        vo.setBbsHit(rs.getInt("hit"));
        return vo;
    }

    @Override
    public BulletinVO selectOne(int bbsId) {
        BulletinVO vo = null;
        try(ResultSet rs = jdbcContext.queryTemplate(c -> {
            try {
                return c.prepareStatement(
                    "select * from BULLETIN where ID = ?");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("Fail To Load");
            }
        });){
            while (rs.next()) {
                vo = rsToVo(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public List<BulletinVO> selectByTitle(String title) {
        List<BulletinVO> bulletinVOList = new ArrayList<>();

        try(ResultSet rs = jdbcContext.queryTemplate(c -> {
            try {
                return c.prepareStatement(
                    "select * from BULLETIN where TITLE like '%' ||? || '%' order by CREATE_DATE desc");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("Fail To Load");
            }
        });){
            while (rs.next()) {
                bulletinVOList.add(rsToVo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bulletinVOList;
    }

    @Override
    public void insert(BulletinVO vo) {
        jdbcContext.commandTemplate(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(
                    "insert into BULLETIN (ID, TITLE, CONTENT, WRITER, IMAGE, CREATE_DATE, HIT) values (HIBERNATE_SEQUENCE.nextval, ?, ?, ?, ?, sysdate, 0)");
                ps.setString(1, vo.getBbsTitle());
                ps.setString(2, vo.getBbsContent());
                ps.setString(3, vo.getBbsWriter());
                ps.setString(4, vo.getBbsImage());
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("Fail To Load");
            }
        });
    }

    @Override
    public BulletinVO update(BulletinVO vo) {
        jdbcContext.commandTemplate(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(
                    "update BULLETIN set TITLE = ?, CONTENT = ?,"
                        + "IMAGE = nvl(?, IMAGE) where ID = ?");
                ps.setString(1, vo.getBbsTitle());
                ps.setString(2, vo.getBbsContent());
                ps.setString(3, vo.getBbsImage());
                ps.setInt(4, vo.getBbsId());
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
                PreparedStatement ps = c.prepareStatement("delete from BULLETIN where ID = ?");
                ps.setInt(1, bbsId);
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("FAIL TO UPDATE :(");
            }
        });
    }

    // 조회수 증가
    public void updateCount(Integer id) {
        jdbcContext.commandTemplate(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(
                    "update BULLETIN set HIT = HIT+1 where ID = ?");
                ps.setInt(id, 1);
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new AssertionError("FAIL TO UPDATE HIT :(");
            }
        });
    }
}
