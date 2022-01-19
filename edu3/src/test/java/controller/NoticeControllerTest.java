package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import service.NoticeService;
import vo.NoticeVO;

class NoticeControllerTest {
    private MockMvc mvc;
    private NoticeService noticeService = new NoticeService();

    @DisplayName(value = "noticeForm 테스트")
    @Test
    void addFormControllerTest() throws Exception {
        //given
        String url = "http://localhost:8089/edu3_war_exploded/noticeForm.do";

        // when, then
        mvc.perform(get(url))
            .andExpect(status().isOk());
    }

    @DisplayName(value = "noticeAdd 테스트")
    @Test
    void noticeSaveControllerTest() throws Exception {
        //given
        String url = "http://localhost:8089/edu3_war_exploded/noticeAdd.do";

        NoticeVO noticeVO1 = new NoticeVO();
        noticeVO1.setTitle("title1");
        noticeVO1.setContent("content1");

        NoticeVO noticeVO2 = new NoticeVO();
        noticeVO2.setTitle("제목1");
        noticeVO2.setContent("내용1");

        // when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(new ObjectMapper().writeValueAsString(noticeVO1)))
            .andExpect(status().isOk());

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(new ObjectMapper().writeValueAsString(noticeVO2)))
            .andExpect(status().isOk());

        //then
        List<NoticeVO> noticeList = noticeService.findAll();
        assertEquals(noticeVO1.getTitle(), noticeList.get(0).getTitle());
        assertEquals(noticeVO2.getTitle(), noticeList.get(1).getTitle());
    }

    @DisplayName(value = "notice select 테스트")
    @Test
    void noticeSelectControllerTest() throws Exception {
        //given
        String url = "http://localhost:8089/edu3_war_exploded/noticeSelect.do";

        // when
        mvc.perform(get(url))
            .andExpect(status().isOk());

        //then


    }

    @DisplayName(value = "notice update 테스트")
    @Test
    void noticeUpdateControllerTest() throws Exception {
        //given
        String url = "http://localhost:8089/edu3_war_exploded/noticeUpdate.do";
        NoticeVO noticeVO1 = new NoticeVO();
        noticeVO1.setTitle("title1");
        noticeVO1.setContent("content1");
        noticeService.save(noticeVO1);

        NoticeVO beforeUpdate = noticeService.findAll().get(0);
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
        String url = "http://localhost:8089/edu3_war_exploded/noticeDelete.do";

        // when


        //then

    }
}