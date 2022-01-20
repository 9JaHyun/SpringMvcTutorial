package com.edu3tospring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.edu3tospring.domain.notice.Notice;
import com.edu3tospring.domain.notice.NoticeService;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;

    @AfterEach
    void tearDown() {
        noticeService.deleteAll();
    }

    @DisplayName(value = "SAVE 테스트")
    @Test
    void saveTest(){
        Notice notice = new Notice();
        notice.setTitle("testNotice1");
        notice.setContent("content1.");
        noticeService.save(notice);

        Notice findNotice = noticeService.findAll().get(0);
        assertEquals(notice.getTitle(), findNotice.getTitle());
        assertEquals(notice.getContent(), findNotice.getContent());
        Assertions.assertSame(0, findNotice.getHit());
    }

    @DisplayName(value = "조회수 증가 테스트")
    @Test
    void findByIdTest(){
        Notice notice = new Notice();
        notice.setTitle("testNotice1");
        notice.setContent("content1.");
        Integer id = noticeService.save(notice);

        Notice before = noticeService.findById(id);
        Notice after = noticeService.findById(id);
        assertSame(before.getHit() + 1, after.getHit());
    }

    @DisplayName(value = "제목 검색 테스트")
    @Test
    void findByTitleTest(){
        Notice notice = new Notice();
        notice.setTitle("testNotice1");
        notice.setContent("content1.");
        noticeService.save(notice);

        Notice notice1 = new Notice();
        notice1.setTitle("testNotice2");
        notice1.setContent("content2.");
        noticeService.save(notice1);

        Notice notice2 = new Notice();
        notice2.setTitle("testNotice3");
        notice2.setContent("content3.");
        noticeService.save(notice2);

        Notice notice3 = new Notice();
        notice3.setTitle("notice1");
        notice3.setContent("content4.");
        noticeService.save(notice3);

        Notice notice4 = new Notice();
        notice4.setTitle("notice2");
        notice4.setContent("content5.");
        noticeService.save(notice4);

        List<Notice> noticeVOList = noticeService.findByTitle("test");
        noticeVOList.iterator().forEachRemaining(noticeJpaVO ->
            assertEquals(noticeJpaVO.getTitle(), noticeService.findById(noticeJpaVO.getId()).getTitle())
        );
        Assertions.assertSame(3, noticeVOList.size());
    }


    @DisplayName(value = "업데이트 테스트")
    @Test
    void updateTest(){
        Notice vo = new Notice();
        vo.setTitle("testNotice1");
        vo.setContent("테스트입니다.");
        Integer id = noticeService.save(vo);
        Notice beforeUpdate = noticeService.findById(id);

        beforeUpdate.setTitle("updateTestNotice1");
        beforeUpdate.setContent("updateContent");

        Integer afterId = noticeService.save(beforeUpdate);

        assertEquals(beforeUpdate.getId(), afterId);
        assertEquals(beforeUpdate.getTitle(), noticeService.findById(id).getTitle());
        assertEquals(beforeUpdate.getContent(), noticeService.findById(id).getContent());
    }
}
