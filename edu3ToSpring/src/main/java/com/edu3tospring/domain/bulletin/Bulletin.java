package com.edu3tospring.domain.bulletin;

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
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@SequenceGenerator(
      name="BULLETIN_SEQ_GEN", //시퀀스 제너레이터 이름
      sequenceName="BULLETIN_ID_SEQ", //시퀀스 이름
      initialValue=1
)
@Entity
public class Bulletin extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BULLETIN_SEQ_GEN")
    @Column(name = "bulletin_id")
    public Integer id;
    private String title;
    private String content;
    private String writer;
    private String image;
    private int hit;

    @JsonIgnore
    @OneToMany(mappedBy = "bulletin")
    private List<Reply> replies = new ArrayList<>();

    public void increaseHit() {
        this.hit += 1;
    }
}
