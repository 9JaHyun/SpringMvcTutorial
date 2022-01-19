package vo;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BulletinVO {
    private Integer bbsId;
    private String bbsTitle;
    private String bbsContent;
    private String bbsWriter;
    private String bbsImage;
    private LocalDateTime bbsCreateDate;
    private int bbsHit;

    public void increaseHit() {
        this.bbsHit += 1;
    }
}
