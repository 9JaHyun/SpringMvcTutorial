package com.edu3tospring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.edu3tospring.domain.notice.Notice;
import com.edu3tospring.domain.notice.NoticeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoticeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    @Autowired
    private NoticeService noticeService;

    @BeforeEach
    void init() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .build();
    }

    @AfterEach
    public void tearDown() {
        noticeService.deleteAll();
    }

    @DisplayName(value = "notice Form 테스트")
    @Test
    void addFormControllerTest() throws Exception {
        //given
        String url = "http://localhost:" + port + "/notices/add";

        // when, then
        mvc.perform(get(url))
            .andExpect(status().isOk());
    }

    @DisplayName(value = "notice Add 테스트")
    @Test
    void noticeSaveControllerTest() throws Exception {
        //given
        String url = "http://localhost:" + port + "/notices/add";

        Notice noticeVO1 = new Notice();
        noticeVO1.setTitle("title1");
        noticeVO1.setContent("content1");

        Notice noticeVO2 = new Notice();
        noticeVO2.setTitle("제목1");
        noticeVO2.setContent("내용1");

        // when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(new ObjectMapper().writeValueAsString(noticeVO1)))
            .andExpect(status().is3xxRedirection());

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(new ObjectMapper().writeValueAsString(noticeVO2)))
            .andExpect(status().is3xxRedirection());

        //then
        List<Notice> noticeList = noticeService.findAll();
        assertEquals(noticeVO1.getTitle(), noticeList.get(0).getTitle());
        assertEquals(noticeVO2.getTitle(), noticeList.get(1).getTitle());
    }

    @DisplayName(value = "notice select 테스트")
    @Test
    void noticeSelectControllerTest() throws Exception {
        Notice noticeVO1 = new Notice();
        noticeVO1.setTitle("title1");
        noticeVO1.setContent("content1");

        Notice noticeVO2 = new Notice();
        noticeVO2.setTitle("제목1");
        noticeVO2.setContent("내용1");

        Integer notice1Id = noticeService.save(noticeVO1);
        Integer notice2Id = noticeService.save(noticeVO2);
        //given
        String url = "http://localhost:" + port + "/notices/" + notice1Id;
        // when
        mvc.perform(get(url))
            .andExpect(status().isOk());
    }

    @DisplayName(value = "notice List 테스트")
    @Test
    void noticeListControllerTest() throws Exception {
        //given
        String url = "http://localhost:" + port + "/notices";

        // when
        mvc.perform(get(url))
            .andExpect(status().isOk());

        //then
    }

    @DisplayName(value = "notice update 테스트")
    @Test
    void noticeUpdateControllerTest() throws Exception {
        Notice noticeVO1 = new Notice();
        noticeVO1.setTitle("title1");
        noticeVO1.setContent("content1");
        Integer saveId = noticeService.save(noticeVO1);

        //given
        String url = "http://localhost:" + port + "/notices/" + saveId + "/update";

        Notice updateNotice = noticeService.findAll().get(0);
        updateNotice.setTitle("newTitle1");
        updateNotice.setTitle("newContent1");


        // when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(new ObjectMapper().writeValueAsString(updateNotice)))
            .andExpect(status().is3xxRedirection());

        //then
        Assertions.assertNotEquals(noticeVO1.getTitle(), noticeService.findById(saveId).getTitle());
        Assertions.assertEquals(updateNotice.getTitle(), noticeService.findById(saveId).getTitle());
    }

    @DisplayName(value = "notice delete 테스트")
    @Test
    void noticeDeleteControllerTest() throws Exception {
        Notice noticeVO1 = new Notice();
        noticeVO1.setTitle("title1");
        noticeVO1.setContent("content1");
        Integer saveId = noticeService.save(noticeVO1);

        Assertions.assertEquals(noticeVO1.getTitle(), noticeService.findById(saveId).getTitle());

        //given
        String url = "http://localhost:" + port + "/notices/" + saveId + "/delete";

        // when
        mvc.perform(post(url))
              .andExpect(status().isOk());

        //then
        Assertions.assertThrows(AssertionError.class, () -> noticeService.findById(saveId));

    }
}