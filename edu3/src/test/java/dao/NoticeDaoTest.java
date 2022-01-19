package dao;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.NoticeVO;


public class NoticeDaoTest {

    private NoticeDao noticeDao = NoticeDao.getInstance();

    @AfterEach
    void tearDown() {
        noticeDao.deleteAll();
    }

    @DisplayName(value = "select one 테스트")
    @Test
    void selectOneTest() {
        NoticeVO vo1 = new NoticeVO(1, "test1", "content1", LocalDateTime.now(), 5);
        NoticeVO vo2 = new NoticeVO(2, "test2", "content2", LocalDateTime.now(), 100);
        NoticeVO vo3 = new NoticeVO(3, "test3", "content3", LocalDateTime.now(), 10);
        NoticeVO vo4 = new NoticeVO(4, "test4", "content4", LocalDateTime.now(), 10);

        noticeDao.insert(vo1);
        noticeDao.insert(vo2);
        noticeDao.insert(vo3);
        noticeDao.insert(vo4);

        List<NoticeVO> noticeVOList = noticeDao.selectAll();
        noticeVOList.iterator().forEachRemaining(noticeVO -> {
                Assertions.assertEquals(noticeVO.getTitle(), noticeDao.selectOne(noticeVO.getId()).getTitle());
//                생성이 어찌되었든간에 저장당시 hit은 0이 되어야 한다.
                Assertions.assertSame(0, noticeDao.selectOne(noticeVO.getId()).getHit());
            }
        );
    }

    @DisplayName(value = "select all 테스트")
    @Test
    void selectAllTest() {
        NoticeVO vo1 = new NoticeVO(1, "test1", "content1", LocalDateTime.now(), 0);
        NoticeVO vo2 = new NoticeVO(2, "test2", "content2", LocalDateTime.now(), 0);
        NoticeVO vo3 = new NoticeVO(3, "test3", "content3", LocalDateTime.now(), 0);
        NoticeVO vo4 = new NoticeVO(4, "test4", "content4", LocalDateTime.now(), 0);

        noticeDao.insert(vo1);
        noticeDao.insert(vo2);
        noticeDao.insert(vo3);
        noticeDao.insert(vo4);

        List<NoticeVO> list = noticeDao.selectAll();
        Assertions.assertSame(4, list.size());
    }

}
