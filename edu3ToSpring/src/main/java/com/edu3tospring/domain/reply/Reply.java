package com.edu3tospring.domain.reply;

import com.edu3tospring.domain.BaseEntity;
import com.edu3tospring.domain.bulletin.Bulletin;
import com.edu3tospring.domain.notice.Notice;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@SequenceGenerator(
      name="REPLY_SEQ_GEN", //시퀀스 제너레이터 이름
      sequenceName="REPLY_ID_SEQ", //시퀀스 이름
      initialValue=1
)
@Entity
public class Reply extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLY_SEQ_GEN")
    @Column(name = "reply_id")
    private Integer id;
    private String content;
    private String writer;

    @ManyToOne
    @JoinColumn(name = "bulletin_id")
    private Bulletin bulletin;

    @ManyToOne
    @JoinColumn(name = "notice_id")
    private Notice notice;

    public void addReply(Bulletin bulletin) {
        this.bulletin = bulletin;
        bulletin.getReplies().add(this);
    }

    public void addReply(Notice notice) {
        this.notice = notice;
        notice.getReplies().add(this);
    }
}
