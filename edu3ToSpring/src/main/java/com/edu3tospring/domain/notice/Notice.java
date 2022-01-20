package com.edu3tospring.domain.notice;

import com.edu3tospring.domain.BaseEntity;
import com.edu3tospring.domain.reply.Reply;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.w3c.dom.html.HTMLBaseFontElement;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "notice_jpa")
@SequenceGenerator(
      name="NOTICE_SEQ_GEN", //시퀀스 제너레이터 이름
      sequenceName="NOTICE_ID_SEQ", //시퀀스 이름
      initialValue=1
)
@Entity
public class Notice extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ_GEN")
    @Column(name = "notice_id")
    private Integer id;
    private String title;
    private String content;
    private int hit;

    @JsonIgnore
    @OneToMany(mappedBy = "notice")
    private List<Reply> replies = new ArrayList<>();


    public void increaseHit() {
        this.hit += 1;
    }
}
