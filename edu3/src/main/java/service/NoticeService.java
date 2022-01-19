package service;

import dao.NoticeDao;
import java.util.List;
import vo.BulletinVO;
import vo.NoticeVO;

public class NoticeService {

    private final NoticeDao noticeDao = NoticeDao.getInstance();

    public void save(NoticeVO noticeVO) {
        noticeDao.insert(noticeVO);
    }

    public NoticeVO findById(Integer id) {
        NoticeVO noticeVO = noticeDao.selectOne(id);
        noticeDao.updateCount(id);
        noticeVO.increaseHit();
        return noticeVO;
    }

    public List<NoticeVO> findByTitle(String title) {
        return noticeDao.selectByTitle(title);
    }

    public List<NoticeVO> findAll() {
        return noticeDao.selectAll();
    }

    public NoticeVO update(NoticeVO noticeVO) {
        return noticeDao.update(noticeVO);
    }

    public void deleteById(Integer id) {
        noticeDao.deleteById(id);
    }

    public void deleteAll() {
        noticeDao.deleteAll();
    }

    public BulletinVO updateCount(BulletinVO bulletinVO) {
        noticeDao.updateCount(bulletinVO.getBbsId());
        bulletinVO.increaseHit();
        return bulletinVO;
    }
}
