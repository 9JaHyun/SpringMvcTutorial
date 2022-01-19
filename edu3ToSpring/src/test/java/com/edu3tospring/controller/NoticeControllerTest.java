package com.edu3tospring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.edu3tospring.domain.notice.NoticeJpa;
import com.edu3tospring.domain.notice.NoticeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
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
        String url = "http://localhost:" + port + "/noticeForm.do";

        // when, then
        mvc.perform(get(url))
            .andExpect(status().isOk());
    }

    @DisplayName(value = "notice Add 테스트")
    @Test
    void noticeSaveControllerTest() throws Exception {
        //given
        String url = "http://localhost:" + port + "/noticeAdd.do";

        NoticeJpa noticeJpaVO1 = new NoticeJpa();
        noticeJpaVO1.setTitle("title1");
        noticeJpaVO1.setContent("content1");

        NoticeJpa noticeJpaVO2 = new NoticeJpa();
        noticeJpaVO2.setTitle("제목1");
        noticeJpaVO2.setContent("내용1");

        // when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(new ObjectMapper().writeValueAsString(noticeJpaVO1)))
            .andExpect(status().isOk());

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(new ObjectMapper().writeValueAsString(noticeJpaVO2)))
            .andExpect(status().isOk());

        //then
        List<NoticeJpa> noticeJpaList = noticeService.findAll();
        assertEquals(noticeJpaVO1.getTitle(), noticeJpaList.get(0).getTitle());
        assertEquals(noticeJpaVO2.getTitle(), noticeJpaList.get(1).getTitle());
    }

    @DisplayName(value = "notice select 테스트")
    @Test
    void noticeSelectControllerTest() throws Exception {
        //given
        String url = "http://localhost:" + port + "/notice/{}";

        // when
        mvc.perform(get(url))
            .andExpect(status().isOk());

        //then


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
        //given
        String url = "http://localhost:" + port + "/noticeUpdate.do";
        NoticeJpa noticeJpaVO1 = new NoticeJpa();
        noticeJpaVO1.setTitle("title1");
        noticeJpaVO1.setContent("content1");
        noticeService.save(noticeJpaVO1);

        NoticeJpa beforeUpdate = noticeService.findAll().get(0);
        beforeUpdate.setTitle("newTitle1");
        beforeUpdate.setTitle("newContent1");


        // when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(new ObjectMapper().writeValueAsString(beforeUpdate)))
            .andExpect(status().isOk());


        //then

    }

    @DisplayName(value = "notice delete 테스트")
    @Test
    void noticeDeleteControllerTest(){
        //given
        String url = "http://localhost:" + port + "/noticeDelete.do";

        // when


        //then

    }
}