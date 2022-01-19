package vo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime date;
    private int hit;

    public void increaseHit() {
        this.hit += 1;
    }
}
