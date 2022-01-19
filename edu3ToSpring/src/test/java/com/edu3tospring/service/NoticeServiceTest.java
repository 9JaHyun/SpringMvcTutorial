package com.edu3tospring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.edu3tospring.domain.notice.NoticeJpa;
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
        NoticeJpa noticeJpa = new NoticeJpa();
        noticeJpa.setTitle("testNotice1");
        noticeJpa.setContent("content1.");
        noticeService.save(noticeJpa);

        NoticeJpa findNoticeJpa = noticeService.findAll().get(0);
        assertEquals(noticeJpa.getTitle(), findNoticeJpa.getTitle());
        assertEquals(noticeJpa.getContent(), findNoticeJpa.getContent());
        Assertions.assertSame(0, findNoticeJpa.getHit());
    }

    @DisplayName(value = "조회수 증가 테스트")
    @Test
    void findByIdTest(){
        NoticeJpa noticeJpa = new NoticeJpa();
        noticeJpa.setTitle("testNotice1");
        noticeJpa.setContent("content1.");
        Integer id = noticeService.save(noticeJpa);

        NoticeJpa before = noticeService.findById(id);
        NoticeJpa after = noticeService.findById(id);
        assertSame(before.getHit() + 1, after.getHit());
    }

    @DisplayName(value = "제목 검색 테스트")
    @Test
    void findByTitleTest(){
        NoticeJpa noticeJpa = new NoticeJpa();
        noticeJpa.setTitle("testNotice1");
        noticeJpa.setContent("content1.");
        noticeService.save(noticeJpa);

        NoticeJpa noticeJpa1 = new NoticeJpa();
        noticeJpa1.setTitle("testNotice2");
        noticeJpa1.setContent("content2.");
        noticeService.save(noticeJpa1);

        NoticeJpa noticeJpa2 = new NoticeJpa();
        noticeJpa2.setTitle("testNotice3");
        noticeJpa2.setContent("content3.");
        noticeService.save(noticeJpa2);

        NoticeJpa noticeJpa3 = new NoticeJpa();
        noticeJpa3.setTitle("notice1");
        noticeJpa3.setContent("content4.");
        noticeService.save(noticeJpa3);

        NoticeJpa noticeJpa4 = new NoticeJpa();
        noticeJpa4.setTitle("notice2");
        noticeJpa4.setContent("content5.");
        noticeService.save(noticeJpa4);

        List<NoticeJpa> noticeJpaVOList = noticeService.findByTitle("test");
        noticeJpaVOList.iterator().forEachRemaining(noticeJpaVO ->
            assertEquals(noticeJpaVO.getTitle(), noticeService.findById(noticeJpaVO.getId()).getTitle())
        );
        Assertions.assertSame(3, noticeJpaVOList.size());
    }


    @DisplayName(value = "업데이트 테스트")
    @Test
    void updateTest(){
        NoticeJpa vo = new NoticeJpa();
        vo.setTitle("testNotice1");
        vo.setContent("테스트입니다.");
        Integer id = noticeService.save(vo);
        NoticeJpa beforeUpdate = noticeService.findById(id);

        beforeUpdate.setTitle("updateTestNotice1");
        beforeUpdate.setContent("updateContent");

        Integer afterId = noticeService.save(beforeUpdate);

        assertEquals(beforeUpdate.getId(), afterId);
        assertEquals(beforeUpdate.getTitle(), noticeService.findById(id).getTitle());
        assertEquals(beforeUpdate.getContent(), noticeService.findById(id).getContent());
    }
}
