package com.edu3tospring.domain.notice;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Integer save(NoticeJpa noticeJpa) {
        noticeRepository.save(noticeJpa);
        return noticeJpa.getId();
    }

    public NoticeJpa findById(Integer id) {
        NoticeJpa notice = noticeRepository.findById(id)
            .orElseThrow(() -> new AssertionError("잘못된 ID 입니다. ID = " + id));
        notice.increaseHit();
        save(notice);
        return notice;
    }

    public List<NoticeJpa> findByTitle(String title) {
        return noticeRepository.findByTitle(title);
    }

    public List<NoticeJpa> findAll() {
        return noticeRepository.findAll();
    }

    public void deleteById(Integer id) {
        noticeRepository.deleteById(id);
    }

    public void deleteAll() {
        noticeRepository.deleteAll();
    }
}
