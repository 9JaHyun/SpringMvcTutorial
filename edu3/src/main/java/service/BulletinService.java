package service;

import dao.BulletinDAO;
import java.util.List;
import vo.BulletinVO;

public class BulletinService {
    private final BulletinDAO bulletinDAO = BulletinDAO.getInstance();

    public void save(BulletinVO bulletinVO) {
        bulletinDAO.insert(bulletinVO);
    }

    public BulletinVO findById(Integer id) {
        return bulletinDAO.selectOne(id);
    }

    public List<BulletinVO> findByTitle(String title) {
        return bulletinDAO.selectByTitle(title);
    }

    public List<BulletinVO> findAll() {
        return bulletinDAO.selectAll();
    }

    public BulletinVO update(BulletinVO bulletinVO) {
        return bulletinDAO.update(bulletinVO);
    }

    public BulletinVO updateCount(BulletinVO bulletinVO) {
        bulletinDAO.updateCount(bulletinVO.getBbsId());
        bulletinVO.increaseHit();
        return bulletinVO;
    }

    public void delete(Integer id) {
        bulletinDAO.deleteById(id);
    }
}
