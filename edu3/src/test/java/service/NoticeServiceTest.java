package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.NoticeVO;

public class NoticeServiceTest {

    private NoticeService noticeService = new NoticeService();

    @AfterEach
    void tearDown() {
        noticeService.deleteAll();
    }

    @DisplayName(value = "SAVE 테스트")
    @Test
    void saveTest(){
        NoticeVO vo = new NoticeVO();
        vo.setTitle("testNotice1");
        vo.setContent("content1.");
        noticeService.save(vo);

        NoticeVO findNotice = noticeService.findAll().get(0);
        assertEquals(vo.getTitle(), findNotice.getTitle());
        assertEquals(vo.getContent(), findNotice.getContent());
        Assertions.assertSame(0, findNotice.getHit());
    }

    @DisplayName(value = "조회수 증가 테스트")
    @Test
    void findByIdTest(){
        NoticeVO vo = new NoticeVO();
        vo.setTitle("testNotice1");
        vo.setContent("content1.");
        noticeService.save(vo);

        NoticeVO before = noticeService.findAll().get(0);
        NoticeVO after = noticeService.findById(before.getId());
        assertSame(before.getHit() + 1, after.getHit());
    }

    @DisplayName(value = "제목 검색 테스트")
    @Test
    void findByTitleTest(){
        NoticeVO vo = new NoticeVO();
        vo.setTitle("testNotice1");
        vo.setContent("content1.");
        noticeService.save(vo);

        NoticeVO vo1 = new NoticeVO();
        vo1.setTitle("testNotice2");
        vo1.setContent("content2.");
        noticeService.save(vo1);

        NoticeVO vo2 = new NoticeVO();
        vo2.setTitle("testNotice3");
        vo2.setContent("content3.");
        noticeService.save(vo2);

        NoticeVO vo3 = new NoticeVO();
        vo3.setTitle("notice1");
        vo3.setContent("content4.");
        noticeService.save(vo3);

        NoticeVO vo4 = new NoticeVO();
        vo4.setTitle("notice2");
        vo4.setContent("content5.");
        noticeService.save(vo4);

        List<NoticeVO> noticeVOList = noticeService.findByTitle("test");
        noticeVOList.iterator().forEachRemaining(noticeVO -> {
                assertEquals(noticeVO.getTitle(), noticeService.findById(noticeVO.getId()).getTitle());
            }
        );
        Assertions.assertSame(3, noticeVOList.size());
    }


    @DisplayName(value = "업데이트 테스트")
    @Test
    void updateTest(){
        NoticeVO vo = new NoticeVO();
        vo.setTitle("testNotice1");
        vo.setContent("테스트입니다.");
        noticeService.save(vo);
        NoticeVO beforeUpdate = noticeService.findAll().get(0);

        beforeUpdate.setTitle("updateTestNotice1");
        beforeUpdate.setContent("updateContent");

        NoticeVO updatedNotice = noticeService.update(beforeUpdate);
        assertEquals(beforeUpdate.getId(), updatedNotice.getId());
        Assertions.assertNotEquals(vo.getTitle(), updatedNotice.getTitle());
        Assertions.assertNotEquals(vo.getContent(), updatedNotice.getContent());
    }
}
