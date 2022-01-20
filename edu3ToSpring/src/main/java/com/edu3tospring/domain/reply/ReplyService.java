package com.edu3tospring.domain.reply;

import com.edu3tospring.domain.bulletin.Bulletin;
import com.edu3tospring.domain.bulletin.BulletinService;
import com.edu3tospring.domain.notice.Notice;
import com.edu3tospring.domain.notice.NoticeService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final NoticeService noticeService;
    private final BulletinService bulletinService;

    public Integer save(Reply reply) {
        Reply save = replyRepository.save(reply);
        return save.getId();
    }

    public Reply findById(Integer id) {
        return replyRepository.findById(id)
              .orElseThrow(() -> new AssertionError("존재하지 않는 댓글입니다."));
    }

    public List<Reply> findByBulletinId(Integer id) {
        return replyRepository.findByBulletinId(id);
    }
    public List<Reply> findByNoticeId(Integer id) {
        return replyRepository.findByNoticeId(id);
    }

    public void deleteById(Integer id) {
        replyRepository.deleteById(id);
    }
}
