package service;

import java.util.List;
import vo.BulletinVO;

public interface BulletinService {

    public List<BulletinVO> selectList();

    public BulletinVO selectOne(int bbsId);

    List<BulletinVO> selectByTitle(String title);

    public BulletinVO insert(BulletinVO vo);

    public BulletinVO update(BulletinVO vo);

    public int delete(int bbsId);
}
